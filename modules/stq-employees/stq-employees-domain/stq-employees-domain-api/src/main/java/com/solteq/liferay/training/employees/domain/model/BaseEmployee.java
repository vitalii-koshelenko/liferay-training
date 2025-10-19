package com.solteq.liferay.training.employees.domain.model;

import java.util.Objects;

public class BaseEmployee implements Employee {

    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String phoneNumber;
    private String companyName;
    private String department;
    private String workLocation;

    public BaseEmployee() {
    }

    public BaseEmployee(long employeeId, String firstName, String lastName, String email, String jobTitle,
                        String phoneNumber, String companyName, String department, String workLocation) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.jobTitle = jobTitle;
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.department = department;
        this.workLocation = workLocation;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        BaseEmployee employee = (BaseEmployee) o;
        return employeeId == employee.employeeId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(employeeId);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"employeeId\": ").append(employeeId);
        sb.append(", \"firstName\": \"").append(firstName).append("\"");
        sb.append(", \"lastName\": \"").append(lastName).append("\"");
        sb.append(", \"email\": \"").append(email).append("\"");
        sb.append(", \"jobTitle\": \"").append(jobTitle).append("\"");
        sb.append(", \"phoneNumber\": \"").append(phoneNumber).append("\"");
        sb.append(", \"companyName\": \"").append(companyName).append("\"");
        sb.append(", \"department\": \"").append(department).append("\"");
        sb.append(", \"workLocation\": \"").append(workLocation).append("\"");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(String workLocation) {
        this.workLocation = workLocation;
    }

}
