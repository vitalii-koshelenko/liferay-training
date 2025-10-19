/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.solteq.liferay.training.employees.repository.db.exception;

import com.liferay.portal.kernel.exception.NoSuchModelException;

/**
 * @author Solteq
 */
public class NoSuchSTQEmployeeException extends NoSuchModelException {

	public NoSuchSTQEmployeeException() {
	}

	public NoSuchSTQEmployeeException(String msg) {
		super(msg);
	}

	public NoSuchSTQEmployeeException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public NoSuchSTQEmployeeException(Throwable throwable) {
		super(throwable);
	}

}