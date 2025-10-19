/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service.persistence.impl;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.sanitizer.Sanitizer;
import com.liferay.portal.kernel.sanitizer.SanitizerException;
import com.liferay.portal.kernel.sanitizer.SanitizerUtil;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.security.auth.PrincipalThreadLocal;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.ContentTypes;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.ProxyUtil;

import com.solteq.liferay.training.employees.repository.db.exception.NoSuchSTQEmployeeException;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployeeTable;
import com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeImpl;
import com.solteq.liferay.training.employees.repository.db.model.impl.STQEmployeeModelImpl;
import com.solteq.liferay.training.employees.repository.db.service.persistence.STQEmployeePersistence;
import com.solteq.liferay.training.employees.repository.db.service.persistence.STQEmployeeUtil;
import com.solteq.liferay.training.employees.repository.db.service.persistence.impl.constants.stqPersistenceConstants;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the stq employee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Solteq
 * @generated
 */
@Component(service = STQEmployeePersistence.class)
public class STQEmployeePersistenceImpl
	extends BasePersistenceImpl<STQEmployee> implements STQEmployeePersistence {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>STQEmployeeUtil</code> to access the stq employee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		STQEmployeeImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByEmail;

	/**
	 * Returns the stq employee where email = &#63; or throws a <code>NoSuchSTQEmployeeException</code> if it could not be found.
	 *
	 * @param email the email
	 * @return the matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee findByEmail(String email)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = fetchByEmail(email);

		if (stqEmployee == null) {
			StringBundler sb = new StringBundler(4);

			sb.append(_NO_SUCH_ENTITY_WITH_KEY);

			sb.append("email=");
			sb.append(email);

			sb.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(sb.toString());
			}

			throw new NoSuchSTQEmployeeException(sb.toString());
		}

		return stqEmployee;
	}

	/**
	 * Returns the stq employee where email = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param email the email
	 * @return the matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee fetchByEmail(String email) {
		return fetchByEmail(email, true);
	}

	/**
	 * Returns the stq employee where email = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param email the email
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee fetchByEmail(String email, boolean useFinderCache) {
		email = Objects.toString(email, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {email};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByEmail, finderArgs, this);
		}

		if (result instanceof STQEmployee) {
			STQEmployee stqEmployee = (STQEmployee)result;

			if (!Objects.equals(email, stqEmployee.getEmail())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler sb = new StringBundler(3);

			sb.append(_SQL_SELECT_STQEMPLOYEE_WHERE);

			boolean bindEmail = false;

			if (email.isEmpty()) {
				sb.append(_FINDER_COLUMN_EMAIL_EMAIL_3);
			}
			else {
				bindEmail = true;

				sb.append(_FINDER_COLUMN_EMAIL_EMAIL_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindEmail) {
					queryPos.add(email);
				}

				List<STQEmployee> list = query.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByEmail, finderArgs, list);
					}
				}
				else {
					STQEmployee stqEmployee = list.get(0);

					result = stqEmployee;

					cacheResult(stqEmployee);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (STQEmployee)result;
		}
	}

	/**
	 * Removes the stq employee where email = &#63; from the database.
	 *
	 * @param email the email
	 * @return the stq employee that was removed
	 */
	@Override
	public STQEmployee removeByEmail(String email)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = findByEmail(email);

		return remove(stqEmployee);
	}

	/**
	 * Returns the number of stq employees where email = &#63;.
	 *
	 * @param email the email
	 * @return the number of matching stq employees
	 */
	@Override
	public int countByEmail(String email) {
		STQEmployee stqEmployee = fetchByEmail(email);

		if (stqEmployee == null) {
			return 0;
		}

		return 1;
	}

	private static final String _FINDER_COLUMN_EMAIL_EMAIL_2 =
		"stqEmployee.email = ?";

	private static final String _FINDER_COLUMN_EMAIL_EMAIL_3 =
		"(stqEmployee.email IS NULL OR stqEmployee.email = '')";

	private FinderPath _finderPathWithPaginationFindByDepartment;
	private FinderPath _finderPathWithoutPaginationFindByDepartment;
	private FinderPath _finderPathCountByDepartment;

	/**
	 * Returns all the stq employees where department = &#63;.
	 *
	 * @param department the department
	 * @return the matching stq employees
	 */
	@Override
	public List<STQEmployee> findByDepartment(String department) {
		return findByDepartment(
			department, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<STQEmployee> findByDepartment(
		String department, int start, int end) {

		return findByDepartment(department, start, end, null);
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
	@Override
	public List<STQEmployee> findByDepartment(
		String department, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator) {

		return findByDepartment(
			department, start, end, orderByComparator, true);
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
	@Override
	public List<STQEmployee> findByDepartment(
		String department, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator,
		boolean useFinderCache) {

		department = Objects.toString(department, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByDepartment;
				finderArgs = new Object[] {department};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByDepartment;
			finderArgs = new Object[] {
				department, start, end, orderByComparator
			};
		}

		List<STQEmployee> list = null;

		if (useFinderCache) {
			list = (List<STQEmployee>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (STQEmployee stqEmployee : list) {
					if (!department.equals(stqEmployee.getDepartment())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_STQEMPLOYEE_WHERE);

			boolean bindDepartment = false;

			if (department.isEmpty()) {
				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3);
			}
			else {
				bindDepartment = true;

				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(STQEmployeeModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDepartment) {
					queryPos.add(department);
				}

				list = (List<STQEmployee>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee findByDepartment_First(
			String department, OrderByComparator<STQEmployee> orderByComparator)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = fetchByDepartment_First(
			department, orderByComparator);

		if (stqEmployee != null) {
			return stqEmployee;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("department=");
		sb.append(department);

		sb.append("}");

		throw new NoSuchSTQEmployeeException(sb.toString());
	}

	/**
	 * Returns the first stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee fetchByDepartment_First(
		String department, OrderByComparator<STQEmployee> orderByComparator) {

		List<STQEmployee> list = findByDepartment(
			department, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee findByDepartment_Last(
			String department, OrderByComparator<STQEmployee> orderByComparator)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = fetchByDepartment_Last(
			department, orderByComparator);

		if (stqEmployee != null) {
			return stqEmployee;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("department=");
		sb.append(department);

		sb.append("}");

		throw new NoSuchSTQEmployeeException(sb.toString());
	}

	/**
	 * Returns the last stq employee in the ordered set where department = &#63;.
	 *
	 * @param department the department
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee fetchByDepartment_Last(
		String department, OrderByComparator<STQEmployee> orderByComparator) {

		int count = countByDepartment(department);

		if (count == 0) {
			return null;
		}

		List<STQEmployee> list = findByDepartment(
			department, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public STQEmployee[] findByDepartment_PrevAndNext(
			long employeeId, String department,
			OrderByComparator<STQEmployee> orderByComparator)
		throws NoSuchSTQEmployeeException {

		department = Objects.toString(department, "");

		STQEmployee stqEmployee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			STQEmployee[] array = new STQEmployeeImpl[3];

			array[0] = getByDepartment_PrevAndNext(
				session, stqEmployee, department, orderByComparator, true);

			array[1] = stqEmployee;

			array[2] = getByDepartment_PrevAndNext(
				session, stqEmployee, department, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected STQEmployee getByDepartment_PrevAndNext(
		Session session, STQEmployee stqEmployee, String department,
		OrderByComparator<STQEmployee> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_STQEMPLOYEE_WHERE);

		boolean bindDepartment = false;

		if (department.isEmpty()) {
			sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3);
		}
		else {
			bindDepartment = true;

			sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(STQEmployeeModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindDepartment) {
			queryPos.add(department);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(stqEmployee)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<STQEmployee> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the stq employees where department = &#63; from the database.
	 *
	 * @param department the department
	 */
	@Override
	public void removeByDepartment(String department) {
		for (STQEmployee stqEmployee :
				findByDepartment(
					department, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(stqEmployee);
		}
	}

	/**
	 * Returns the number of stq employees where department = &#63;.
	 *
	 * @param department the department
	 * @return the number of matching stq employees
	 */
	@Override
	public int countByDepartment(String department) {
		department = Objects.toString(department, "");

		FinderPath finderPath = _finderPathCountByDepartment;

		Object[] finderArgs = new Object[] {department};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_STQEMPLOYEE_WHERE);

			boolean bindDepartment = false;

			if (department.isEmpty()) {
				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3);
			}
			else {
				bindDepartment = true;

				sb.append(_FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindDepartment) {
					queryPos.add(department);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_DEPARTMENT_DEPARTMENT_2 =
		"stqEmployee.department = ?";

	private static final String _FINDER_COLUMN_DEPARTMENT_DEPARTMENT_3 =
		"(stqEmployee.department IS NULL OR stqEmployee.department = '')";

	private FinderPath _finderPathWithPaginationFindByCompanyName;
	private FinderPath _finderPathWithoutPaginationFindByCompanyName;
	private FinderPath _finderPathCountByCompanyName;

	/**
	 * Returns all the stq employees where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the matching stq employees
	 */
	@Override
	public List<STQEmployee> findByCompanyName(String companyName) {
		return findByCompanyName(
			companyName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<STQEmployee> findByCompanyName(
		String companyName, int start, int end) {

		return findByCompanyName(companyName, start, end, null);
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
	@Override
	public List<STQEmployee> findByCompanyName(
		String companyName, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator) {

		return findByCompanyName(
			companyName, start, end, orderByComparator, true);
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
	@Override
	public List<STQEmployee> findByCompanyName(
		String companyName, int start, int end,
		OrderByComparator<STQEmployee> orderByComparator,
		boolean useFinderCache) {

		companyName = Objects.toString(companyName, "");

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindByCompanyName;
				finderArgs = new Object[] {companyName};
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindByCompanyName;
			finderArgs = new Object[] {
				companyName, start, end, orderByComparator
			};
		}

		List<STQEmployee> list = null;

		if (useFinderCache) {
			list = (List<STQEmployee>)finderCache.getResult(
				finderPath, finderArgs, this);

			if ((list != null) && !list.isEmpty()) {
				for (STQEmployee stqEmployee : list) {
					if (!companyName.equals(stqEmployee.getCompanyName())) {
						list = null;

						break;
					}
				}
			}
		}

		if (list == null) {
			StringBundler sb = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					3 + (orderByComparator.getOrderByFields().length * 2));
			}
			else {
				sb = new StringBundler(3);
			}

			sb.append(_SQL_SELECT_STQEMPLOYEE_WHERE);

			boolean bindCompanyName = false;

			if (companyName.isEmpty()) {
				sb.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
			}
			else {
				bindCompanyName = true;

				sb.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
			}

			if (orderByComparator != null) {
				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}
			else {
				sb.append(STQEmployeeModelImpl.ORDER_BY_JPQL);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindCompanyName) {
					queryPos.add(companyName);
				}

				list = (List<STQEmployee>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Returns the first stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee findByCompanyName_First(
			String companyName,
			OrderByComparator<STQEmployee> orderByComparator)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = fetchByCompanyName_First(
			companyName, orderByComparator);

		if (stqEmployee != null) {
			return stqEmployee;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyName=");
		sb.append(companyName);

		sb.append("}");

		throw new NoSuchSTQEmployeeException(sb.toString());
	}

	/**
	 * Returns the first stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee fetchByCompanyName_First(
		String companyName, OrderByComparator<STQEmployee> orderByComparator) {

		List<STQEmployee> list = findByCompanyName(
			companyName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee
	 * @throws NoSuchSTQEmployeeException if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee findByCompanyName_Last(
			String companyName,
			OrderByComparator<STQEmployee> orderByComparator)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = fetchByCompanyName_Last(
			companyName, orderByComparator);

		if (stqEmployee != null) {
			return stqEmployee;
		}

		StringBundler sb = new StringBundler(4);

		sb.append(_NO_SUCH_ENTITY_WITH_KEY);

		sb.append("companyName=");
		sb.append(companyName);

		sb.append("}");

		throw new NoSuchSTQEmployeeException(sb.toString());
	}

	/**
	 * Returns the last stq employee in the ordered set where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching stq employee, or <code>null</code> if a matching stq employee could not be found
	 */
	@Override
	public STQEmployee fetchByCompanyName_Last(
		String companyName, OrderByComparator<STQEmployee> orderByComparator) {

		int count = countByCompanyName(companyName);

		if (count == 0) {
			return null;
		}

		List<STQEmployee> list = findByCompanyName(
			companyName, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
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
	@Override
	public STQEmployee[] findByCompanyName_PrevAndNext(
			long employeeId, String companyName,
			OrderByComparator<STQEmployee> orderByComparator)
		throws NoSuchSTQEmployeeException {

		companyName = Objects.toString(companyName, "");

		STQEmployee stqEmployee = findByPrimaryKey(employeeId);

		Session session = null;

		try {
			session = openSession();

			STQEmployee[] array = new STQEmployeeImpl[3];

			array[0] = getByCompanyName_PrevAndNext(
				session, stqEmployee, companyName, orderByComparator, true);

			array[1] = stqEmployee;

			array[2] = getByCompanyName_PrevAndNext(
				session, stqEmployee, companyName, orderByComparator, false);

			return array;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	protected STQEmployee getByCompanyName_PrevAndNext(
		Session session, STQEmployee stqEmployee, String companyName,
		OrderByComparator<STQEmployee> orderByComparator, boolean previous) {

		StringBundler sb = null;

		if (orderByComparator != null) {
			sb = new StringBundler(
				4 + (orderByComparator.getOrderByConditionFields().length * 3) +
					(orderByComparator.getOrderByFields().length * 3));
		}
		else {
			sb = new StringBundler(3);
		}

		sb.append(_SQL_SELECT_STQEMPLOYEE_WHERE);

		boolean bindCompanyName = false;

		if (companyName.isEmpty()) {
			sb.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
		}
		else {
			bindCompanyName = true;

			sb.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields =
				orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				sb.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						sb.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(WHERE_GREATER_THAN);
					}
					else {
						sb.append(WHERE_LESSER_THAN);
					}
				}
			}

			sb.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				sb.append(_ORDER_BY_ENTITY_ALIAS);
				sb.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						sb.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						sb.append(ORDER_BY_ASC);
					}
					else {
						sb.append(ORDER_BY_DESC);
					}
				}
			}
		}
		else {
			sb.append(STQEmployeeModelImpl.ORDER_BY_JPQL);
		}

		String sql = sb.toString();

		Query query = session.createQuery(sql);

		query.setFirstResult(0);
		query.setMaxResults(2);

		QueryPos queryPos = QueryPos.getInstance(query);

		if (bindCompanyName) {
			queryPos.add(companyName);
		}

		if (orderByComparator != null) {
			for (Object orderByConditionValue :
					orderByComparator.getOrderByConditionValues(stqEmployee)) {

				queryPos.add(orderByConditionValue);
			}
		}

		List<STQEmployee> list = query.list();

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Removes all the stq employees where companyName = &#63; from the database.
	 *
	 * @param companyName the company name
	 */
	@Override
	public void removeByCompanyName(String companyName) {
		for (STQEmployee stqEmployee :
				findByCompanyName(
					companyName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null)) {

			remove(stqEmployee);
		}
	}

	/**
	 * Returns the number of stq employees where companyName = &#63;.
	 *
	 * @param companyName the company name
	 * @return the number of matching stq employees
	 */
	@Override
	public int countByCompanyName(String companyName) {
		companyName = Objects.toString(companyName, "");

		FinderPath finderPath = _finderPathCountByCompanyName;

		Object[] finderArgs = new Object[] {companyName};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler sb = new StringBundler(2);

			sb.append(_SQL_COUNT_STQEMPLOYEE_WHERE);

			boolean bindCompanyName = false;

			if (companyName.isEmpty()) {
				sb.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3);
			}
			else {
				bindCompanyName = true;

				sb.append(_FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2);
			}

			String sql = sb.toString();

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				QueryPos queryPos = QueryPos.getInstance(query);

				if (bindCompanyName) {
					queryPos.add(companyName);
				}

				count = (Long)query.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_2 =
		"stqEmployee.companyName = ?";

	private static final String _FINDER_COLUMN_COMPANYNAME_COMPANYNAME_3 =
		"(stqEmployee.companyName IS NULL OR stqEmployee.companyName = '')";

	public STQEmployeePersistenceImpl() {
		setModelClass(STQEmployee.class);

		setModelImplClass(STQEmployeeImpl.class);
		setModelPKClass(long.class);

		setTable(STQEmployeeTable.INSTANCE);
	}

	/**
	 * Caches the stq employee in the entity cache if it is enabled.
	 *
	 * @param stqEmployee the stq employee
	 */
	@Override
	public void cacheResult(STQEmployee stqEmployee) {
		entityCache.putResult(
			STQEmployeeImpl.class, stqEmployee.getPrimaryKey(), stqEmployee);

		finderCache.putResult(
			_finderPathFetchByEmail, new Object[] {stqEmployee.getEmail()},
			stqEmployee);
	}

	private int _valueObjectFinderCacheListThreshold;

	/**
	 * Caches the stq employees in the entity cache if it is enabled.
	 *
	 * @param stqEmployees the stq employees
	 */
	@Override
	public void cacheResult(List<STQEmployee> stqEmployees) {
		if ((_valueObjectFinderCacheListThreshold == 0) ||
			((_valueObjectFinderCacheListThreshold > 0) &&
			 (stqEmployees.size() > _valueObjectFinderCacheListThreshold))) {

			return;
		}

		for (STQEmployee stqEmployee : stqEmployees) {
			if (entityCache.getResult(
					STQEmployeeImpl.class, stqEmployee.getPrimaryKey()) ==
						null) {

				cacheResult(stqEmployee);
			}
		}
	}

	/**
	 * Clears the cache for all stq employees.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(STQEmployeeImpl.class);

		finderCache.clearCache(STQEmployeeImpl.class);
	}

	/**
	 * Clears the cache for the stq employee.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(STQEmployee stqEmployee) {
		entityCache.removeResult(STQEmployeeImpl.class, stqEmployee);
	}

	@Override
	public void clearCache(List<STQEmployee> stqEmployees) {
		for (STQEmployee stqEmployee : stqEmployees) {
			entityCache.removeResult(STQEmployeeImpl.class, stqEmployee);
		}
	}

	@Override
	public void clearCache(Set<Serializable> primaryKeys) {
		finderCache.clearCache(STQEmployeeImpl.class);

		for (Serializable primaryKey : primaryKeys) {
			entityCache.removeResult(STQEmployeeImpl.class, primaryKey);
		}
	}

	protected void cacheUniqueFindersCache(
		STQEmployeeModelImpl stqEmployeeModelImpl) {

		Object[] args = new Object[] {stqEmployeeModelImpl.getEmail()};

		finderCache.putResult(
			_finderPathFetchByEmail, args, stqEmployeeModelImpl);
	}

	/**
	 * Creates a new stq employee with the primary key. Does not add the stq employee to the database.
	 *
	 * @param employeeId the primary key for the new stq employee
	 * @return the new stq employee
	 */
	@Override
	public STQEmployee create(long employeeId) {
		STQEmployee stqEmployee = new STQEmployeeImpl();

		stqEmployee.setNew(true);
		stqEmployee.setPrimaryKey(employeeId);

		stqEmployee.setCompanyId(CompanyThreadLocal.getCompanyId());

		return stqEmployee;
	}

	/**
	 * Removes the stq employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee that was removed
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	@Override
	public STQEmployee remove(long employeeId)
		throws NoSuchSTQEmployeeException {

		return remove((Serializable)employeeId);
	}

	/**
	 * Removes the stq employee with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the stq employee
	 * @return the stq employee that was removed
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	@Override
	public STQEmployee remove(Serializable primaryKey)
		throws NoSuchSTQEmployeeException {

		Session session = null;

		try {
			session = openSession();

			STQEmployee stqEmployee = (STQEmployee)session.get(
				STQEmployeeImpl.class, primaryKey);

			if (stqEmployee == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSTQEmployeeException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(stqEmployee);
		}
		catch (NoSuchSTQEmployeeException noSuchEntityException) {
			throw noSuchEntityException;
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected STQEmployee removeImpl(STQEmployee stqEmployee) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(stqEmployee)) {
				stqEmployee = (STQEmployee)session.get(
					STQEmployeeImpl.class, stqEmployee.getPrimaryKeyObj());
			}

			if (stqEmployee != null) {
				session.delete(stqEmployee);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		if (stqEmployee != null) {
			clearCache(stqEmployee);
		}

		return stqEmployee;
	}

	@Override
	public STQEmployee updateImpl(STQEmployee stqEmployee) {
		boolean isNew = stqEmployee.isNew();

		if (!(stqEmployee instanceof STQEmployeeModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(stqEmployee.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(stqEmployee);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in stqEmployee proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom STQEmployee implementation " +
					stqEmployee.getClass());
		}

		STQEmployeeModelImpl stqEmployeeModelImpl =
			(STQEmployeeModelImpl)stqEmployee;

		long userId = GetterUtil.getLong(PrincipalThreadLocal.getName());

		if (userId > 0) {
			long companyId = stqEmployee.getCompanyId();

			long groupId = 0;

			long employeeId = 0;

			if (!isNew) {
				employeeId = stqEmployee.getPrimaryKey();
			}

			try {
				stqEmployee.setFirstName(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getFirstName(), null));

				stqEmployee.setLastName(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getLastName(), null));

				stqEmployee.setEmail(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getEmail(), null));

				stqEmployee.setJobTitle(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getJobTitle(), null));

				stqEmployee.setPhoneNumber(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getPhoneNumber(), null));

				stqEmployee.setCompanyName(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getCompanyName(), null));

				stqEmployee.setDepartment(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getDepartment(), null));

				stqEmployee.setWorkLocation(
					SanitizerUtil.sanitize(
						companyId, groupId, userId, STQEmployee.class.getName(),
						employeeId, ContentTypes.TEXT_HTML, Sanitizer.MODE_ALL,
						stqEmployee.getWorkLocation(), null));
			}
			catch (SanitizerException sanitizerException) {
				throw new SystemException(sanitizerException);
			}
		}

		Session session = null;

		try {
			session = openSession();

			if (isNew) {
				session.save(stqEmployee);
			}
			else {
				stqEmployee = (STQEmployee)session.merge(stqEmployee);
			}
		}
		catch (Exception exception) {
			throw processException(exception);
		}
		finally {
			closeSession(session);
		}

		entityCache.putResult(
			STQEmployeeImpl.class, stqEmployeeModelImpl, false, true);

		cacheUniqueFindersCache(stqEmployeeModelImpl);

		if (isNew) {
			stqEmployee.setNew(false);
		}

		stqEmployee.resetOriginalValues();

		return stqEmployee;
	}

	/**
	 * Returns the stq employee with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the stq employee
	 * @return the stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	@Override
	public STQEmployee findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSTQEmployeeException {

		STQEmployee stqEmployee = fetchByPrimaryKey(primaryKey);

		if (stqEmployee == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSTQEmployeeException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return stqEmployee;
	}

	/**
	 * Returns the stq employee with the primary key or throws a <code>NoSuchSTQEmployeeException</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee
	 * @throws NoSuchSTQEmployeeException if a stq employee with the primary key could not be found
	 */
	@Override
	public STQEmployee findByPrimaryKey(long employeeId)
		throws NoSuchSTQEmployeeException {

		return findByPrimaryKey((Serializable)employeeId);
	}

	/**
	 * Returns the stq employee with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param employeeId the primary key of the stq employee
	 * @return the stq employee, or <code>null</code> if a stq employee with the primary key could not be found
	 */
	@Override
	public STQEmployee fetchByPrimaryKey(long employeeId) {
		return fetchByPrimaryKey((Serializable)employeeId);
	}

	/**
	 * Returns all the stq employees.
	 *
	 * @return the stq employees
	 */
	@Override
	public List<STQEmployee> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
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
	@Override
	public List<STQEmployee> findAll(int start, int end) {
		return findAll(start, end, null);
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
	@Override
	public List<STQEmployee> findAll(
		int start, int end, OrderByComparator<STQEmployee> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
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
	@Override
	public List<STQEmployee> findAll(
		int start, int end, OrderByComparator<STQEmployee> orderByComparator,
		boolean useFinderCache) {

		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<STQEmployee> list = null;

		if (useFinderCache) {
			list = (List<STQEmployee>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler sb = null;
			String sql = null;

			if (orderByComparator != null) {
				sb = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				sb.append(_SQL_SELECT_STQEMPLOYEE);

				appendOrderByComparator(
					sb, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = sb.toString();
			}
			else {
				sql = _SQL_SELECT_STQEMPLOYEE;

				sql = sql.concat(STQEmployeeModelImpl.ORDER_BY_JPQL);
			}

			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(sql);

				list = (List<STQEmployee>)QueryUtil.list(
					query, getDialect(), start, end);

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the stq employees from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (STQEmployee stqEmployee : findAll()) {
			remove(stqEmployee);
		}
	}

	/**
	 * Returns the number of stq employees.
	 *
	 * @return the number of stq employees
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query query = session.createQuery(_SQL_COUNT_STQEMPLOYEE);

				count = (Long)query.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception exception) {
				throw processException(exception);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "employeeId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_STQEMPLOYEE;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return STQEmployeeModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the stq employee persistence.
	 */
	@Activate
	public void activate() {
		_valueObjectFinderCacheListThreshold = GetterUtil.getInteger(
			PropsUtil.get(PropsKeys.VALUE_OBJECT_FINDER_CACHE_LIST_THRESHOLD));

		_finderPathWithPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0],
			new String[0], true);

		_finderPathCountAll = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0], new String[0], false);

		_finderPathFetchByEmail = new FinderPath(
			FINDER_CLASS_NAME_ENTITY, "fetchByEmail",
			new String[] {String.class.getName()}, new String[] {"email"},
			true);

		_finderPathWithPaginationFindByDepartment = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByDepartment",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"department"}, true);

		_finderPathWithoutPaginationFindByDepartment = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByDepartment",
			new String[] {String.class.getName()}, new String[] {"department"},
			true);

		_finderPathCountByDepartment = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByDepartment",
			new String[] {String.class.getName()}, new String[] {"department"},
			false);

		_finderPathWithPaginationFindByCompanyName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findByCompanyName",
			new String[] {
				String.class.getName(), Integer.class.getName(),
				Integer.class.getName(), OrderByComparator.class.getName()
			},
			new String[] {"companyName"}, true);

		_finderPathWithoutPaginationFindByCompanyName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findByCompanyName",
			new String[] {String.class.getName()}, new String[] {"companyName"},
			true);

		_finderPathCountByCompanyName = new FinderPath(
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByCompanyName",
			new String[] {String.class.getName()}, new String[] {"companyName"},
			false);

		STQEmployeeUtil.setPersistence(this);
	}

	@Deactivate
	public void deactivate() {
		STQEmployeeUtil.setPersistence(null);

		entityCache.removeCache(STQEmployeeImpl.class.getName());
	}

	@Override
	@Reference(
		target = stqPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
	}

	@Override
	@Reference(
		target = stqPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = stqPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_STQEMPLOYEE =
		"SELECT stqEmployee FROM STQEmployee stqEmployee";

	private static final String _SQL_SELECT_STQEMPLOYEE_WHERE =
		"SELECT stqEmployee FROM STQEmployee stqEmployee WHERE ";

	private static final String _SQL_COUNT_STQEMPLOYEE =
		"SELECT COUNT(stqEmployee) FROM STQEmployee stqEmployee";

	private static final String _SQL_COUNT_STQEMPLOYEE_WHERE =
		"SELECT COUNT(stqEmployee) FROM STQEmployee stqEmployee WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "stqEmployee.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No STQEmployee exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No STQEmployee exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		STQEmployeePersistenceImpl.class);

	@Override
	protected FinderCache getFinderCache() {
		return finderCache;
	}

}