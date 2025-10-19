<%@ include file="init.jsp" %>

<%
    EmployeeService employeeService = (EmployeeService) request.getAttribute(EmployeeService.class.getName());
    EmployeeFilter employeeFilter = (EmployeeFilter) request.getAttribute(EmployeesListKeys.EMPLOYEE_FILTER);
    String searchTerm = employeeFilter != null ? employeeFilter.getSearch() : GetterUtil.DEFAULT_STRING;
    String companyName = employeeFilter != null ? employeeFilter.getCompanyName() : GetterUtil.DEFAULT_STRING;
    String jobTitle = employeeFilter != null ? employeeFilter.getJobTitle() : GetterUtil.DEFAULT_STRING;
    String department = employeeFilter != null ? employeeFilter.getDepartment() : GetterUtil.DEFAULT_STRING;
    String workLocation = employeeFilter != null ? employeeFilter.getWorkLocation() : GetterUtil.DEFAULT_STRING;
%>

<liferay-portlet:renderURL varImpl="iteratorURL">
    <liferay-portlet:param name="searchTerm" value="<%= searchTerm %>" />
    <liferay-portlet:param name="companyName" value="<%= companyName %>" />
    <liferay-portlet:param name="jobTitle" value="<%= jobTitle %>" />
    <liferay-portlet:param name="department" value="<%= department %>" />
    <liferay-portlet:param name="workLocation" value="<%= workLocation %>" />
</liferay-portlet:renderURL>

<portlet:renderURL var="applyFiltersURL" />
<portlet:renderURL var="addEmployeeURL">
    <portlet:param name="mvcRenderCommandName" value="<%= EmployeesListPortletKeys.MVC_COMMAND_EDIT %>" />
</portlet:renderURL>

<div class="container mt-3">
    <div class="sheet sheet-xl">
        <div class="sheet-header">
        </div>
        <div class="sheet-section">
            <aui:form action="${applyFiltersURL}" method="post">
                <div class="container mt-3">
                    <div class="row">
                        <aui:select name="companyName" value="<%= companyName %>" label="filter.companyName"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="companyName" items="<%= CompanyName.values() %>">
                                <aui:option value="${companyName.key}" label="${companyName.label}" />
                            </c:forEach>
                        </aui:select>
                        <aui:select name="jobTitle" value="<%= jobTitle%>" label="filter.jobTitle"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="jobTitle" items="<%= JobTitle.values() %>">
                                <aui:option value="${jobTitle.label}" label="${jobTitle.label}" />
                            </c:forEach>
                        </aui:select>
                        <aui:select name="department" value="<%= department %>" label="filter.department"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="department" items="<%= Department.values() %>">
                                <aui:option value="${department.key}" label="${department.label}" />
                            </c:forEach>
                        </aui:select>
                        <aui:select name="workLocation" value="<%= workLocation %>" label="filter.workLocation"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="workLocation" items="<%= WorkLocation.values() %>">
                                <aui:option value="${workLocation.key}" label="${workLocation.label}" />
                            </c:forEach>
                        </aui:select>
                    </div>
                    <div class="row align-items-center">
                        <div class="col-md-6"></div>
                        <aui:input name="searchTerm" value="<%= searchTerm %>" label="filter.searchTerm" wrapperCssClass="col-md-3" />
                        <div class="col-md-3 d-flex justify-content-between">
                            <div>
                                <aui:button href="${applyFiltersURL}" name="back" value="btn.clear-filters" />
                                <aui:button type="submit" value="btn.apply-filters" />
                            </div>
                            <div>
                                <aui:button href="${addEmployeeURL}" primary="true" name="addEmployee" value="btn.add" />
                            </div>
                        </div>
                    </div>
                </div>
            </aui:form>

            <liferay-ui:search-container total="<%= employeeService.getEmployeeCount(employeeFilter) %>" delta="4"
                                         emptyResultsMessage="no-employees-found" iteratorURL="${iteratorURL}">
                <liferay-ui:search-container-results
                        results="<%= employeeService.getEmployees(searchContainer.getStart(), searchContainer.getEnd(), employeeFilter) %>"/>
                <liferay-ui:search-container-row className="com.solteq.liferay.training.employees.domain.model.Employee" modelVar="employee" keyProperty="employeeId">
                    <liferay-ui:search-container-column-text name="employee.employeeId" value="${employee.employeeId}" />
                    <liferay-ui:search-container-column-text name="employee.firstName" value="${employee.firstName}" />
                    <liferay-ui:search-container-column-text name="employee.lastName" value="${employee.lastName}" />
                    <liferay-ui:search-container-column-text name="employee.email" value="${employee.email}" />
                    <liferay-ui:search-container-column-text name="employee.jobTitle" value="${employee.jobTitle}" />
                    <liferay-ui:search-container-column-text name="employee.phoneNumber" value="${employee.phoneNumber}" />
                    <liferay-ui:search-container-column-text name="employee.companyName" value="${employee.companyNameLabel}" />
                    <liferay-ui:search-container-column-text name="employee.department" value="${employee.departmentLabel}" />
                    <liferay-ui:search-container-column-text name="employee.workLocation" value="${employee.workLocationLabel}" />
                    <liferay-ui:search-container-column-text>
                        <liferay-ui:icon-menu direction="left-side" markupView="lexicon" showWhenSingleIcon="true">
                            <%-- Details --%>
                            <portlet:renderURL var="employeeDetailsURL">
                                <portlet:param name="mvcRenderCommandName" value="<%= EmployeesListPortletKeys.MVC_COMMAND_DETAILS %>" />
                                <portlet:param name="employeeId" value="${employee.employeeId}" />
                            </portlet:renderURL>
                            <liferay-ui:icon message="action.details" url="${employeeDetailsURL}" />
                            <%-- Edit --%>
                            <portlet:renderURL var="editEmployeeURL">
                                <portlet:param name="mvcRenderCommandName" value="<%= EmployeesListPortletKeys.MVC_COMMAND_EDIT %>" />
                                <portlet:param name="employeeId" value="${employee.employeeId}" />
                            </portlet:renderURL>
                            <liferay-ui:icon message="action.edit" url="${editEmployeeURL}" />
                            <%-- Delete --%>
                            <portlet:actionURL name="<%= EmployeesListPortletKeys.MVC_COMMAND_DELETE %>" var="deleteEmployeeURL">
                                <portlet:param name="employeeId" value="${employee.employeeId}" />
                            </portlet:actionURL>
                            <liferay-ui:icon-delete message="action.delete" confirmation="action.delete-confirmation" url="${deleteEmployeeURL}" />
                        </liferay-ui:icon-menu>
                    </liferay-ui:search-container-column-text>
                </liferay-ui:search-container-row>
                <liferay-ui:search-iterator markupView="lexicon" />
            </liferay-ui:search-container>
        </div>
    </div>
</div>