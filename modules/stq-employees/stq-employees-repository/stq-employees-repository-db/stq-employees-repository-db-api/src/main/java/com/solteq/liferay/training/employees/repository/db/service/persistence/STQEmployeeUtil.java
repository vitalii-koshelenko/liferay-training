/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service.persistence;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence utility for the stq employee service. This utility wraps <code>com.solteq.liferay.training.employees.repository.db.service.persistence.impl.STQEmployeePersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Solteq
 * @see STQEmployeePersistence
 * @generated
 */
public class STQEmployeeUtil {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(STQEmployee stqEmployee) {
		getPersistence().clearCache(stqEmployee);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, STQEmployee> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<STQEmployee> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<STQEmployee> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<STQEmployee> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static STQEmployee update(STQEmployee stqEmployee) {
		return getPersistence().update(stqEmployee);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static STQEmployee update(
		STQEmployee stqEmployee, ServiceContext serviceContext) {

		return getPersistence().update(stqEmployee, serviceContext);
	}

	/**
	 * Returns the stq employee where email = &#63; or throws a <code>NoSuchSTQEmployeeException</code> if it could not be found.
	 *
	 * @param email the email
	 * @return the matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public static STQEmployee findByEmail(String email)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByEmail(email);
	}

	/**
	 * Returns the stq employee where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param email the email
	 * @return the matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public static STQEmployee fetchByEmail(String email) {
		return getPersistence().fetchByEmail(email);
	}

	/**
	 * Returns the stq employee where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param email the email
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public static STQEmployee fetchByEmail(
		String email, boolean useFinderCache) {

		return getPersistence().fetchByEmail(email, useFinderCache);
	}

	/**
	 * Removes the stq employee where email = &#63; from the database.
	 *
	 * @param email the email
	 * @return the stq employee that was removed
	 */
	public static STQEmployee removeByEmail(String email)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().removeByEmail(email);
	}

	/**
	 * Returns the number of stq employees where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching stq employees
	 */
	public static int countByEmail(String email) {
		return getPersistence().countByEmail(email);
	}

	/**
	 * Returns all the stq employees where department = &#63;.
	 *
	 * @param department the department
	 * @return the matching stq employees
	 */
	public static List<STQEmployee> findByDepartment(String department) {
		return getPersistence().findByDepartment(department);
	}

	/**
	 * Returns a range of all the stq employees where department = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param department the department
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @return the range of matching stq employees
	 */
	public static List<STQEmployee> findByDepartment(
		String department, int start, int end) {

		return getPersistence().findByDepartment(department, start, end);
	}

