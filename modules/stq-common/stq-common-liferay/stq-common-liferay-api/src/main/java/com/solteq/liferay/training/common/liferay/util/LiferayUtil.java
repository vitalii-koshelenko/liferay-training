package com.solteq.liferay.training.common.liferay.util;

import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.solteq.liferay.training.common.liferay.constants.LocaleConstants;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public final class LiferayUtil {

    private LiferayUtil() {
    }

    public static long getDefaultCompanyId() {
        List<Company> companies = CompanyLocalServiceUtil.getCompanies(0, 1);
        return ListUtil.isNotEmpty(companies) ? companies.getFirst().getCompanyId() : 0;
    }

    public static User getAdminUser() {
        try {
            long defaultCompanyId = getDefaultCompanyId();
            List<User> adminUsers = UserLocalServiceUtil.getUsersByRoleName(defaultCompanyId, RoleConstants.ADMINISTRATOR, 0, 1);
            return ListUtil.isNotEmpty(adminUsers) ? adminUsers.getFirst() : null;
        } catch (Exception e) {
            return null;
        }
    }

    public static long getAdminUserId() {
        User adminUser = getAdminUser();
        return adminUser != null ? adminUser.getUserId() : 0;
    }

    public static Map<Locale, String> getLocalizedMap(String name) {
        Map<Locale, String> localizedMap = new HashMap<>();
        localizedMap.put(LocaleConstants.DEFAULT_LOCALE, name);
        return localizedMap;
    }

}