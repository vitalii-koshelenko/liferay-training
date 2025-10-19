package com.solteq.liferay.training.employees.domain.enumeration;

import com.liferay.portal.kernel.util.StringUtil;
import com.solteq.liferay.training.employees.domain.constants.CompanyNameConstants;

public enum CompanyName {

    SOLTEQ_OYJ(CompanyNameConstants.SOLTEQ_OYJ, CompanyNameConstants.SOLTEQ_OYJ_LABEL),
    SOLTEQ_POLAND(CompanyNameConstants.SOLTEQ_POLAND, CompanyNameConstants.SOLTEQ_POLAND_LABEL);

    private final String key;
    private final String label;

    CompanyName(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public static CompanyName fromKey(String key) {
        for (CompanyName companyName : CompanyName.values()) {
            if (StringUtil.equalsIgnoreCase(companyName.getKey(), key)) {
                return companyName;
            }
        }
        return null;
    }

}
