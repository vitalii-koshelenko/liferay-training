package com.solteq.liferay.training.employees.domain.enumeration;

import com.solteq.liferay.training.employees.domain.constants.JobTitleConstants;

public enum JobTitle {

    DEVELOPER(JobTitleConstants.DEVELOPER),
    FRONTEND_DEVELOPER(JobTitleConstants.FRONTEND_DEVELOPER),
    SOFTWARE_DEVELOPER(JobTitleConstants.SOFTWARE_DEVELOPER),
    SENIOR_SOFTWARE_DEVELOPER(JobTitleConstants.SENIOR_SOFTWARE_DEVELOPER),
    LEAD_DEVELOPER(JobTitleConstants.LEAD_DEVELOPER),
    LIFERAY_DXP_ARCHITECT(JobTitleConstants.LIFERAY_DXP_ARCHITECT),
    SOLUTION_ARCHITECT(JobTitleConstants.SOLUTION_ARCHITECT),
    HEAD_OF_BUSINESS_SOLUTIONS(JobTitleConstants.HEAD_OF_BUSINESS_SOLUTIONS);

    private final String label;

    JobTitle(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}