package com.solteq.liferay.training.employees.repository.api;


import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.Employee;

import java.util.List;

public interface EmployeeRepository {

    List<Employee> fetchEmployees();

    int fetchEmployeeCount(EmployeeFilter filter);

    List<Employee> fetchEmployees(int start, int end, EmployeeFilter filter);

    Employee fetchEmployeeById(long employeeId);

    Employee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber,
                          String companyName, String jobTitle, String department, String workLocation);

    void deleteEmployee(long employeeId);

}