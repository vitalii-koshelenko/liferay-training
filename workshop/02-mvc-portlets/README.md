[Home](../../README.md) 

# 2. MVC Portlets

### Goals

- Understanding portlets implementation basics; 
- Hands-on practice in MVC Commands implementation; 
- Leveraging Search Containers for paginated views;
- CRUD operations implementation;
- Filtering and search options. 

### Scope

In the scope of this module, the UI layer for Employee Registry should be implemented, as an MVC Portlet.

### Instructions

#### 1.1. Widget Module Generation

Navigate to `modules` directory in the project's workspace.

Generate an `mvc-portlet` module with Blade CLI:

     blade create -t mvc-portlet -d stq-employees -p com.solteq.liferay.training.employees.web -c EmployeesListPortlet stq-employees-web

Explore the generated sourced, deploy the widget module and add it to a portal page.

#### 1.2. Displaying Employees

Add dependency on `stq-employees-domain-api` module.

Move the portlet class to `employeelist` package, and adjust in this way:

```java 
@Component(
        property = {
                "com.liferay.portlet.display-category=" + EmployeesListPortletKeys.CATEGORY_NAME,
                "com.liferay.portlet.instanceable=false",
                "jakarta.portlet.display-name=" + EmployeesListPortletKeys.DISPLAY_NAME,
                "jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID,
                "jakarta.portlet.resource-bundle=content.Language",
                "jakarta.portlet.security-role-ref=power-user,user",
                "com.liferay.portlet.header-portlet-css=/employees-list/css/main.css",
                "com.liferay.portlet.header-portlet-javascript=/employees-list/js/main.js",
                "com.liferay.portlet.css-class-wrapper=" + EmployeesListPortletKeys.CSS_CLASS_WRAPPER,
        },
        service = Portlet.class
)
public class EmployeesListPortlet extends MVCPortlet {

}
```

_**Note**: make sure to use **jakarta.*** packages in the latest Liferay versions (2025.Q3+)_

Also, adjust the `EmployeesListPortletKeys` constants class in this way:

```java
public class EmployeesListPortletKeys {

    public static final String CATEGORY_NAME = "Solteq Training";

    public static final String DISPLAY_NAME = "Employee List";

    public static final String PORTLET_ID = "com_solteq_liferay_training_employees_web_EmployeesListPortlet";

    public static final String MVC_COMMAND_DEFAULT = "/";
    
    public static final String VIEW_JSP = "/employees-list/view.jsp";

    public static final String CSS_CLASS_WRAPPER = "employees-list-wrapper";
}
```
Define `employees-list` directory inside `resources` and adjust/restructure FE files as well (see final version).

Implement the `ViewMVCRenderCommand` command:

```java
@Component(
        property = {
                "jakarta.portlet.name=" + EmployeesListPortletKeys.PORTLET_ID,
                "mvc.command.name=" + EmployeesListPortletKeys.MVC_COMMAND_DEFAULT,
        },
        service = MVCRenderCommand.class
)
public class ViewMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
        List<Employee> employees = employeeService.getEmployees();
        renderRequest.setAttribute(EmployeesListKeys.EMPLOYEES, employees);
        return EmployeesListPortletKeys.VIEW_JSP;
    }

    @Reference
    private EmployeeService employeeService;
}
```

Render a list of employees on the `view.jsp`:

```html
<div class="table-responsive">
    <table class="table table-autofit table-head-bordered table-heading-nowrap table-hover table-list table-striped">
        <thead>
            <tr>
                <td>ID</td>
                <td>First Name</td>
                <td>Last Name</td>
                <td>Email</td>
                <td>Job Title</td>
                <td>Phone Number</td>
                <td>Company Name</td>
                <td>Department</td>
                <td>Work Location</td>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="employee" items="${employees}">
                <tr>
                    <td>${employee.employeeId}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.email}</td>
                    <td>${employee.jobTitle}</td>
                    <td>${employee.phoneNumber}</td>
                    <td>${employee.companyName}</td>
                    <td>${employee.department}</td>
                    <td>${employee.workLocation}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
```

#### 1.3. Pagination and Filtering

Copy `constants`, `enumeration` and `filter` packages from the final version to the Domain API.

Add default methods for `Employee` interface (for list labels):

