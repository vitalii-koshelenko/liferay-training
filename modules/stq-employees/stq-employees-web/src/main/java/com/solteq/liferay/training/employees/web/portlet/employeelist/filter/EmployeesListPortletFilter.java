package com.solteq.liferay.training.employees.web.portlet.employeelist.filter;

import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import com.solteq.liferay.training.employees.web.constants.EmployeesListKeys;
import com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import jakarta.portlet.filter.FilterChain;
import jakarta.portlet.filter.FilterConfig;
import jakarta.portlet.filter.PortletFilter;
import jakarta.portlet.filter.RenderFilter;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

@Component(
        property = {
                "jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID,
        },
        service = PortletFilter.class
)
public class EmployeesListPortletFilter implements RenderFilter {

    @Override
    public void init(FilterConfig filterConfig) throws PortletException {
    }

    @Override
    public void doFilter(RenderRequest renderRequest, RenderResponse renderResponse, FilterChain filterChain) throws IOException, PortletException {
        renderRequest.setAttribute(EmployeesListKeys.REPOSITORY_TYPE, employeeService.getRepositoryType());
        filterChain.doFilter(renderRequest, renderResponse);
    }

    @Override
    public void destroy() {
    }

    @Reference
    private EmployeeService employeeService;

}