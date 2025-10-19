/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;stq_STQEmployee&quot; database table.
 *
 * @author Solteq
 * @see STQEmployee
 * @generated
 */
public class STQEmployeeTable extends BaseTable<STQEmployeeTable> {

	public static final STQEmployeeTable INSTANCE = new STQEmployeeTable();

	public final Column<STQEmployeeTable, Long> employeeId = createColumn(
		"employeeId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<STQEmployeeTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> firstName = createColumn(
		"firstName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> lastName = createColumn(
		"lastName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> email = createColumn(
		"email", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> jobTitle = createColumn(
		"jobTitle", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> phoneNumber = createColumn(
		"phoneNumber", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> companyName = createColumn(
		"companyName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> department = createColumn(
		"department", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<STQEmployeeTable, String> workLocation = createColumn(
		"workLocation", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private STQEmployeeTable() {
		super("stq_STQEmployee", STQEmployeeTable::new);
	}

}