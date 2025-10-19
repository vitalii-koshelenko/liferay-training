package com.solteq.liferay.training.employees.configuration.util;

import com.liferay.portal.configuration.module.configuration.ConfigurationProviderUtil;
import com.liferay.portal.kernel.module.configuration.ConfigurationException;
import com.solteq.liferay.training.employees.configuration.EmployeesRepositoryConfiguration;

public final class EmployeesConfigurationUtil {

    private EmployeesConfigurationUtil() {}

    public static EmployeesRepositoryConfiguration getEmployeesRepositoryConfiguration() throws ConfigurationException {
        return ConfigurationProviderUtil.getSystemConfiguration(EmployeesRepositoryConfiguration.class);
    }

}
