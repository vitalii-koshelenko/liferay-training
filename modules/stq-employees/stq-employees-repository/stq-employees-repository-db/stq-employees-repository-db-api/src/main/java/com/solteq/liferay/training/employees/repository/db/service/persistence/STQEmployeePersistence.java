/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service.persistence;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.solteq.liferay.training.employees.repository.db.exception.NoSuchSTQEmployeeException;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the stq employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Solteq
 * @see STQEmployeeUtil
 * @generated
 */
@ProviderType
public interface STQEmployeePersistence extends BasePersistence<STQEmployee> {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link STQEmployeeUtil} to access the stq employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the stq employee where email = &#63; or throws a <code>NoSuchSTQEmployeeException</code> if it could not be found.
	 *
	 * @param email the email
	 * @return the matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public STQEmployee findByEmail(String email)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the stq employee where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param email the email
	 * @return the matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public STQEmployee fetchByEmail(String email);

	/**
	 * Returns the stq employee where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param email the email
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public STQEmployee fetchByEmail(String email, boolean useFinderCache);

	/**
	 * Removes the stq employee where email = &#63; from the database.
	 *
	 * @param email the email
	 * @return the stq employee that was removed
	 */
	public STQEmployee removeByEmail(String email)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the number of stq employees where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching stq employees
	 */
	public int countByEmail(String email);

	/**
	 * Returns all the stq employees where department = &#63;.
	 *
	 * @param department the department
	 * @return the matching stq employees
	 */
	public java.util.List<STQEmployee> findByDepartment(String department);

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
	public java.util.List<STQEmployee> findByDepartment(
		String department, int start, int end);

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
	public java.util.List<STQEmployee> findByDepartment(
		String department, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

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
	public java.util.List<STQEmployee> findByDepartment(
		String department, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public STQEmployee findByDepartment_First(
			String department,
			com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
				orderByComparator)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the first stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public STQEmployee fetchByDepartment_First(
		String department,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

	/**
	 * Returns the last stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public STQEmployee findByDepartment_Last(
			String department,
			com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
				orderByComparator)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the last stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public STQEmployee fetchByDepartment_Last(
		String department,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

	/**
	 * Returns the stq employees before and after the current stq employee in the ordered set where department = &#63;.
	 *
	 * @param employeeId the primary key of the current stq employee
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public STQEmployee[] findByDepartment_PrevAndNext(
			long employeeId, String department,
			com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
				orderByComparator)
		throws NoSuchSTQEmployeeException;

	/**
	 * Removes all the stq employees where department = &#63; from the database.
	 *
	 * @param department the department
	 */
	public void removeByDepartment(String department);

	/**
	 * Returns the number of stq employees where department = &#63;.
	 *
	 * @param department the department
	 * @return the number of matching stq employees
	 */
	public int countByDepartment(String department);

	/**
	 * Returns all the stq employees where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the matching stq employees
	 */
	public java.util.List<STQEmployee> findByCompanyName(String companyName);

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
	public java.util.List<STQEmployee> findByCompanyName(
		String companyName, int start, int end);

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
	public java.util.List<STQEmployee> findByCompanyName(
		String companyName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

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
	public java.util.List<STQEmployee> findByCompanyName(
		String companyName, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Returns the first stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public STQEmployee findByCompanyName_First(
			String companyName,
			com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
				orderByComparator)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the first stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public STQEmployee fetchByCompanyName_First(
		String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

	/**
	 * Returns the last stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	public STQEmployee findByCompanyName_Last(
			String companyName,
			com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
				orderByComparator)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the last stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	public STQEmployee fetchByCompanyName_Last(
		String companyName,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

	/**
	 * Returns the stq employees before and after the current stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param employeeId the primary key of the current stq employee
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public STQEmployee[] findByCompanyName_PrevAndNext(
			long employeeId, String companyName,
			com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
				orderByComparator)
		throws NoSuchSTQEmployeeException;

	/**
	 * Removes all the stq employees where companyName = &#63; from the database.
	 *
	 * @param companyName the company name
	 */
	public void removeByCompanyName(String companyName);

	/**
	 * Returns the number of stq employees where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the number of matching stq employees
	 */
	public int countByCompanyName(String companyName);

	/**
	 * Caches the stq employee in the entity cache if it is enabled.
	 *
	 * @param stqEmployee the stq employee
	 */
	public void cacheResult(STQEmployee stqEmployee);

	/**
	 * Caches the stq employees in the entity cache if it is enabled.
	 *
	 * @param stqEmployees the stq employees
	 */
	public void cacheResult(java.util.List<STQEmployee> stqEmployees);

	/**
	 * Creates a new stq employee with the primary key. Does not add the stq employee to the database.
	 *
	 * @param employeeId the primary key for the new stq employee
	 * @return the new stq employee
	 */
	public STQEmployee create(long employeeId);

	/**
	 * Removes the stq employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee that was removed
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public STQEmployee remove(long employeeId)
		throws NoSuchSTQEmployeeException;

	public STQEmployee updateImpl(STQEmployee stqEmployee);

	/**
	 * Returns the stq employee with the primary key or throws a <code>NoSuchSTQEmployeeException</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	public STQEmployee findByPrimaryKey(long employeeId)
		throws NoSuchSTQEmployeeException;

	/**
	 * Returns the stq employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee, or <code>null</code> if a stq employee with the primary key could not be found
	 */
	public STQEmployee fetchByPrimaryKey(long employeeId);

	/**
	 * Returns all the stq employees.
	 *
	 * @return the stq employees
	 */
	public java.util.List<STQEmployee> findAll();

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
	public java.util.List<STQEmployee> findAll(int start, int end);

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
	public java.util.List<STQEmployee> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator);

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
	public java.util.List<STQEmployee> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<STQEmployee>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the stq employees from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of stq employees.
	 *
	 * @return the number of stq employees
	 */
	public int countAll();

}