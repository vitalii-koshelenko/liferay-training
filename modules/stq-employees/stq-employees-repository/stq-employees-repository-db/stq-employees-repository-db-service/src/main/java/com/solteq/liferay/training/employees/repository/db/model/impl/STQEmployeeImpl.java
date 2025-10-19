/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.model.impl;

import com.solteq.liferay.training.employees.domain.model.Employee;

/**
 * @author Solteq
 */
public class STQEmployeeImpl extends STQEmployeeBaseImpl implements Employee {

    @Override
    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}