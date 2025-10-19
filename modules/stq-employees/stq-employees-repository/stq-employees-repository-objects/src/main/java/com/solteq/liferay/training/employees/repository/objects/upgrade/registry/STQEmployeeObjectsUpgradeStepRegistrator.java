package com.solteq.liferay.training.employees.repository.objects.upgrade.registry;

import com.liferay.list.type.service.ListTypeDefinitionLocalService;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.object.admin.rest.resource.v1_0.ObjectFieldResource;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.solteq.liferay.training.employees.repository.objects.upgrade.v_1_0_0.EmployeeObjectPickListsUpgradeProcess;
import com.solteq.liferay.training.employees.repository.objects.upgrade.v_1_0_1.EmployeeObjectFieldsUpgradeProcess;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;


@Component(service = UpgradeStepRegistrator.class)
public class STQEmployeeObjectsUpgradeStepRegistrator implements UpgradeStepRegistrator {

    @Override
    public void register(Registry registry) {
        registry.register("0.0.0", "1.0.0",
                new EmployeeObjectPickListsUpgradeProcess(listTypeDefinitionLocalService, listTypeEntryLocalService));
        registry.register("1.0.0", "1.0.1",
                new EmployeeObjectFieldsUpgradeProcess(objectFieldResourceFactory));
    }

    @Reference
    private ListTypeEntryLocalService listTypeEntryLocalService;
    @Reference
    private ListTypeDefinitionLocalService listTypeDefinitionLocalService;
    @Reference
    private ObjectFieldResource.Factory objectFieldResourceFactory;
}