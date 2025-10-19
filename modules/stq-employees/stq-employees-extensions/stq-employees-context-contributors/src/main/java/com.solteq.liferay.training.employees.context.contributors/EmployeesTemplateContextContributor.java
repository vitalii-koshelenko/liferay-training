package com.solteq.liferay.training.employees.context.contributors;

import com.liferay.portal.kernel.template.TemplateContextContributor;
import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import jakarta.servlet.http.HttpServletRequest;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Map;

@Component(property = "type=" + TemplateContextContributor.TYPE_GLOBAL, service = TemplateContextContributor.class)
public class EmployeesTemplateContextContributor implements TemplateContextContributor {

    public static final String EMPLOYEE_SERVICE = "employeeService";

    @Override
    public void prepare(Map<String, Object> contextObjects, HttpServletRequest httpServletRequest) {
        contextObjects.put(EMPLOYEE_SERVICE, employeeService);
    }

    @Reference
    private EmployeeService employeeService;
}