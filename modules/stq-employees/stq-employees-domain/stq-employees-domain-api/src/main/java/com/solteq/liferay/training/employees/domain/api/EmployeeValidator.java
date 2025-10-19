package com.solteq.liferay.training.employees.domain.api;

import com.solteq.liferay.training.employees.domain.exception.EmployeeValidationException;
import com.solteq.liferay.training.employees.domain.model.Employee;

public interface EmployeeValidator {

    void validateEmployee(Employee employee) throws EmployeeValidationException;

}
