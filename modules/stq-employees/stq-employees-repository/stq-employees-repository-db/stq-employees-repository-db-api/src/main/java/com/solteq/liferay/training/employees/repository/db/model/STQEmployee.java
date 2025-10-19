/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the STQEmployee service. Represents a row in the &quot;stq_STQEmployee&quot; database table, with each column mapped to a property of this class.
 *
 * @author Solteq
 * @see STQEmployeeModel
 * @generated
 */
@ImplementationClassName(
	"com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeImpl"
)
@ProviderType
public interface STQEmployee extends PersistedModel, STQEmployeeModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<STQEmployee, Long> EMPLOYEE_ID_ACCESSOR =
		new Accessor<STQEmployee, Long>() {

			@Override
			public Long get(STQEmployee stqEmployee) {
				return stqEmployee.getEmployeeId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<STQEmployee> getTypeClass() {
				return STQEmployee.class;
			}

		};

	public String getFullName();

}