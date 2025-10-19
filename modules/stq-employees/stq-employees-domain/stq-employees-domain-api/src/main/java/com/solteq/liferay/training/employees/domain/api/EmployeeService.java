package com.solteq.liferay.training.employees.domain.api;

import com.solteq.liferay.training.employees.domain.exception.EmployeeValidationException;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    default int getEmployeeCount() {
        return getEmployeeCount(null);
    }

    default List<Employee> getEmployees(int start, int end) {
        return getEmployees(start, end, null);
    }

    int getEmployeeCount(EmployeeFilter filter);

    List<Employee> getEmployees(int start, int end, EmployeeFilter filter);


    Employee getEmployeeById(long employeeId);

    Employee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber,
                          String companyName, String jobTitle, String department, String workLocation) throws EmployeeValidationException;

    void deleteEmployee(long employeeId);

    String getRepositoryType();
}