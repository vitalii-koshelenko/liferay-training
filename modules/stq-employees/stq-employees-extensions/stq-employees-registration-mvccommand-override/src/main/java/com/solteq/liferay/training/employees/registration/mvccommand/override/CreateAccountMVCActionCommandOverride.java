package com.solteq.liferay.training.employees.registration.mvccommand.override;

import com.liferay.login.web.constants.LoginPortletKeys;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import jakarta.portlet.ActionRequest;
import jakarta.portlet.ActionResponse;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        property = {
                "jakarta.portlet.name=" + LoginPortletKeys.CREATE_ACCOUNT,
                "jakarta.portlet.name=" + LoginPortletKeys.FAST_LOGIN,
                "jakarta.portlet.name=" + LoginPortletKeys.FORGOT_PASSWORD,
                "jakarta.portlet.name=" + LoginPortletKeys.LOGIN,
                "mvc.command.name=/login/create_account",
                "service.ranking:Integer=100"
        },
        service = MVCActionCommand.class
)
public class CreateAccountMVCActionCommandOverride extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        _log.info("Custom CreateAccountMVCActionCommandOverride START.");
        try {

            // Create Account
            String emailAddress = ParamUtil.getString(actionRequest, "emailAddress");
            String firstName = ParamUtil.getString(actionRequest, "firstName");
            String lastName = ParamUtil.getString(actionRequest, "lastName");
            String phoneNumber = ParamUtil.getString(actionRequest, "phoneNumber");
            String jobTitle = ParamUtil.getString(actionRequest, "jobTitle");
            String companyName = ParamUtil.getString(actionRequest, "companyName");
            String department = ParamUtil.getString(actionRequest, "department");
            String workLocation = ParamUtil.getString(actionRequest, "workLocation");
            Employee employee = employeeRepository.saveEmployee(
                    0L,
                    firstName,
                    lastName,
                    emailAddress,
                    phoneNumber,
                    companyName,
                    jobTitle,
                    department,
                    workLocation
            );
            long employeeId = employee.getEmployeeId();
            _log.info(String.format("Registered Employee User #%d '%s'.", employeeId, employee.getEmail()));

            // Redirect to login page
            sendRedirect(actionRequest, actionResponse, "/c/portal_login");

        } catch (Exception e) {
            String errorMsg = String.format("<b>%s</b>: %s", e.getClass().getSimpleName(), e.getMessage());
            _log.error(errorMsg, e);
            actionRequest.setAttribute("errorMsg", errorMsg);
        }
        _log.info("Custom CreateAccountMVCActionCommandOverride END.");
    }

    @Reference(target = "(repository.type=objects)")
    private EmployeeRepository employeeRepository;
    @Reference(target = "(component.name=com.liferay.login.web.internal.portlet.action.CreateAccountMVCActionCommand)")
    private MVCActionCommand originalMvcActionCommand;

    private static final Log _log  = LogFactoryUtil.getLog(CreateAccountMVCActionCommandOverride.class);
}
