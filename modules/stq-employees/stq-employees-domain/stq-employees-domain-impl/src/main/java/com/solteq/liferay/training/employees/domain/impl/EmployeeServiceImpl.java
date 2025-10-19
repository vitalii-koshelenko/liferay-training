package com.solteq.liferay.training.employees.domain.impl;

import com.solteq.liferay.training.employees.configuration.util.EmployeesRepositoryConfigurationUtil;
import com.solteq.liferay.training.employees.domain.api.EmployeeService;
import com.solteq.liferay.training.employees.domain.api.EmployeeValidator;
import com.solteq.liferay.training.employees.domain.exception.EmployeeValidationException;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.BaseEmployee;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public List<Employee> getEmployees() {
        return getEmployeeRepository().fetchEmployees();
    }

    @Override
    public int getEmployeeCount(EmployeeFilter filter) {
        return getEmployeeRepository().fetchEmployeeCount(filter);
    }

    @Override
    public List<Employee> getEmployees(int start, int end, EmployeeFilter filter) {
        return getEmployeeRepository().fetchEmployees(start, end, filter);
    }

    @Override
    public Employee getEmployeeById(long employeeId) {
        return getEmployeeRepository().fetchEmployeeById(employeeId);
    }

    @Override
    public Employee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber,
                                 String companyName, String jobTitle, String department, String workLocation)
            throws EmployeeValidationException {

        // Validate Employee
        BaseEmployee baseEmployee = new BaseEmployee(employeeId, firstName, lastName, email, jobTitle,
                phoneNumber, companyName, department, workLocation);
        employeeValidator.validateEmployee(baseEmployee);

        // Save Employee
        return getEmployeeRepository().saveEmployee(employeeId, firstName, lastName, email, phoneNumber,
                companyName, jobTitle, department, workLocation);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        getEmployeeRepository().deleteEmployee(employeeId);
    }

    public EmployeeRepository getEmployeeRepository() {
        String repositoryType = getRepositoryType();
        return switch (repositoryType) {
            case "in-memory" -> inMemoryEmployeeRepository;
            case "database" -> databaseEmployeeRepository;
            case "elasticsearch" -> elasticsearchEmployeeRepository;
            case "objects" -> objectsEmployeeRepository;
            default -> databaseEmployeeRepository;
        };
    }
    
    @Override
    public String getRepositoryType() {
        return EmployeesRepositoryConfigurationUtil.getRepositoryType();
    }

    @Reference
    private EmployeeValidator employeeValidator;

    @Reference(target = "(repository.type=in-memory)")
    private EmployeeRepository inMemoryEmployeeRepository;
    @Reference(target = "(repository.type=database)")
    private EmployeeRepository databaseEmployeeRepository;
    @Reference(target = "(repository.type=elasticsearch)")
    private EmployeeRepository elasticsearchEmployeeRepository;
    @Reference(target = "(repository.type=objects)")
    private EmployeeRepository objectsEmployeeRepository;

    /*
    @Reference(
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY
    )
    private volatile EmployeeRepository employeeRepository;
    */
}
