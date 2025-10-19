package com.solteq.liferay.training.employees.web.portlet.employeelist.action;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.util.ParamUtil;
import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import com.solteq.liferay.training.employees.domain.exception.EmployeeValidationException;
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
                "mvc.command.name=" + EmployeesListPortletKeys.MVC_COMMAND_EDIT
        },
        service = MVCActionCommand.class
)
public class EditMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        try {
            // Save Employee Data
            long employeeId = ParamUtil.getLong(actionRequest, EmployeesListKeys.EMPLOYEE_ID);
            String firstName = ParamUtil.getString(actionRequest, EmployeesListKeys.FIRST_NAME);
            String lastName = ParamUtil.getString(actionRequest, EmployeesListKeys.LAST_NAME);
            String email = ParamUtil.getString(actionRequest, EmployeesListKeys.EMAIL);
            String phoneNumber = ParamUtil.getString(actionRequest, EmployeesListKeys.PHONE_NUMBER);
            String companyName = ParamUtil.getString(actionRequest, EmployeesListKeys.COMPANY_NAME);
            String jobTitle = ParamUtil.getString(actionRequest, EmployeesListKeys.JOB_TITLE);
            String department = ParamUtil.getString(actionRequest, EmployeesListKeys.DEPARTMENT);
            String workLocation = ParamUtil.getString(actionRequest, EmployeesListKeys.WORK_LOCATION);
            employeeService.saveEmployee(employeeId, firstName, lastName, email, phoneNumber,
                    companyName, jobTitle, department, workLocation);

            // Redirect
            RenderURL redirectURL = actionResponse.createRedirectURL(MimeResponse.Copy.PUBLIC);
            sendRedirect(actionRequest, actionResponse, redirectURL.toString());
        } catch (Exception e) {
            if (e instanceof EmployeeValidationException) {
                SessionErrors.add(actionRequest, e.getClass(), e);
                hideDefaultErrorMessage(actionRequest);
            }
            _log.error("Error: " + e.getMessage(), e);
            actionResponse.getRenderParameters().setValue("mvcRenderCommandName", EmployeesListPortletKeys.MVC_COMMAND_EDIT);
        }
    }

    @Reference
    private EmployeeService employeeService;

    private static final Log _log = LogFactoryUtil.getLog(EditMVCActionCommand.class);
}
