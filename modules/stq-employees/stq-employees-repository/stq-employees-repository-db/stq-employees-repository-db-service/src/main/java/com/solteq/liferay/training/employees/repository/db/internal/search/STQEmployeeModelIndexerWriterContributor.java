package com.solteq.liferay.training.employees.repository.db.internal.search;

import com.liferay.portal.search.batch.BatchIndexingActionable;
import com.liferay.portal.search.batch.DynamicQueryBatchIndexingActionableFactory;
import com.liferay.portal.search.spi.model.index.contributor.ModelIndexerWriterContributor;
import com.liferay.portal.search.spi.model.index.contributor.helper.ModelIndexerWriterDocumentHelper;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import com.solteq.liferay.training.employees.repository.db.service.STQEmployeeLocalService;

public class STQEmployeeModelIndexerWriterContributor implements ModelIndexerWriterContributor<STQEmployee> {

	public STQEmployeeModelIndexerWriterContributor(STQEmployeeLocalService stqEmployeeLocalService,
                        DynamicQueryBatchIndexingActionableFactory dqIndexingFactory) {
		this.stqEmployeeLocalService = stqEmployeeLocalService;
		this.dqIndexingFactory = dqIndexingFactory;
	}

	@Override
	public void customize(BatchIndexingActionable actionable, ModelIndexerWriterDocumentHelper helper) {
		actionable.setPerformActionMethod((STQEmployee employee) -> actionable.addDocuments(helper.getDocument(employee)));
	}

	@Override
	public BatchIndexingActionable getBatchIndexingActionable() {
		return dqIndexingFactory.getBatchIndexingActionable(stqEmployeeLocalService.getIndexableActionableDynamicQuery());
	}

	@Override
	public long getCompanyId(STQEmployee employee) {
		return employee.getCompanyId();
	}

	private final STQEmployeeLocalService stqEmployeeLocalService;
	private final DynamicQueryBatchIndexingActionableFactory dqIndexingFactory;
}