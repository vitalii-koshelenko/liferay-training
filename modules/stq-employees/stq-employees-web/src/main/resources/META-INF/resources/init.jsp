<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page import="com.liferay.portal.kernel.util.GetterUtil" %>
<%@ page import="com.solteq.liferay.training.employees.domain.api.EmployeeService " %>
<%@ page import="com.solteq.liferay.training.employees.domain.enumeration.CompanyName" %>
<%@ page import="com.solteq.liferay.training.employees.domain.enumeration.Department" %>
<%@ page import="com.solteq.liferay.training.employees.domain.enumeration.JobTitle" %>
<%@ page import="com.solteq.liferay.training.employees.domain.enumeration.WorkLocation" %>
<%@ page import="com.solteq.liferay.training.employees.domain.exception.EmployeeValidationException" %>
<%@ page import="com.solteq.liferay.training.employees.domain.filter.EmployeeFilter" %>
<%@ page import="com.solteq.liferay.training.employees.web.constants.EmployeesListKeys" %>
<%@ page import="com.solteq.liferay.training.employees.web.constants.EmployeesListPortletKeys" %>


<liferay-theme:defineObjects/>
<portlet:defineObjects/>