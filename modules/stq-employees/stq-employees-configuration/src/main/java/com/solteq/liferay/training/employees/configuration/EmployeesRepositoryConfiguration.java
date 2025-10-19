package com.solteq.liferay.training.employees.configuration;

import aQute.bnd.annotation.metatype.Meta;
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition;

@ExtendedObjectClassDefinition(
        category = EmployeesConfigurationKeys.EMPLOYEES_CATEGORY_KEY,
        scope = ExtendedObjectClassDefinition.Scope.SYSTEM
)
@Meta.OCD(
        id = EmployeesConfigurationKeys.EMPLOYEES_REPOSITORY_CONFIGURATION_ID,
        name = EmployeesConfigurationKeys.EMPLOYEES_REPOSITORY_CONFIGURATION_NAME,
        description = EmployeesConfigurationKeys.EMPLOYEES_REPOSITORY_CONFIGURATION_DESC
)
public interface EmployeesRepositoryConfiguration {

    @Meta.AD(
            deflt = "database",
            required = false,
            type = Meta.Type.String,
            name = "employees-repository.repository-type.name",
            description = "employees-repository.repository-type.desc",
            optionValues = {"in-memory", "database", "elasticsearch", "objects"}
    )
    public String repositoryType();

}
