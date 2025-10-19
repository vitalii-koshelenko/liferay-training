/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service;

import com.liferay.portal.kernel.service.ServiceWrapper;
import com.liferay.portal.kernel.service.persistence.BasePersistence;

/**
 * Provides a wrapper for {@link STQEmployeeLocalService}.
 *
 * @author Solteq
 * @see STQEmployeeLocalService
 * @generated
 */
public class STQEmployeeLocalServiceWrapper
	implements ServiceWrapper<STQEmployeeLocalService>,
			   STQEmployeeLocalService {

	public STQEmployeeLocalServiceWrapper() {
		this(null);
	}

	public STQEmployeeLocalServiceWrapper(
		STQEmployeeLocalService stqEmployeeLocalService) {

		_stqEmployeeLocalService = stqEmployeeLocalService;
	}

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
	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		addSTQEmployee(
			com.solteq.liferay.training.employees.repository.db.model.
				STQEmployee stqEmployee) {

		return _stqEmployeeLocalService.addSTQEmployee(stqEmployee);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel createPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stqEmployeeLocalService.createPersistedModel(primaryKeyObj);
	}

	/**
	 * Creates a new stq employee with the primary key. Does not add the stq employee to the database.
	 *
	 * @param employeeId the primary key for the new stq employee
	 * @return the new stq employee
	 */
	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		createSTQEmployee(long employeeId) {

		return _stqEmployeeLocalService.createSTQEmployee(employeeId);
	}

	@Override
	public void deleteEmployee(long employeeId) {
		_stqEmployeeLocalService.deleteEmployee(employeeId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stqEmployeeLocalService.deletePersistedModel(persistedModel);
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
	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
			deleteSTQEmployee(long employeeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stqEmployeeLocalService.deleteSTQEmployee(employeeId);
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
	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		deleteSTQEmployee(
			com.solteq.liferay.training.employees.repository.db.model.
				STQEmployee stqEmployee) {

		return _stqEmployeeLocalService.deleteSTQEmployee(stqEmployee);
	}

	@Override
	public <T> T dslQuery(com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {
		return _stqEmployeeLocalService.dslQuery(dslQuery);
	}

	@Override
	public int dslQueryCount(
		com.liferay.petra.sql.dsl.query.DSLQuery dslQuery) {

		return _stqEmployeeLocalService.dslQueryCount(dslQuery);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _stqEmployeeLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _stqEmployeeLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _stqEmployeeLocalService.dynamicQuery(dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _stqEmployeeLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _stqEmployeeLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _stqEmployeeLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public int fetchEmployeeCount(
		com.solteq.liferay.training.employees.domain.filter.EmployeeFilter
			filter) {

		return _stqEmployeeLocalService.fetchEmployeeCount(filter);
	}

	@Override
	public java.util.List
		<com.solteq.liferay.training.employees.repository.db.model.STQEmployee>
			fetchEmployees(
				int start, int end,
				com.solteq.liferay.training.employees.domain.filter.
					EmployeeFilter filter) {

		return _stqEmployeeLocalService.fetchEmployees(start, end, filter);
	}

	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		fetchSTQEmployee(long employeeId) {

		return _stqEmployeeLocalService.fetchSTQEmployee(employeeId);
	}

	@Override
	public java.util.List
		<com.solteq.liferay.training.employees.repository.db.model.STQEmployee>
			findByCompanyName(String companyName) {

		return _stqEmployeeLocalService.findByCompanyName(companyName);
	}

	@Override
	public java.util.List
		<com.solteq.liferay.training.employees.repository.db.model.STQEmployee>
			findByDepartment(String department) {

		return _stqEmployeeLocalService.findByDepartment(department);
	}

	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		findByEmail(String email) {

		return _stqEmployeeLocalService.findByEmail(email);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _stqEmployeeLocalService.getActionableDynamicQuery();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _stqEmployeeLocalService.getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _stqEmployeeLocalService.getOSGiServiceIdentifier();
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stqEmployeeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Returns the stq employee with the primary key.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee
	 * @throws PortalException if a stq employee with the primary key could not be found
	 */
	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
			getSTQEmployee(long employeeId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _stqEmployeeLocalService.getSTQEmployee(employeeId);
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
	@Override
	public java.util.List
		<com.solteq.liferay.training.employees.repository.db.model.STQEmployee>
			getSTQEmployees(int start, int end) {

		return _stqEmployeeLocalService.getSTQEmployees(start, end);
	}

	/**
	 * Returns the number of stq employees.
	 *
	 * @return the number of stq employees
	 */
	@Override
	public int getSTQEmployeesCount() {
		return _stqEmployeeLocalService.getSTQEmployeesCount();
	}

	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		saveEmployee(
			long employeeId, String firstName, String lastName, String email,
			String phoneNumber, String companyName, String jobTitle,
			String department, String workLocation) {

		return _stqEmployeeLocalService.saveEmployee(
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
	@Override
	public com.solteq.liferay.training.employees.repository.db.model.STQEmployee
		updateSTQEmployee(
			com.solteq.liferay.training.employees.repository.db.model.
				STQEmployee stqEmployee) {

		return _stqEmployeeLocalService.updateSTQEmployee(stqEmployee);
	}

	@Override
	public BasePersistence<?> getBasePersistence() {
		return _stqEmployeeLocalService.getBasePersistence();
	}

	@Override
	public STQEmployeeLocalService getWrappedService() {
		return _stqEmployeeLocalService;
	}

	@Override
	public void setWrappedService(
		STQEmployeeLocalService stqEmployeeLocalService) {

		_stqEmployeeLocalService = stqEmployeeLocalService;
	}

	private STQEmployeeLocalService _stqEmployeeLocalService;

}