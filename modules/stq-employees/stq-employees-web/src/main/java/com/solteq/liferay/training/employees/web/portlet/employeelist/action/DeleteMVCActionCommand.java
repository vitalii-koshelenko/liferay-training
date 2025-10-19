package com.solteq.liferay.training.employees.web.portlet.employeelist.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import com.solteq.liferay.training.employees.web.constants.EmployeesListKeys;
import com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import jakarta.portlet.MimeResponse;
import jakarta.portlet.RenderURL;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = {
                "jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID,
                "mvc.command.name=" + EmployeesListPortletKeys.MVC_COMMAND_DELETE
        },
        service = MVCActionCommand.class
)
public class DeleteMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {

        // Delete Employee
        long employeeId = ParamUtil.getLong(actionRequest, EmployeesListKeys.EMPLOYEE_ID);
        employeeService.deleteEmployee(employeeId);

        // Redirect
        RenderURL redirectURL = actionResponse.createRedirectURL(MimeResponse.Copy.PUBLIC);
        sendRedirect(actionRequest, actionResponse, redirectURL.toString());
    }

    @Reference
    private EmployeeService employeeService;
}
