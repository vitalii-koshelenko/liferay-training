package com.solteq.liferay.training.employees.web.portlet.employeelist.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.web.constants.EmployeesListKeys;
import com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys;
import jakarta.portlet.PortletException;
import jakarta.portlet.RenderRequest;
import jakarta.portlet.RenderResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = {
                "jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID,
                "mvc.command.name=" + EmployeesListPortletKeys.MVC_COMMAND_DEFAULT,
        },
        service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

        String searchTerm = ParamUtil.getString(renderRequest, EmployeesListKeys.SEARCH_TERM);
        String companyName = ParamUtil.getString(renderRequest, EmployeesListKeys.COMPANY_NAME);
        String jobTitle = ParamUtil.getString(renderRequest, EmployeesListKeys.JOB_TITLE);
        String department = ParamUtil.getString(renderRequest, EmployeesListKeys.DEPARTMENT);
        String workLocation = ParamUtil.getString(renderRequest, EmployeesListKeys.WORK_LOCATION);
        EmployeeFilter employeeFilter = EmployeeFilter.of(searchTerm, jobTitle, companyName, department, workLocation);
        renderRequest.setAttribute(EmployeesListKeys.EMPLOYEE_FILTER, employeeFilter);

        renderRequest.setAttribute(EmployeeService.class.getName(), employeeService);

        return EmployeesListPortletKeys.VIEW_JSP;
    }

    @Reference
    private EmployeeService employeeService;
}
