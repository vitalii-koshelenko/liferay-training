<%@ include file="init.jsp" %>

<portlet:renderURL var="backURL" />
<portlet:actionURL name="<%= EmployeesListPortletKeys.MVC_COMMAND_EDIT %>" var="editEmployeeURL" />

<div class="container mt-3">
    <div class="sheet sheet-xl">
        <div class="sheet-header">
            <h2>
                <c:choose>
                    <c:when test="${not empty employee.employeeId}">
                        <liferay-ui:message key="edit-employee" />: ${employee.firstName} ${employee.lastName}
                    </c:when>
                    <c:otherwise>
                        <liferay-ui:message key="add-employee" />
                    </c:otherwise>
                </c:choose>
            </h2>
        </div>
        <div class="sheet-section">
            <aui:form action="${editEmployeeURL}" cssClass="container-fluid container-fluid-max-xl container-form-lg" method="post" name="fm">

                <liferay-ui:error exception="<%= EmployeeValidationException.FirstNameRequired.class %>" message="error.first-name-required" />
                <liferay-ui:error exception="<%= EmployeeValidationException.LastNameRequired.class %>" message="error.last-name-required" />
                <liferay-ui:error exception="<%= EmployeeValidationException.EmailRequired.class %>" message="error.email-required" />

                <aui:input type="hidden" name="employeeId" value="${employee.employeeId}" />
                <div class="container mt-3">
                    <div class="row">
                        <aui:input name="firstName" value="${employee.firstName}"
                                   label="employee.firstName" wrapperCssClass="col-md-3" />
                        <aui:input name="lastName" value="${employee.lastName}"
                                   label="employee.lastName" wrapperCssClass="col-md-3" />
                        <aui:input name="email" value="${employee.email}"
                                   label="employee.email" wrapperCssClass="col-md-3" />
                        <aui:input name="phoneNumber" value="${employee.phoneNumber}"
                                   label="employee.phoneNumber" wrapperCssClass="col-md-3" />

                        <aui:select name="companyName" value="${employee.companyName}" label="employee.companyName"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="companyName" items="<%= CompanyName.values() %>">
                                <aui:option value="${companyName.key}" label="${companyName.label}" />
                            </c:forEach>
                        </aui:select>
                        <aui:select name="jobTitle" value="${employee.jobTitle}" label="employee.jobTitle"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="jobTitle" items="<%= JobTitle.values() %>">
                                <aui:option value="${jobTitle.label}" label="${jobTitle.label}" />
                            </c:forEach>
                        </aui:select>
                        <aui:select name="department" value="${employee.department}" label="employee.department"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="department" items="<%= Department.values() %>">
                                <aui:option value="${department.key}" label="${department.label}" />
                            </c:forEach>
                        </aui:select>
                        <aui:select name="workLocation" value="${employee.workLocation}" label="employee.workLocation"
                                    wrapperCssClass="col-md-3" showEmptyOption="true">
                            <c:forEach var="workLocation" items="<%= WorkLocation.values() %>">
                                <aui:option value="${workLocation.key}" label="${workLocation.label}" />
                            </c:forEach>
                        </aui:select>
                    </div>
                    <aui:button-row cssClass="text-right">
                        <aui:button href="${backURL}" name="back" value="btn.cancel" />
                        <aui:button type="submit" value="btn.save" />
                    </aui:button-row>
                </div>
            </aui:form>
        </div>
    </div>
</div>