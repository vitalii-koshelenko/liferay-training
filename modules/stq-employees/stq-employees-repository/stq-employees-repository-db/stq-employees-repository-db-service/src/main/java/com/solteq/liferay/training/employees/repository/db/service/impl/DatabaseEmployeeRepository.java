package com.solteq.liferay.training.employees.repository.db.service.impl;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import com.solteq.liferay.training.employees.repository.db.service.STQEmployeeLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;
import java.util.stream.Collectors;

@Component(
        immediate = true,
        property = {
                "repository.type=database",
                "service.ranking:Integer=102"
        },
        service = EmployeeRepository.class
)
public class DatabaseEmployeeRepository implements EmployeeRepository {

    @Override
    public List<Employee> fetchEmployees() {
        List<STQEmployee> stqEmployees = stqEmployeeLocalService.getSTQEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS);
        return stqEmployees.stream().map(stqEmployee -> (Employee)stqEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public int fetchEmployeeCount(EmployeeFilter filter) {
        return stqEmployeeLocalService.fetchEmployeeCount(filter);
    }

    @Override
    public List<Employee> fetchEmployees(int start, int end, EmployeeFilter filter) {
        List<STQEmployee> stqEmployees = stqEmployeeLocalService.fetchEmployees(start, end, filter);
        return stqEmployees.stream().map(stqEmployee -> (Employee)stqEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public Employee fetchEmployeeById(long employeeId) {
        return (Employee) stqEmployeeLocalService.fetchSTQEmployee(employeeId);
    }

    @Override
    public Employee saveEmployee(long employeeId, String firstName, String lastName, String email,
                                 String phoneNumber, String companyName, String jobTitle, String department, String workLocation) {
        return (Employee) stqEmployeeLocalService.saveEmployee(employeeId, firstName, lastName, email, phoneNumber,
                companyName, jobTitle, department, workLocation);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        stqEmployeeLocalService.deleteEmployee(employeeId);
    }

    @Reference
    private STQEmployeeLocalService stqEmployeeLocalService;
}
