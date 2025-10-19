package com.solteq.liferay.training.employees.repository.sync.dispatch.tasks;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.model.SyncInfo;
import com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.service.EmployeesSyncService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
        immediate = true,
        property = {
                "dispatch.task.executor.name=" + EmployeesSyncDispatchTaskExecutor.KEY,
                "dispatch.task.executor.type=" + EmployeesSyncDispatchTaskExecutor.KEY,
        },
        service = DispatchTaskExecutor.class
)
public class EmployeesSyncDispatchTaskExecutor extends BaseDispatchTaskExecutor {

    public static final String KEY = "dispatch-task.employees-sync";

    @Override
    public void doExecute(DispatchTrigger dispatchTrigger, DispatchTaskExecutorOutput dispatchTaskExecutorOutput) throws Exception {
        _log.info("EmployeesSyncDispatchTaskExecutor Started...");
        try {
            // Sync Employees
            SyncInfo syncInfo = employeesSyncService.syncEmployees();

            // Save Formatted Response
             dispatchTaskExecutorOutput.setOutput(syncInfo.toString());

        } catch (Exception e) {

            // Handle Error
            String errorMsg = "Employees Sync failed, cause: " + e.getMessage();
            _log.error(errorMsg, e);

            // Set Error Response
            dispatchTaskExecutorOutput.setError(errorMsg);

            // Throw an Exception to make Job Failed
            throw new PortalException(errorMsg);
        }

        _log.info("EmployeesSyncDispatchTaskExecutor Finished.");
    }

    @Override
    public String getName() {
        return KEY;
    }

    @Reference
    private EmployeesSyncService employeesSyncService;

    private static final Log _log = LogFactoryUtil.getLog(EmployeesSyncDispatchTaskExecutor.class);
}