	/**
	 * Returns an ordered range of all the stq employees where department = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param department the department
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stq employees
	 */
	public static List<STQEmployee> findByDepartment(
		String department, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().findByDepartment(
			department, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the stq employees where department = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param department the department
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stq employees
	 */
	public static List<STQEmployee> findByDepartment(
		String department, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByDepartment(
			department, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public static STQEmployee findByDepartment_First(
			String department, OrderByComparator<STQEmployee> orderByComparator)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByDepartment_First(
			department, orderByComparator);
	}

	/**
	 * Returns the first stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public static STQEmployee fetchByDepartment_First(
		String department, OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().fetchByDepartment_First(
			department, orderByComparator);
	}

	/**
	 * Returns the last stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public static STQEmployee findByDepartment_Last(
			String department, OrderByComparator<STQEmployee> orderByComparator)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByDepartment_Last(
			department, orderByComparator);
	}

	/**
	 * Returns the last stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public static STQEmployee fetchByDepartment_Last(
		String department, OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().fetchByDepartment_Last(
			department, orderByComparator);
	}

	/**
	 * Returns the stq employees before and after the current stq employee in the ordered set where department = &#63;.
	 *
	 * @param employeeId the primary key of the current stq employee
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public static STQEmployee[] findByDepartment_PrevAndNext(
			long employeeId, String department,
			OrderByComparator<STQEmployee> orderByComparator)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByDepartment_PrevAndNext(
			employeeId, department, orderByComparator);
	}

	/**
	 * Removes all the stq employees where department = &#63; from the database.
	 *
	 * @param department the department
	 */
	public static void removeByDepartment(String department) {
		getPersistence().removeByDepartment(department);
	}

	/**
	 * Returns the number of stq employees where department = &#63;.
	 *
	 * @param department the department
	 * @return the number of matching stq employees
	 */
	public static int countByDepartment(String department) {
		return getPersistence().countByDepartment(department);
	}

	/**
	 * Returns all the stq employees where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the matching stq employees
	 */
	public static List<STQEmployee> findByCompanyName(String companyName) {
		return getPersistence().findByCompanyName(companyName);
	}

	/**
	 * Returns a range of all the stq employees where companyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param companyName the company name
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @return the range of matching stq employees
	 */
	public static List<STQEmployee> findByCompanyName(
		String companyName, int start, int end) {

		return getPersistence().findByCompanyName(companyName, start, end);
	}

	/**
	 * Returns an ordered range of all the stq employees where companyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param companyName the company name
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching stq employees
	 */
	public static List<STQEmployee> findByCompanyName(
		String companyName, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().findByCompanyName(
			companyName, start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the stq employees where companyName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param companyName the company name
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of matching stq employees
	 */
	public static List<STQEmployee> findByCompanyName(
		String companyName, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findByCompanyName(
			companyName, start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Returns the first stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public static STQEmployee findByCompanyName_First(
			String companyName,
			OrderByComparator<STQEmployee> orderByComparator)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByCompanyName_First(
			companyName, orderByComparator);
	}

	/**
	 * Returns the first stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public static STQEmployee fetchByCompanyName_First(
		String companyName, OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().fetchByCompanyName_First(
			companyName, orderByComparator);
	}

	/**
	 * Returns the last stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public static STQEmployee findByCompanyName_Last(
			String companyName,
			OrderByComparator<STQEmployee> orderByComparator)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByCompanyName_Last(
			companyName, orderByComparator);
	}

	/**
	 * Returns the last stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public static STQEmployee fetchByCompanyName_Last(
		String companyName, OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().fetchByCompanyName_Last(
			companyName, orderByComparator);
	}

	/**
	 * Returns the stq employees before and after the current stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param employeeId the primary key of the current stq employee
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public static STQEmployee[] findByCompanyName_PrevAndNext(
			long employeeId, String companyName,
			OrderByComparator<STQEmployee> orderByComparator)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByCompanyName_PrevAndNext(
			employeeId, companyName, orderByComparator);
	}

	/**
	 * Removes all the stq employees where companyName = &#63; from the database.
	 *
	 * @param companyName the company name
	 */
	public static void removeByCompanyName(String companyName) {
		getPersistence().removeByCompanyName(companyName);
	}

	/**
	 * Returns the number of stq employees where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the number of matching stq employees
	 */
	public static int countByCompanyName(String companyName) {
		return getPersistence().countByCompanyName(companyName);
	}

	/**
	 * Caches the stq employee in the entity cache if it is enabled.
	 *
	 * @param stqEmployee the stq employee
	 */
	public static void cacheResult(STQEmployee stqEmployee) {
		getPersistence().cacheResult(stqEmployee);
	}

	/**
	 * Caches the stq employees in the entity cache if it is enabled.
	 *
	 * @param stqEmployees the stq employees
	 */
	public static void cacheResult(List<STQEmployee> stqEmployees) {
		getPersistence().cacheResult(stqEmployees);
	}

	/**
	 * Creates a new stq employee with the primary key. Does not add the stq employee to the database.
	 *
	 * @param employeeId the primary key for the new stq employee
	 * @return the new stq employee
	 */
	public static STQEmployee create(long employeeId) {
		return getPersistence().create(employeeId);
	}

	/**
	 * Removes the stq employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee that was removed
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public static STQEmployee remove(long employeeId)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().remove(employeeId);
	}

	public static STQEmployee updateImpl(STQEmployee stqEmployee) {
		return getPersistence().updateImpl(stqEmployee);
	}

	/**
	 * Returns the stq employee with the primary key or throws a <code>NoSuchSTQEmployeeException</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public static STQEmployee findByPrimaryKey(long employeeId)
		throws com.solteq.liferay.training.employees.repository.db.exception.
			NoSuchSTQEmployeeException {

		return getPersistence().findByPrimaryKey(employeeId);
	}

	/**
	 * Returns the stq employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee, or <code>null</code> if a stq employee with the primary key could not be found
	 */
	public static STQEmployee fetchByPrimaryKey(long employeeId) {
		return getPersistence().fetchByPrimaryKey(employeeId);
	}

	/**
	 * Returns all the stq employees.
	 *
	 * @return the stq employees
	 */
	public static List<STQEmployee> findAll() {
		return getPersistence().findAll();
	}

	/**
	 * Returns a range of all the stq employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @return the range of stq employees
	 */
	public static List<STQEmployee> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	 * Returns an ordered range of all the stq employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of stq employees
	 */
	public static List<STQEmployee> findAll(
		int start, int end, OrderByComparator<STQEmployee> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	 * Returns an ordered range of all the stq employees.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent, then the query will include the default ORDER BY logic from <code>STQEmployeeModelImpl</code>.
	 * </p>
	 *
	 * @param start the lower bound of the range of stq employees
	 * @param end the upper bound of the range of stq employees (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of stq employees
	 */
	public static List<STQEmployee> findAll(
		int start, int end, OrderByComparator<STQEmployee> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the stq employees from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of stq employees.
	 *
	 * @return the number of stq employees
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static STQEmployeePersistence getPersistence() {
		return _persistence;
	}

	public static void setPersistence(STQEmployeePersistence persistence) {
		_persistence = persistence;
	}

	private static volatile STQEmployeePersistence _persistence;

}