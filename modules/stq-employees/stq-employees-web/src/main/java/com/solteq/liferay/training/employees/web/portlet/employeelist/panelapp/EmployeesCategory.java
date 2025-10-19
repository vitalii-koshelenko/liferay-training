package com.solteq.liferay.training.employees.web.portlet.employeelist.panelapp;

import com.liferay.application.list.BasePanelCategory;
import com.liferay.application.list.PanelCategory;
import com.liferay.application.list.constants.PanelCategoryKeys;
import com.liferay.portal.kernel.language.Language;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.service.permission.PortalPermissionUtil;
import com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Locale;

@Component(
        property = {
                "panel.category.key=" + PanelCategoryKeys.CONTROL_PANEL,
                "panel.category.order:Integer=100"
        },
        service = PanelCategory.class
)
public class EmployeesCategory extends BasePanelCategory {

    @Override
    public String getKey() {
        return EmployeesListPortletKeys.PANEL_CATEGORY_NAME;
    }

    @Override
    public String getLabel(Locale locale) {
        return _language.get(locale, EmployeesListPortletKeys.PANEL_CATEGORY_NAME);
    }

    @Override
    public boolean isShow(PermissionChecker permissionChecker, Group group) {
        return PortalPermissionUtil.contains(permissionChecker, ActionKeys.VIEW_CONTROL_PANEL);
    }

    @Reference
    private Language _language;
}
