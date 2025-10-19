package com.solteq.liferay.training.employees.domain.model;

import com.solteq.liferay.training.employees.domain.enumeration.CompanyName;
import com.solteq.liferay.training.employees.domain.enumeration.Department;
import com.solteq.liferay.training.employees.domain.enumeration.WorkLocation;

public interface Employee {

    long getEmployeeId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getJobTitle();

    String getPhoneNumber();

    String getCompanyName();

    default String getCompanyNameLabel() {
        CompanyName companyName = CompanyName.fromKey(getCompanyName());
        return companyName != null ? companyName.getLabel() : getCompanyName();
    }

    String getDepartment();

    default String getDepartmentLabel() {
        Department department = Department.fromKey(getDepartment());
        return department != null ? department.getLabel() : getDepartment();
    }

    String getWorkLocation();

    default String getWorkLocationLabel() {
        WorkLocation workLocation = WorkLocation.fromKey(getWorkLocation());
        return workLocation != null ? workLocation.getLabel() : getWorkLocation();
    }

}