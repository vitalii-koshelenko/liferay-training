package com.solteq.liferay.training.employees.web.portlet.employeelist.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import com.solteq.liferay.training.employees.domain.model.Employee;
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
                "mvc.command.name=" + EmployeesListPortletKeys.MVC_COMMAND_EDIT,
        },
        service = MVCRenderCommand.class
)
public class EditMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        long employeeId = ParamUtil.getLong(renderRequest, EmployeesListKeys.EMPLOYEE_ID);
        Employee employee = employeesService.getEmployeeById(employeeId);
        renderRequest.setAttribute(EmployeesListKeys.EMPLOYEE, employee);
        return EmployeesListPortletKeys.EDIT_JSP;
    }

    @Reference
    private EmployeeService employeesService;
}
