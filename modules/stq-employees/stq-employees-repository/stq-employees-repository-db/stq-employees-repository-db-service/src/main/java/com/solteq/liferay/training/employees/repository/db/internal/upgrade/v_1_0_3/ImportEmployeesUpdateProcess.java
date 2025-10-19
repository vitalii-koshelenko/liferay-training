package com.solteq.liferay.training.employees.repository.db.internal.upgrade.v_1_0_3;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;

import java.util.List;

public class ImportEmployeesUpdateProcess extends UpgradeProcess {

    private final EmployeeRepository imMemoryEmployeeRepository;
    private final EmployeeRepository databaseEmployeeRepository;

    public ImportEmployeesUpdateProcess(EmployeeRepository imMemoryEmployeeRepository, EmployeeRepository databaseEmployeeRepository) {
        this.imMemoryEmployeeRepository = imMemoryEmployeeRepository;
        this.databaseEmployeeRepository = databaseEmployeeRepository;
    }

    @Override
    protected void doUpgrade() throws Exception {
        List<Employee> employees = imMemoryEmployeeRepository.fetchEmployees();
        for (Employee employee: employees) {
            Employee dbEmployee = databaseEmployeeRepository.saveEmployee(
                    0,
                    employee.getFirstName(),
                    employee.getLastName(),
                    employee.getEmail(),
                    employee.getPhoneNumber(),
                    employee.getCompanyName(),
                    employee.getJobTitle(),
                    employee.getDepartment(),
                    employee.getWorkLocation()
            );
            _log.info(String.format(String.format("Saved Employee #%d %s to Database from In-Memory repository.", dbEmployee.getEmployeeId(), dbEmployee.getEmail())));
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(ImportEmployeesUpdateProcess.class);
}
