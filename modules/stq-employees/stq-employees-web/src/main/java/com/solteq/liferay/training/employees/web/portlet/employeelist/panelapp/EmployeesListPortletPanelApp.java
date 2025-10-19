package com.solteq.liferay.training.employees.web.portlet.employeelist.panelapp;

import com.liferay.application.list.BasePanelApp;
import com.liferay.application.list.PanelApp;
import com.liferay.portal.kernel.model.Portlet;
import com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {
                "panel.app.order:Integer=200",
                "panel.category.key=" + EmployeesListPortletKeys.PANEL_CATEGORY_NAME,
                "service.ranking:Integer=1",
        },
        service = {PanelApp.class}
)
public class EmployeesListPortletPanelApp extends BasePanelApp {

    @Override
    public Portlet getPortlet() {
        return portlet;
    }

    @Override
    public String getPortletId() {
        return EmployeesListPortletKeys.PORTLET_ID;
    }

    @Reference(target = "(jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID + ")")
    private Portlet portlet;

}