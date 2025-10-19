package com.solteq.liferay.training.employees.repository.objects;

import com.liferay.object.model.ObjectDefinition;
import com.liferay.object.service.ObjectDefinitionLocalService;
import com.liferay.object.service.ObjectEntryLocalService;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.solteq.liferay.training.common.liferay.api.UserService;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.common.liferay.util.ServiceContextUtil;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.BaseEmployee;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import com.solteq.liferay.training.employees.repository.objects.constants.UserObjectConstants;
import com.solteq.liferay.training.employees.repository.objects.internal.UserObjectSearcher;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "repository.type=objects",
                "service.ranking:Integer=104"
        },
        service = EmployeeRepository.class
)
public class ObjectsEmployeeRepository implements EmployeeRepository {

    @Override
    public List<Employee> fetchEmployees() {
        return fetchEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    @Override
    public int fetchEmployeeCount(EmployeeFilter filter) {
        return userObjectSearcher.searchUsersCount(filter);
    }

    @Override
    public List<Employee> fetchEmployees(int start, int end, EmployeeFilter filter) {
        List<User> users = userObjectSearcher.searchUsers(start, end, filter);
        return users.stream()
                .map(this::toEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Employee fetchEmployeeById(long employeeId) {
        User user = userLocalService.fetchUser(employeeId);
        return toEmployee(user);
    }

    @Override
    public Employee saveEmployee(long userId, String firstName, String lastName, String email, String phoneNumber,
                                 String companyName, String jobTitle, String department, String workLocation) {
        ServiceContext serviceContext = ServiceContextUtil.getServiceContext();
        User user = null;
        if (Validator.isNull(userId)) {
            // Save Liferay User
            user = userService.createUser(firstName, lastName, email, phoneNumber, jobTitle);
            userId = user.getUserId();
        } else {
            // Update Liferay User
            user = userService.updateUser(userId, firstName, lastName, email, phoneNumber, jobTitle);
        }
        // Save Extended Values (System Object Fields)
        saveUserExtendedValues(userId, companyName, department, workLocation, serviceContext);
        return toEmployee(user);
    }

    private void saveUserExtendedValues(long userId, String companyName, String department, String workLocation, ServiceContext serviceContext) {
        try {
            Map<String, Serializable> extendedValues = new HashMap<>();
            extendedValues.put(UserObjectConstants.FIELD_COMPANY_NAME, companyName);
            extendedValues.put(UserObjectConstants.FIELD_DEPARTMENT, department);
            extendedValues.put(UserObjectConstants.FIELD_WORK_LOCATION, workLocation);
            objectEntryLocalService.addOrUpdateExtensionDynamicObjectDefinitionTableValues(
                    serviceContext.getUserId(),
                    getUserObjectDefinition(),
                    userId,
                    extendedValues,
                    serviceContext
            );
        } catch (Exception e) {
            throw new RuntimeException(String.format("Failed to save extended values for user #%d, cause: %s.", userId, e.getMessage()), e);
        }
    }

    private ObjectDefinition getUserObjectDefinition() {
        return objectDefinitionLocalService.fetchObjectDefinitionByExternalReferenceCode(UserObjectConstants.ERC_USER, LiferayUtil.getDefaultCompanyId());
    }

    @Override
    public void deleteEmployee(long employeeId) {
        try {
            userLocalService.deleteUser(employeeId);
        } catch (Exception e) {
            _log.error(String.format("Failed to delete user #%d, cause: %s.", employeeId, e.getMessage()));
        }
    }

    private Employee toEmployee(User user) {
        if (user == null) {
            return null;
        }
        try {
            // Basic Fields
            BaseEmployee employee = new BaseEmployee();
            long userId = user.getUserId();
            employee.setEmployeeId(userId);
            employee.setFirstName(user.getFirstName());
            employee.setLastName(user.getLastName());
            employee.setEmail(user.getEmailAddress());
            employee.setJobTitle(user.getJobTitle());

            // Extension Fields
            Map<String, Serializable> extensionValues = objectEntryLocalService.getExtensionDynamicObjectDefinitionTableValues(getUserObjectDefinition(), userId);
            String companyName = MapUtil.getString(extensionValues, UserObjectConstants.FIELD_COMPANY_NAME);
            String department = MapUtil.getString(extensionValues, UserObjectConstants.FIELD_DEPARTMENT);
            String workLocation = MapUtil.getString(extensionValues, UserObjectConstants.FIELD_WORK_LOCATION);
            employee.setCompanyName(companyName);
            employee.setDepartment(department);
            employee.setWorkLocation(workLocation);
            return employee;
        } catch (Exception e) {
            _log.error(String.format("Failed to convert User #%d to Employee, cause: %s.", user.getUserId(), e.getMessage()));
            return null;
        }
    }

    @Reference
    private UserService userService;
    @Reference
    private UserLocalService userLocalService;
    @Reference
    private UserObjectSearcher userObjectSearcher;
    @Reference
    private ObjectEntryLocalService objectEntryLocalService;
    @Reference
    private ObjectDefinitionLocalService objectDefinitionLocalService;

    private static final Log _log = LogFactoryUtil.getLog(ObjectsEmployeeRepository.class);
}