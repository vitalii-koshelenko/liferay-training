package com.solteq.liferay.training.employees.domain.impl;

import com.liferay.portal.kernel.util.Validator;
import com.solteq.liferay.training.employees.domain.api.EmployeeValidator;
import com.solteq.liferay.training.employees.domain.exception.EmployeeValidationException;
import com.solteq.liferay.training.employees.domain.model.Employee;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = EmployeeValidator.class)
public class DefaultEmployeeValidator implements EmployeeValidator {

    @Override
    public void validateEmployee(Employee employee) throws EmployeeValidationException {
        String firstName = employee.getFirstName();
        if (Validator.isBlank(firstName)) {
            throw new EmployeeValidationException.FirstNameRequired();
        }
        String lastName = employee.getLastName();
        if (Validator.isBlank(lastName)) {
            throw new EmployeeValidationException.LastNameRequired();
        }
        String email = employee.getEmail();
        if (Validator.isBlank(email)) {
            throw new EmployeeValidationException.EmailRequired();
        }
    }

}
