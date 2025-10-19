package com.solteq.liferay.training.employees.repository.db.internal.upgrade.v_1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployeeTable;

public class AddCompanyIdUpgradeProcess extends UpgradeProcess {

    public static final String TYPE_LONG = "LONG";

    @Override
    protected void doUpgrade() throws Exception {
        alterTableAddColumn(
                STQEmployeeTable.INSTANCE.getTableName(),
                STQEmployeeTable.INSTANCE.companyId.getName(),
                TYPE_LONG
        );
    }

}
