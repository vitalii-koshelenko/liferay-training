package com.solteq.liferay.training.employees.configuration.category;

import com.liferay.configuration.admin.category.ConfigurationCategory;
import com.solteq.liferay.training.employees.configuration.EmployeesConfigurationKeys;
import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationCategory.class)
public class EmployeesConfigurationCategory implements ConfigurationCategory {

    @Override
    public String getCategorySection() {
        return EmployeesConfigurationKeys.CATEGORY_SECTION;
    }

    @Override
    public String getCategoryKey() {
        return EmployeesConfigurationKeys.EMPLOYEES_CATEGORY_KEY;
    }

    @Override
    public String getCategoryIcon() {
        return EmployeesConfigurationKeys.EMPLOYEES_CATEGORY_ICON;
    }

}
