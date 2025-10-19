/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.service.impl;

import com.liferay.petra.sql.dsl.DSLQueryFactoryUtil;
import com.liferay.petra.sql.dsl.expression.Predicate;
import com.liferay.petra.sql.dsl.query.DSLQuery;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.util.Validator;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployeeTable;
import com.solteq.liferay.training.employees.repository.db.service.base.STQEmployeeLocalServiceBaseImpl;
import org.osgi.service.component.annotations.Component;

import java.util.List;

/**
 * @author Solteq
 */
@Component(
	property = "model.class.name=com.solteq.liferay.training.employees.repository.db.model.STQEmployee",
	service = AopService.class
)
public class STQEmployeeLocalServiceImpl extends STQEmployeeLocalServiceBaseImpl {

    @Override
    public List<STQEmployee> fetchEmployees(int start, int end, EmployeeFilter filter) {
        DSLQuery dslQuery = DSLQueryFactoryUtil
                .select()
                .from(STQEmployeeTable.INSTANCE)
                .where(buildWhereCondition(filter))
                .limit(start, end);
        return dslQuery(dslQuery);
    }

    @Override
    public int fetchEmployeeCount(EmployeeFilter filter) {
        DSLQuery dslQuery = DSLQueryFactoryUtil
                .count()
                .from(STQEmployeeTable.INSTANCE)
                .where(buildWhereCondition(filter));
        return dslQueryCount(dslQuery);
    }

    private Predicate buildWhereCondition(EmployeeFilter filter) {
        String companyName = filter.getCompanyName();
        String department = filter.getDepartment();
        String jobTitle = filter.getJobTitle();
        String workLocation = filter.getWorkLocation();
        String searchTerm = filter.getSearch();
        Predicate whereCondition = null;
        if (Validator.isNotNull(companyName)) {
            whereCondition = STQEmployeeTable.INSTANCE.companyName.eq(companyName) ;
        }
        if (Validator.isNotNull(department)) {
            Predicate departmentCondition = STQEmployeeTable.INSTANCE.department.eq(department);
            whereCondition = whereCondition == null ? departmentCondition : whereCondition.and(departmentCondition);
        }
        if (Validator.isNotNull(jobTitle)) {
            Predicate jobTitleCondition = STQEmployeeTable.INSTANCE.jobTitle.eq(jobTitle);
            whereCondition = whereCondition == null ? jobTitleCondition : whereCondition.and(jobTitleCondition);
        }
        if (Validator.isNotNull(workLocation)) {
            Predicate workLocationCondition = STQEmployeeTable.INSTANCE.workLocation.eq(workLocation);
            whereCondition = whereCondition == null ? workLocationCondition : whereCondition.and(workLocationCondition);
        }
        if (Validator.isNotNull(searchTerm)) {
            Predicate searchCondition = STQEmployeeTable.INSTANCE.firstName.eq(searchTerm)
                    .or(STQEmployeeTable.INSTANCE.lastName.eq(searchTerm))
                    .or(STQEmployeeTable.INSTANCE.email.eq(searchTerm));
            whereCondition = whereCondition == null ? searchCondition : whereCondition.and(searchCondition);
        }
        return whereCondition;
    }

    @Override
    public STQEmployee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber,
                                 String companyName, String jobTitle, String department, String workLocation) {
        STQEmployee stqEmployee = null;
        if (Validator.isNull(employeeId)) {
            // Add new STQEmployee
            employeeId = counterLocalService.increment(STQEmployee.class.getName());
            stqEmployee = stqEmployeePersistence.create(employeeId);
        } else {
            // Fetch STQEmployee by employeeId
            stqEmployee = stqEmployeePersistence.fetchByPrimaryKey(employeeId);
            if (stqEmployee == null) {
                throw new RuntimeException("Employee not found, employeeId: " + employeeId);
            }
        }
        stqEmployee.setCompanyId(LiferayUtil.getDefaultCompanyId());
        stqEmployee.setFirstName(firstName);
        stqEmployee.setLastName(lastName);
        stqEmployee.setEmail(email);
        stqEmployee.setPhoneNumber(phoneNumber);
        stqEmployee.setCompanyName(companyName);
        stqEmployee.setJobTitle(jobTitle);
        stqEmployee.setDepartment(department);
        stqEmployee.setWorkLocation(workLocation);
        // Update STQEmployee
        return stqEmployeeLocalService.updateSTQEmployee(stqEmployee);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        try {
            stqEmployeeLocalService.deleteSTQEmployee(employeeId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public STQEmployee findByEmail(String email) {
        return stqEmployeePersistence.fetchByEmail(email);
    }

    @Override
    public List<STQEmployee> findByDepartment(String department) {
        return stqEmployeePersistence.findByDepartment(department);
    }

    @Override
    public List<STQEmployee> findByCompanyName(String companyName) {
        return stqEmployeePersistence.findByCompanyName(companyName);
    }
}