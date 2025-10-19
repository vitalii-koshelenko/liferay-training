/* (c) 2024 Solteq. All rights reserved. */
package com.solteq.liferay.training.employees.repository.db.internal.search;

import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.search.spi.index.configuration.contributor.CompanyIndexConfigurationContributor;
import com.liferay.portal.search.spi.index.configuration.contributor.helper.MappingsHelper;
import com.liferay.portal.search.spi.index.configuration.contributor.helper.SettingsHelper;
import org.osgi.service.component.annotations.Component;

@Component(immediate = true, service = CompanyIndexConfigurationContributor.class)
public class STQEmployeeCompanyIndexConfigurationContributor implements CompanyIndexConfigurationContributor {

    @Override
    public void contributeMappings(long companyId, MappingsHelper mappingsHelper) {
        mappingsHelper.putMappings(StringUtil.read(getClass(), "dependencies/additional-type-mappings.json"));
    }

    @Override
    public void contributeSettings(long companyId, SettingsHelper settingsHelper) {}
}