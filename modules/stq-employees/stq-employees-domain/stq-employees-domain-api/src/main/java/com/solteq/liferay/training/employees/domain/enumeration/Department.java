package com.solteq.liferay.training.employees.domain.enumeration;

import com.liferay.portal.kernel.util.StringUtil;
import com.solteq.liferay.training.employees.domain.constants.DepartmentConstants;

public enum Department {

    FRONTEND(DepartmentConstants.FRONTEND, DepartmentConstants.FRONTEND_LABEL),
    BUSINESS_SOLUTIONS(DepartmentConstants.BUSINESS_SOLUTIONS, DepartmentConstants.BUSINESS_SOLUTIONS_LABEL);

    private final String key;
    private final String label;

    Department(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public static Department fromKey(String key) {
        for (Department department : Department.values()) {
            if (StringUtil.equalsIgnoreCase(department.getKey(), key)) {
                return department;
            }
        }
        return null;
    }

}
