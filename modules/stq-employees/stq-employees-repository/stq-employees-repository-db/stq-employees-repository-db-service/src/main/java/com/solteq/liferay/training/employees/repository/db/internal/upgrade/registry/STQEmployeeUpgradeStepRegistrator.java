package com.solteq.liferay.training.employees.repository.db.internal.upgrade.registry;

import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import com.solteq.liferay.training.employees.repository.db.internal.upgrade.v_1_0_1.AddCompanyIdUpgradeProcess;
import com.solteq.liferay.training.employees.repository.db.internal.upgrade.v_1_0_2.UpdateCompanyIdUpgradeProcess;
import com.solteq.liferay.training.employees.repository.db.internal.upgrade.v_1_0_3.ImportEmployeesUpdateProcess;
import com.solteq.liferay.training.employees.repository.db.service.STQEmployeeLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = UpgradeStepRegistrator.class)
public class STQEmployeeUpgradeStepRegistrator implements UpgradeStepRegistrator {

    @Override
    public void register(Registry registry) {
        registry.register("1.0.0", "1.0.1", new AddCompanyIdUpgradeProcess());
        registry.register("1.0.1", "1.0.2", new UpdateCompanyIdUpgradeProcess(stqEmployeeLocalService));
        registry.register("1.0.2", "1.0.3", new ImportEmployeesUpdateProcess(inMemoryEmployeeRepository, databaseEmployeeRepository));
    }

    @Reference(target = "(repository.type=in-memory)")
    private EmployeeRepository inMemoryEmployeeRepository;
    @Reference(target = "(repository.type=database)")
    private EmployeeRepository databaseEmployeeRepository;
    @Reference
    private STQEmployeeLocalService stqEmployeeLocalService;
}
