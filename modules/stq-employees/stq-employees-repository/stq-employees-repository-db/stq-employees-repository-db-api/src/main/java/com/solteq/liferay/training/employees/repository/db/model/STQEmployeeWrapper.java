/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.model;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link STQEmployee}.
 * </p>
 *
 * @author Solteq
 * @see STQEmployee
 * @generated
 */
public class STQEmployeeWrapper
	extends BaseModelWrapper<STQEmployee>
	implements ModelWrapper<STQEmployee>, STQEmployee {

	public STQEmployeeWrapper(STQEmployee stqEmployee) {
		super(stqEmployee);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("employeeId", getEmployeeId());
		attributes.put("companyId", getCompanyId());
		attributes.put("firstName", getFirstName());
		attributes.put("lastName", getLastName());
		attributes.put("email", getEmail());
		attributes.put("jobTitle", getJobTitle());
		attributes.put("phoneNumber", getPhoneNumber());
		attributes.put("companyName", getCompanyName());
		attributes.put("department", getDepartment());
		attributes.put("workLocation", getWorkLocation());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long employeeId = (Long)attributes.get("employeeId");

		if (employeeId != null) {
			setEmployeeId(employeeId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		String firstName = (String)attributes.get("firstName");

		if (firstName != null) {
			setFirstName(firstName);
		}

		String lastName = (String)attributes.get("lastName");

		if (lastName != null) {
			setLastName(lastName);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}

		String jobTitle = (String)attributes.get("jobTitle");

		if (jobTitle != null) {
			setJobTitle(jobTitle);
		}

		String phoneNumber = (String)attributes.get("phoneNumber");

		if (phoneNumber != null) {
			setPhoneNumber(phoneNumber);
		}

		String companyName = (String)attributes.get("companyName");

		if (companyName != null) {
			setCompanyName(companyName);
		}

		String department = (String)attributes.get("department");

		if (department != null) {
			setDepartment(department);
		}

		String workLocation = (String)attributes.get("workLocation");

		if (workLocation != null) {
			setWorkLocation(workLocation);
		}
	}

	@Override
	public STQEmployee cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this stq employee.
	 *
	 * @return the company ID of this stq employee
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the company name of this stq employee.
	 *
	 * @return the company name of this stq employee
	 */
	@Override
	public String getCompanyName() {
		return model.getCompanyName();
	}

	/**
	 * Returns the department of this stq employee.
	 *
	 * @return the department of this stq employee
	 */
	@Override
	public String getDepartment() {
		return model.getDepartment();
	}

	/**
	 * Returns the email of this stq employee.
	 *
	 * @return the email of this stq employee
	 */
	@Override
	public String getEmail() {
		return model.getEmail();
	}

	/**
	 * Returns the employee ID of this stq employee.
	 *
	 * @return the employee ID of this stq employee
	 */
	@Override
	public long getEmployeeId() {
		return model.getEmployeeId();
	}

	/**
	 * Returns the first name of this stq employee.
	 *
	 * @return the first name of this stq employee
	 */
	@Override
	public String getFirstName() {
		return model.getFirstName();
	}

	@Override
	public String getFullName() {
		return model.getFullName();
	}

	/**
	 * Returns the job title of this stq employee.
	 *
	 * @return the job title of this stq employee
	 */
	@Override
	public String getJobTitle() {
		return model.getJobTitle();
	}

	/**
	 * Returns the last name of this stq employee.
	 *
	 * @return the last name of this stq employee
	 */
	@Override
	public String getLastName() {
		return model.getLastName();
	}

	/**
	 * Returns the phone number of this stq employee.
	 *
	 * @return the phone number of this stq employee
	 */
	@Override
	public String getPhoneNumber() {
		return model.getPhoneNumber();
	}

	/**
	 * Returns the primary key of this stq employee.
	 *
	 * @return the primary key of this stq employee
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the work location of this stq employee.
	 *
	 * @return the work location of this stq employee
	 */
	@Override
	public String getWorkLocation() {
		return model.getWorkLocation();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this stq employee.
	 *
	 * @param companyId the company ID of this stq employee
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the company name of this stq employee.
	 *
	 * @param companyName the company name of this stq employee
	 */
	@Override
	public void setCompanyName(String companyName) {
		model.setCompanyName(companyName);
	}

	/**
	 * Sets the department of this stq employee.
	 *
	 * @param department the department of this stq employee
	 */
	@Override
	public void setDepartment(String department) {
		model.setDepartment(department);
	}

	/**
	 * Sets the email of this stq employee.
	 *
	 * @param email the email of this stq employee
	 */
	@Override
	public void setEmail(String email) {
		model.setEmail(email);
	}

	/**
	 * Sets the employee ID of this stq employee.
	 *
	 * @param employeeId the employee ID of this stq employee
	 */
	@Override
	public void setEmployeeId(long employeeId) {
		model.setEmployeeId(employeeId);
	}

	/**
	 * Sets the first name of this stq employee.
	 *
	 * @param firstName the first name of this stq employee
	 */
	@Override
	public void setFirstName(String firstName) {
		model.setFirstName(firstName);
	}

	/**
	 * Sets the job title of this stq employee.
	 *
	 * @param jobTitle the job title of this stq employee
	 */
	@Override
	public void setJobTitle(String jobTitle) {
		model.setJobTitle(jobTitle);
	}

	/**
	 * Sets the last name of this stq employee.
	 *
	 * @param lastName the last name of this stq employee
	 */
	@Override
	public void setLastName(String lastName) {
		model.setLastName(lastName);
	}

	/**
	 * Sets the phone number of this stq employee.
	 *
	 * @param phoneNumber the phone number of this stq employee
	 */
	@Override
	public void setPhoneNumber(String phoneNumber) {
		model.setPhoneNumber(phoneNumber);
	}

	/**
	 * Sets the primary key of this stq employee.
	 *
	 * @param primaryKey the primary key of this stq employee
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the work location of this stq employee.
	 *
	 * @param workLocation the work location of this stq employee
	 */
	@Override
	public void setWorkLocation(String workLocation) {
		model.setWorkLocation(workLocation);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	protected STQEmployeeWrapper wrap(STQEmployee stqEmployee) {
		return new STQEmployeeWrapper(stqEmployee);
	}

}