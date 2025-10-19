package com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.ListUtil;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.EmployeesSyncDispatchTaskExecutor;
import com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.model.SyncInfo;
import com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.service.EmployeesSyncService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.List;

@Component(immediate = true, service = EmployeesSyncService.class)
public class EmployeesSyncServiceImpl implements EmployeesSyncService {

    @Override
    public SyncInfo syncEmployees() {
        SyncInfo syncInfo = new SyncInfo();
        List<Employee> dbEmployees = databaseEmployeeRepository.fetchEmployees();
        if (ListUtil.isNotEmpty(dbEmployees)) {
            long companyId = LiferayUtil.getDefaultCompanyId();
            for (Employee dbEmployee: dbEmployees) {
                try {
                    String emailAddress = dbEmployee.getEmail();
                    User liferayUser = userLocalService.fetchUserByEmailAddress(companyId, emailAddress);
                    if (liferayUser == null) {
                        Employee employee = objectsEmployeeRepository.saveEmployee(
                                0L,
                                dbEmployee.getFirstName(),
                                dbEmployee.getLastName(),
                                dbEmployee.getEmail(),
                                dbEmployee.getPhoneNumber(),
                                dbEmployee.getCompanyName(),
                                dbEmployee.getJobTitle(),
                                dbEmployee.getDepartment(),
                                dbEmployee.getWorkLocation()
                        );
                        long userId = employee.getEmployeeId();
                        _log.info(String.format("Saved User #%d %s to from Database Repository.", userId, emailAddress));
                        syncInfo.addUser(userId, emailAddress);
                    }
                } catch (Exception e) {
                    String errorMsg = String.format("Failed to save user #%d %s, cause: %s.", dbEmployee.getEmployeeId(), dbEmployee.getEmail(), e.getMessage());
                    _log.error(errorMsg);
                    syncInfo.addError(errorMsg);
                }
            }
        }
        return syncInfo;
    }

    @Reference
    private UserLocalService userLocalService;
    @Reference(target = "(repository.type=database)")
    private EmployeeRepository databaseEmployeeRepository;
    @Reference(target = "(repository.type=objects)")
    private EmployeeRepository objectsEmployeeRepository;

    private static final Log _log = LogFactoryUtil.getLog(EmployeesSyncDispatchTaskExecutor.class);
}
