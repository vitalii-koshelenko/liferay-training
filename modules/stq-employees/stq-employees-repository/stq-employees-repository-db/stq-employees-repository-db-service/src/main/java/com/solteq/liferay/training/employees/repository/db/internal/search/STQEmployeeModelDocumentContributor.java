package com.solteq.liferay.training.employees.repository.db.internal.search;

import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.search.spi.model.index.contributor.ModelDocumentContributor;
import com.solteq.liferay.training.employees.domain.search.STQEmployeeField;
import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;
import org.osgi.service.component.annotations.Component;


@Component(
	property = "indexer.class.name=com.solteq.liferay.training.employees.repository.db.model.STQEmployee",
	service = ModelDocumentContributor.class
)
public class STQEmployeeModelDocumentContributor implements ModelDocumentContributor<STQEmployee> {

	@Override
	public void contribute(Document document, STQEmployee employee) {
		document.addNumber(STQEmployeeField.COMPANY_ID, employee.getCompanyId());
		document.addNumber(STQEmployeeField.EMPLOYEE_ID, employee.getEmployeeId());
		document.addText(STQEmployeeField.FIRST_NAME, employee.getFirstName());
		document.addText(STQEmployeeField.LAST_NAME, employee.getLastName());
		document.addKeyword(STQEmployeeField.EMAIL, employee.getEmail());
		document.addKeyword(STQEmployeeField.JOB_TITLE, employee.getJobTitle());
		document.addKeyword(STQEmployeeField.PHONE_NUMBER, employee.getPhoneNumber());
		document.addText(STQEmployeeField.COMPANY_NAME, employee.getCompanyName());
		document.addText(STQEmployeeField.DEPARTMENT, employee.getDepartment());
		document.addText(STQEmployeeField.WORK_LOCATION, employee.getWorkLocation());
	}

}