package com.solteq.liferay.training.employees.configuration.util;

import com.solteq.liferay.training.employees.configuration.EmployeesRepositoryConfiguration;

public class EmployeesRepositoryConfigurationUtil {

    public static final String REPOSITORY_TYPE_DEFAULT = "database";

    public static String getRepositoryType() {
        try {
            EmployeesRepositoryConfiguration configuration =  EmployeesConfigurationUtil.getEmployeesRepositoryConfiguration();
            return configuration != null ? configuration.repositoryType() : REPOSITORY_TYPE_DEFAULT;
        } catch (Exception e) {
            return REPOSITORY_TYPE_DEFAULT;
        }
    }

}
