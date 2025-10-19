<%@ include file="init.jsp" %>

<liferay-portlet:renderURL var="backURL" />

<div class="container mt-3">
    <div class="sheet sheet-xl">
        <div class="sheet-header">
            <h2 class="sheet-title">
                <liferay-ui:message key="employees-details" />: ${employee.firstName} ${employee.lastName}
            </h2>
        </div>
        <div class="sheet-section">
            <div class="row">
                <aui:input disabled="true" name="firstName" value="${employee.firstName}"
                           label="employee.firstName" wrapperCssClass="col-md-3" />
                <aui:input disabled="true" name="lastName" value="${employee.lastName}"
                           label="employee.lastName" wrapperCssClass="col-md-3" />
                <aui:input disabled="true" name="email" value="${employee.email}"
                           label="employee.email" wrapperCssClass="col-md-3" />
                <aui:input disabled="true" name="phoneNumber" value="${employee.phoneNumber}"
                           label="employee.phoneNumber" wrapperCssClass="col-md-3" />

                <aui:select disabled="true" name="companyName" value="${employee.companyName}" label="employee.companyName"
                            wrapperCssClass="col-md-3" showEmptyOption="true">
                    <c:forEach var="companyName" items="<%= CompanyName.values() %>">
                        <aui:option value="${companyName.key}" label="${companyName.label}" />
                    </c:forEach>
                </aui:select>
                <aui:select disabled="true" name="jobTitle" value="${employee.jobTitle}" label="employee.jobTitle"
                            wrapperCssClass="col-md-3" showEmptyOption="true">
                    <c:forEach var="jobTitle" items="<%= JobTitle.values() %>">
                        <aui:option value="${jobTitle.label}" label="${jobTitle.label}" />
                    </c:forEach>
                </aui:select>
                <aui:select disabled="true" name="department" value="${employee.department}" label="employee.department"
                            wrapperCssClass="col-md-3" showEmptyOption="true">
                    <c:forEach var="department" items="<%= Department.values() %>">
                        <aui:option value="${department.key}" label="${department.label}" />
                    </c:forEach>
                </aui:select>
                <aui:select disabled="true" name="workLocation" value="${employee.workLocation}" label="employee.workLocation"
                            wrapperCssClass="col-md-3" showEmptyOption="true">
                    <c:forEach var="workLocation" items="<%= WorkLocation.values() %>">
                        <aui:option value="${workLocation.key}" label="${workLocation.label}" />
                    </c:forEach>
                </aui:select>
            </div>
        </div>
        <div class="sheet-footer">

            <aui:button href="${backURL}" name="back" value="btn.back" />
        </div>
    </div>
</div>