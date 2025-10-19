package com.solteq.liferay.training.employees.web.portlet.employeelist;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys;
import jakarta.portlet.Portlet;
import org.osgi.service.component.annotations.Component;

@Component(
        property = {
                "com.liferay.portlet.display-category=" + EmployeesListPortletKeys.CATEGORY_NAME,
                "com.liferay.portlet.instanceable=false",
                "jakarta.portlet.display-name=" + EmployeesListPortletKeys.DISPLAY_NAME,
                "jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID,
                "jakarta.portlet.resource-bundle=content.Language",
                "jakarta.portlet.security-role-ref=power-user,user",
                "com.liferay.portlet.header-portlet-css=/employees-list/css/main.css",
                "com.liferay.portlet.header-portlet-javascript=/employees-list/js/main.js",
                "com.liferay.portlet.css-class-wrapper=" + EmployeesListPortletKeys.CSS_CLASS_WRAPPER,
        },
        service = Portlet.class
)
public class EmployeesListPortlet extends MVCPortlet {

}