/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service;

import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.module.service.Snapshot;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;

import java.io.Serializable;

import java.util.List;

/**
 * Provides the local service utility for STQEmployee. This utility wraps
 * <code>com.solteq.liferay.training.employees.repository.db.service.impl.STQEmployeeLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Solteq
 * @see STQEmployeeLocalService
 * @generated
 */
public class STQEmployeeLocalServiceUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.solteq.liferay.training.employees.repository.db.service.impl.STQEmployeeLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the stq employee to the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect STQEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param stqEmployee the stq employee
	 * @return the stq employee that was added
	 */
	public static STQEmployee addSTQEmployee(STQEmployee stqEmployee) {
		return getService().addSTQEmployee(stqEmployee);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel createPersistedModel(
			Serializable primaryKeyObj)
		throws PortalException {

		return getService().createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new stq employee with the primary key. Does not add the stq employee to the database.
	 *
	 * @param employeeId the primary key for the new stq employee
	 * @return the new stq employee
	 */
	public static STQEmployee createSTQEmployee(long employeeId) {
		return getService().createSTQEmployee(employeeId);
	}

	public static void deleteEmployee(long employeeId) {
		getService().deleteEmployee(employeeId);
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel deletePersistedModel(
			PersistedModel persistedModel)
		throws PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	/**
	 * Deletes the stq employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect STQEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee that was removed
	 * @throws PortalException if a stq employee with the primary key could not be found
	 */
	public static STQEmployee deleteSTQEmployee(long employeeId)
		throws PortalException {

		return getService().deleteSTQEmployee(employeeId);
	}

	/**
	 * Deletes the stq employee from the database. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect STQEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param stqEmployee the stq employee
	 * @return the stq employee that was removed
	 */
	public static STQEmployee deleteSTQEmployee(STQEmployee stqEmployee) {
		return getService().deleteSTQEmployee(stqEmployee);
	}

	public static <T> T dslQuery(DSLQuery dslQuery) {
		return getService().dslQuery(dslQuery);
	}

	public static int dslQueryCount(DSLQuery dslQuery) {
		return getService().dslQueryCount(dslQuery);
	}

	public static DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> List<T> dynamicQuery(DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static int fetchEmployeeCount(
		com.solteq.liferay.training.employees.domain.filter.EmployeeFilter
			filter) {

		return getService().fetchEmployeeCount(filter);
	}

	public static List<STQEmployee> fetchEmployees(
		int start, int end,
		com.solteq.liferay.training.employees.domain.filter.EmployeeFilter
			filter) {

		return getService().fetchEmployees(start, end, filter);
	}

	public static STQEmployee fetchSTQEmployee(long employeeId) {
		return getService().fetchSTQEmployee(employeeId);
	}

	public static List<STQEmployee> findByCompanyName(String companyName) {
		return getService().findByCompanyName(companyName);
	}

	public static List<STQEmployee> findByDepartment(String department) {
		return getService().findByDepartment(department);
	}

	public static STQEmployee findByEmail(String email) {
		return getService().findByEmail(email);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	public static PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the stq employee with the primary key.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee
	 * @throws PortalException if a stq employee with the primary key could not be found
	 */
	public static STQEmployee getSTQEmployee(long employeeId)
		throws PortalException {

		return getService().getSTQEmployee(employeeId);
	}

	/**
	 * Returns a range of all the stq employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @return the range of stq employees
	 */
	public static List<STQEmployee> getSTQEmployees(int start, int end) {
		return getService().getSTQEmployees(start, end);
	}

	/**
	 * Returns the number of stq employees.
	 *
	 * @return the number of stq employees
	 */
	public static int getSTQEmployeesCount() {
		return getService().getSTQEmployeesCount();
	}

	public static STQEmployee saveEmployee(
		long employeeId, String firstName, String lastName, String email,
		String phoneNumber, String companyName, String jobTitle,
		String department, String workLocation) {

		return getService().saveEmployee(
			employeeId, firstName, lastName, email, phoneNumber, companyName,
			jobTitle, department, workLocation);
	}

	/**
	 * Updates the stq employee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * <p>
	 * <strong>Important:</strong> Inspect STQEmployeeLocalServiceImpl for overloaded versions of the method. If provided, use these entry points to the API, as the implementation logic may require the additional parameters defined there.
	 * </p>
	 *
	 * @param stqEmployee the stq employee
	 * @return the stq employee that was updated
	 */
	public static STQEmployee updateSTQEmployee(STQEmployee stqEmployee) {
		return getService().updateSTQEmployee(stqEmployee);
	}

	public static STQEmployeeLocalService getService() {
		return _serviceSnapshot.get();
	}

	private static final Snapshot<STQEmployeeLocalService> _serviceSnapshot =
		new Snapshot<>(
			STQEmployeeLocalServiceUtil.class, STQEmployeeLocalService.class);

}