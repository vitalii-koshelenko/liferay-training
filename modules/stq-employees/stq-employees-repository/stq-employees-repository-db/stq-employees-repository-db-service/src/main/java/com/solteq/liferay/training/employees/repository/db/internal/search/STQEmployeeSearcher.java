package com.solteq.liferay.training.employees.repository.db.internal.search;

import com.liferay.portal.kernel.search.BaseSearcher;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import org.osgi.service.component.annotations.Component;

@Component(
	property = "model.class.name=com.solteq.liferay.training.employees.repository.db.model.STQEmployee",
	service = BaseSearcher.class
)
public class STQEmployeeSearcher extends BaseSearcher {

	@Override
	public String getClassName() {
		return _CLASS_NAME;
	}

	private static final String _CLASS_NAME = STQEmployee.class.getName();
}