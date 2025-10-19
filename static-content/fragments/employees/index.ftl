[#assign employees = employeeService.getEmployees() /]
[#if employees?has_content]
    <div class="table-responsive">
        <table class="table table-autofit table-head-bordered table-heading-nowrap table-hover table-list table-striped">
            <thead>
                <tr>
                    <td>[@liferay_ui["message"] key="employee.employeeId" /]</td>
                    <td>[@liferay_ui["message"] key="employee.firstName" /]</td>
                    <td>[@liferay_ui["message"] key="employee.lastName" /]</td>
                    <td>[@liferay_ui["message"] key="employee.email" /]</td>
                    <td>[@liferay_ui["message"] key="employee.jobTitle" /]</td>
                    <td>[@liferay_ui["message"] key="employee.phoneNumber" /]</td>
                    <td>[@liferay_ui["message"] key="employee.companyName" /]</td>
                    <td>[@liferay_ui["message"] key="employee.department" /]</td>
                    <td>[@liferay_ui["message"] key="employee.workLocation" /]</td>
                </tr>
            </thead>
            <tbody>
                [#list employees as employee]
                    <tr>
                        <td>${employee.employeeId}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.email}</td>
                        <td>${employee.jobTitle}</td>
                        <td>${(employee.phoneNumber)!""}</td>
                        <td>${employee.companyNameLabel}</td>
                        <td>${employee.departmentLabel}</td>
                        <td>${employee.workLocationLabel}</td>
                    </tr>
                [/#list]
            </tbody>
        </table>
    </div>
[#else]
    [@liferay_ui["message"] key="no-employees-found" /]
[/#if]

