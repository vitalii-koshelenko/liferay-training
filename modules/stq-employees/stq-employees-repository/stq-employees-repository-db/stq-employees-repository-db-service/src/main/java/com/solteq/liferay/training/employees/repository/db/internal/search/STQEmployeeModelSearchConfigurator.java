package com.solteq.liferay.training.employees.repository.db.internal.search;

import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.registrar.ModelSearchConfigurator;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import com.solteq.liferay.training.employees.repository.db.service.STQEmployeeLocalService;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = ModelSearchConfigurator.class)
public class STQEmployeeModelSearchConfigurator implements ModelSearchConfigurator<STQEmployee> {

	@Override
	public String getClassName() {
		return STQEmployee.class.getName();
	}

	@Override
	public ModelIndexerWriterContributor<STQEmployee> getModelIndexerWriterContributor() {
		return modelIndexWriterContributor;
    }

	@Activate
	protected void activate() {
		modelIndexWriterContributor = new STQEmployeeModelIndexerWriterContributor(stqEmployeeLocalService, dqIndexingFactory);
	}

	@Reference
    private STQEmployeeLocalService stqEmployeeLocalService;
	@Reference
	private DynamicQueryBatchIndexingActionableFactory dqIndexingFactory;

    private ModelIndexerWriterContributor<STQEmployee> modelIndexWriterContributor;

}