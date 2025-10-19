/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service.persistence.impl;

import com.liferay.portal.kernel.dao.orm.ArgumentsResolver;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.model.BaseModel;

import com.solteq.liferay.training.employees.repository.db.model.STQEmployeeTable;
import com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeImpl;
import com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeModelImpl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

/**
 * The arguments resolver class for retrieving value from STQEmployee.
 *
 * @author Solteq
 * @generated
 */
@Component(
	property = {
		"class.name=com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeImpl",
		"table.name=stq_STQEmployee"
	},
	service = ArgumentsResolver.class
)
public class STQEmployeeModelArgumentsResolver implements ArgumentsResolver {

	@Override
	public Object[] getArguments(
		FinderPath finderPath, BaseModel<?> baseModel, boolean checkColumn,
		boolean original) {

		String[] columnNames = finderPath.getColumnNames();

		if ((columnNames == null) || (columnNames.length == 0)) {
			if (baseModel.isNew()) {
				return new Object[0];
			}

			return null;
		}

		STQEmployeeModelImpl stqEmployeeModelImpl =
			(STQEmployeeModelImpl)baseModel;

		long columnBitmask = stqEmployeeModelImpl.getColumnBitmask();

		if (!checkColumn || (columnBitmask == 0)) {
			return _getValue(stqEmployeeModelImpl, columnNames, original);
		}

		Long finderPathColumnBitmask = _finderPathColumnBitmasksCache.get(
			finderPath);

		if (finderPathColumnBitmask == null) {
			finderPathColumnBitmask = 0L;

			for (String columnName : columnNames) {
				finderPathColumnBitmask |=
					stqEmployeeModelImpl.getColumnBitmask(columnName);
			}

			_finderPathColumnBitmasksCache.put(
				finderPath, finderPathColumnBitmask);
		}

		if ((columnBitmask & finderPathColumnBitmask) != 0) {
			return _getValue(stqEmployeeModelImpl, columnNames, original);
		}

		return null;
	}

	@Override
	public String getClassName() {
		return STQEmployeeImpl.class.getName();
	}

	@Override
	public String getTableName() {
		return STQEmployeeTable.INSTANCE.getTableName();
	}

	private static Object[] _getValue(
		STQEmployeeModelImpl stqEmployeeModelImpl, String[] columnNames,
		boolean original) {

		Object[] arguments = new Object[columnNames.length];

		for (int i = 0; i < arguments.length; i++) {
			String columnName = columnNames[i];

			if (original) {
				arguments[i] = stqEmployeeModelImpl.getColumnOriginalValue(
					columnName);
			}
			else {
				arguments[i] = stqEmployeeModelImpl.getColumnValue(columnName);
			}
		}

		return arguments;
	}

	private static final Map<FinderPath, Long> _finderPathColumnBitmasksCache =
		new ConcurrentHashMap<>();

}