```java 
    default String getCompanyNameLabel() {
        CompanyName companyName = CompanyName.fromKey(getCompanyName());
        return companyName != null ? companyName.getLabel() : getCompanyName();
    }

    default String getDepartmentLabel() {
        Department department = Department.fromKey(getDepartment());
        return department != null ? department.getLabel() : getDepartment();
    }

    default String getWorkLocationLabel() {
        WorkLocation workLocation = WorkLocation.fromKey(getWorkLocation());
        return workLocation != null ? workLocation.getLabel() : getWorkLocation();
    }
```

Add additional methods for `EmployeeService` for pagination/filtering support:

```java
    default int getEmployeeCount() {
        return getEmployeeCount(null);
    }

    default List<Employee> getEmployees(int start, int end) {
        return getEmployees(start, end, null);
    }

    int getEmployeeCount(EmployeeFilter filter);

    List<Employee> getEmployees(int start, int end, EmployeeFilter filter);
```

Also, add similar methods for `EmployeeRepository`:

```java
    int fetchEmployeeCount(EmployeeFilter filter);

    List<Employee> fetchEmployees(int start, int end, EmployeeFilter filter);
```
Call repository methods from service implementation, copy InMemory service implementation from the final version.

Adjust the `ViewMVCRenderCommand` implementation (handle filters and pass service reference to JSP):

```java
@Override
public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {

    String searchTerm = ParamUtil.getString(renderRequest, EmployeesListKeys.SEARCH_TERM);
    String companyName = ParamUtil.getString(renderRequest, EmployeesListKeys.COMPANY_NAME);
    String jobTitle = ParamUtil.getString(renderRequest, EmployeesListKeys.JOB_TITLE);
    String department = ParamUtil.getString(renderRequest, EmployeesListKeys.DEPARTMENT);
    String workLocation = ParamUtil.getString(renderRequest, EmployeesListKeys.WORK_LOCATION);
    EmployeeFilter employeeFilter = EmployeeFilter.of(searchTerm, jobTitle, companyName, department, workLocation);
    renderRequest.setAttribute(EmployeesListKeys.EMPLOYEE_FILTER, employeeFilter);

    renderRequest.setAttribute(EmployeeService.class.getName(), employeeService);

    return EmployeesListPortletKeys.VIEW_JSP;
}
```

Adjust the `view.jsp` to use search container and filters:

```html
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
                        
                    </liferay-ui:search-container-column-text>
                </liferay-ui:search-container-row>
                <liferay-ui:search-iterator markupView="lexicon" />
            </liferay-ui:search-container>
        </div>
    </div>
</div>
```

Re-deploy the module.

Check if `java.lang.ClassNotFoundException: com.solteq.liferay.training.employees.domain.model.Employee` exception occurs, try to understand why.

Add `Import-Package: com.solteq.liferay.training.employees.domain.model,*` to web module's `bnd.bnd`.

#### 1.4. Actions

Implement actions to View Details, Edit and Delete employees. Also, add a button to create new employees.

Add methods to `EmployeeService`:

```java
    Employee getEmployeeById(long employeeId);

    Employee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber,
                          String companyName, String jobTitle, String department, String workLocation) throws EmployeeValidationException;

    void deleteEmployee(long employeeId);
```

Also, add methods to `EmployeeRepository`:

```java
    Employee fetchEmployeeById(long employeeId);

    Employee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber,
                          String companyName, String jobTitle, String department, String workLocation);

    void deleteEmployee(long employeeId);
```

Add constants to `EmployeesListPortletKeys`:

```java
    public static final String MVC_COMMAND_DETAILS = "/employee/details";
    public static final String MVC_COMMAND_EDIT = "/employee/edit";
    public static final String MVC_COMMAND_DELETE = "/employee/delete";

    public static final String DETAILS_JSP = "/employees-list/details.jsp";
    public static final String EDIT_JSP = "/employees-list/edit.jsp";

```

Implement service methods to delegate calls to еру repository, copy In-Memory repository implementation from the final version.

Copy additional MVC Commands:
- DeleteMVCActionCommand
- DetailsMVCRenderCommand
- EditMVCActionCommand
- EditMVCRenderCommand

Copy additional JSPs: details.jsp, edit.jsp.

Insert Add button

```html
    <div>
        <aui:button href="${addEmployeeURL}" primary="true" name="addEmployee" value="btn.add" />
    </div>
```

and actions

```html
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
```

on the view.jsp.

Re-deploy modules and check CRUD functionality.

###### © [Vitaliy Koshelenko](https://www.linkedin.com/in/vitaliy-koshelenko) 2025 | Solteq | Jyväskylä, Finland