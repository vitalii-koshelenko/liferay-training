package com.solteq.liferay.training.employees.domain.exception;

import com.liferay.portal.kernel.exception.PortalException;

public class EmployeeValidationException extends PortalException {

    public EmployeeValidationException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public static class FirstNameRequired extends EmployeeValidationException {
        public FirstNameRequired() {
            super("First name is required.", null);
        }
    }

    public static class LastNameRequired extends EmployeeValidationException {
        public LastNameRequired() {
            super("Last name is required.", null);
        }
    }

    public static class EmailRequired extends EmployeeValidationException {
        public EmailRequired() {
            super("Email is required.", null);
        }
    }

    public static class EmailShouldBeUnique extends EmployeeValidationException {
        public EmailShouldBeUnique() {
            super("Email should be unique.", null);
        }
    }

}
