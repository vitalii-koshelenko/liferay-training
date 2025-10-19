package com.solteq.liferay.training.employees.repository.objects.internal.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.InfrastructureUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.Validator;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.repository.objects.internal.UserObjectSearcher;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Component(service = UserObjectSearcher.class)
public class UserObjectSearcherImpl implements UserObjectSearcher {

    private DataSource dataSource = null;
    private String userXTableName = "";

    @Activate
    public void init() {
        dataSource = InfrastructureUtil.getDataSource();
        userXTableName = "User_X_" + LiferayUtil.getDefaultCompanyId();
    }

    @Override
    public int searchUsersCount(EmployeeFilter filter) {
        int usersCount = 0;
        try {
            String conditionsSql = buildConditionsSQL(filter);
            String sql = new StringBundler("SELECT COUNT(*) AS cnt FROM User_ u ")
                    .append("INNER JOIN ")
                    .append(userXTableName)
                    .append(" ux on u.userId = ux.userId ")
                    .append("WHERE 1 = 1 ")
                    .append(conditionsSql)
                    .toString();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    usersCount = resultSet.getInt("cnt");
                }
            }
        } catch (Exception e) {
            _log.error("Error: " + e.getMessage(), e);
        }
        return usersCount;
    }

    @Override
    public List<User> searchUsers(int start, int end, EmployeeFilter filter) {
        List<User> users = new ArrayList<>();
        List<Long> userIds = getUserIds(start, end, filter);
        if (ListUtil.isNotEmpty(userIds)) {
            for (Long userId : userIds) {
                User user = userLocalService.fetchUser(userId);
                users.add(user);
            }
        }
        return users;
    }

    private List<Long> getUserIds(int start, int end, EmployeeFilter filter) {
        List<Long> userIds = new ArrayList<>();
        try {
            int limit = end - start;
            String conditionsSql = buildConditionsSQL(filter);
            StringBundler sqlBuilder = new StringBundler("SELECT u.userId FROM User_ u ")
                    .append("INNER JOIN ")
                    .append(userXTableName)
                    .append(" ux on u.userId = ux.userId ")
                    .append("WHERE 1 = 1 ")
                    .append(conditionsSql);

            if (start != QueryUtil.ALL_POS && end != QueryUtil.ALL_POS) {
                sqlBuilder = sqlBuilder.append(" OFFSET ")
                        .append(start)
                        .append(" LIMIT ")
                        .append(limit);
            }
            String sql = sqlBuilder.toString();
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement ps = connection.prepareStatement(sql);
                 ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    userIds.add(resultSet.getLong("userId"));
                }
            }
        } catch (Exception e) {
            _log.error("Error: " + e.getMessage(), e);
        }
        return userIds;
    }

    private String buildConditionsSQL(EmployeeFilter filter) {
        StringBundler conditionsSql = new StringBundler();
        if (filter != null) {
            // Filter by System Object Fields
            String companyName = filter.getCompanyName();
            if (Validator.isNotNull(companyName)) {
                conditionsSql.append(" AND ux.companyName_ = '" + companyName + "'");
            }
            String department = filter.getDepartment();
            if (Validator.isNotNull(department)) {
                conditionsSql.append(" AND ux.department_ = '" + department + "'");
            }
            String workLocation = filter.getWorkLocation();
            if (Validator.isNotNull(workLocation)) {
                conditionsSql.append(" AND ux.workLocation_ = '" + workLocation + "'");
            }
            // Filter by User Fields
            String jobTitle = filter.getJobTitle();
            if (Validator.isNotNull(jobTitle)) {
                conditionsSql.append(" AND u.jobTitle = '" + jobTitle + "'");
            }
            String searchTerm = filter.getSearch();
            if (Validator.isNotNull(searchTerm)) {
                conditionsSql.append(" AND (u.firstName LIKE '%" + searchTerm + "%' OR u.lastName LIKE '%" + searchTerm + "%' OR u.emailAddress = '" + searchTerm + "')");
            }
        }
        return conditionsSql.toString();
    }

    @Reference
    private UserLocalService userLocalService;

    private static final Log _log = LogFactoryUtil.getLog(UserObjectSearcherImpl.class);
}
