package com.solteq.liferay.training.employees.domain.filter;

public class EmployeeFilter {

    private final String search;
    private final String jobTitle;
    private final String companyName;
    private final String department;
    private final String workLocation;

    private EmployeeFilter(String search, String jobTitle, String companyName, String department, String workLocation) {
        this.search = search;
        this.jobTitle = jobTitle;
        this.companyName = companyName;
        this.department = department;
        this.workLocation = workLocation;
    }

    public static EmployeeFilter of(String search, String jobTitle, String companyName, String department, String workLocation) {
        return new EmployeeFilter(search, jobTitle, companyName, department, workLocation);
    }

    public String getSearch() {
        return search;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getDepartment() {
        return department;
    }

    public String getWorkLocation() {
        return workLocation;
    }

}