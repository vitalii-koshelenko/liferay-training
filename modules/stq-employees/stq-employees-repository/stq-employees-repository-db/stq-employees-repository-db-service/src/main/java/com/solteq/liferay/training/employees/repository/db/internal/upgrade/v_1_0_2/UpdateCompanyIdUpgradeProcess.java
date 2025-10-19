package com.solteq.liferay.training.employees.repository.db.internal.upgrade.v_1_0_2;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import com.solteq.liferay.training.employees.repository.db.service.STQEmployeeLocalService;

import java.util.List;

public class UpdateCompanyIdUpgradeProcess extends UpgradeProcess {

    private final STQEmployeeLocalService stqEmployeeLocalService;

    public UpdateCompanyIdUpgradeProcess(STQEmployeeLocalService stqEmployeeLocalService) {
        this.stqEmployeeLocalService = stqEmployeeLocalService;
    }

    @Override
    protected void doUpgrade() throws Exception {
        List<STQEmployee> stqEmployees = stqEmployeeLocalService.getSTQEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        long defaultCompanyId = LiferayUtil.getDefaultCompanyId();
        for (STQEmployee employee : stqEmployees) {
            employee.setCompanyId(defaultCompanyId);
            stqEmployeeLocalService.updateSTQEmployee(employee);
            _log.info(String.format("CompanyId set for employee #%d %s.", employee.getEmployeeId(), employee.getFullName()));
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(UpdateCompanyIdUpgradeProcess.class);
}

