/**
 * SPDX-FileCopyrightText: (c) 2025 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.solteq.liferay.training.employees.repository.db.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;

import com.solteq.liferay.training.employees.repository.db.model.STQEmployee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing STQEmployee in entity cache.
 *
 * @author Solteq
 * @generated
 */
public class STQEmployeeCacheModel
	implements CacheModel<STQEmployee>, Externalizable {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof STQEmployeeCacheModel)) {
			return false;
		}

		STQEmployeeCacheModel stqEmployeeCacheModel =
			(STQEmployeeCacheModel)object;

		if (employeeId == stqEmployeeCacheModel.employeeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, employeeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{employeeId=");
		sb.append(employeeId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", email=");
		sb.append(email);
		sb.append(", jobTitle=");
		sb.append(jobTitle);
		sb.append(", phoneNumber=");
		sb.append(phoneNumber);
		sb.append(", companyName=");
		sb.append(companyName);
		sb.append(", department=");
		sb.append(department);
		sb.append(", workLocation=");
		sb.append(workLocation);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public STQEmployee toEntityModel() {
		STQEmployeeImpl stqEmployeeImpl = new STQEmployeeImpl();

		stqEmployeeImpl.setEmployeeId(employeeId);
		stqEmployeeImpl.setCompanyId(companyId);

		if (firstName == null) {
			stqEmployeeImpl.setFirstName("");
		}
		else {
			stqEmployeeImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			stqEmployeeImpl.setLastName("");
		}
		else {
			stqEmployeeImpl.setLastName(lastName);
		}

		if (email == null) {
			stqEmployeeImpl.setEmail("");
		}
		else {
			stqEmployeeImpl.setEmail(email);
		}

		if (jobTitle == null) {
			stqEmployeeImpl.setJobTitle("");
		}
		else {
			stqEmployeeImpl.setJobTitle(jobTitle);
		}

		if (phoneNumber == null) {
			stqEmployeeImpl.setPhoneNumber("");
		}
		else {
			stqEmployeeImpl.setPhoneNumber(phoneNumber);
		}

		if (companyName == null) {
			stqEmployeeImpl.setCompanyName("");
		}
		else {
			stqEmployeeImpl.setCompanyName(companyName);
		}

		if (department == null) {
			stqEmployeeImpl.setDepartment("");
		}
		else {
			stqEmployeeImpl.setDepartment(department);
		}

		if (workLocation == null) {
			stqEmployeeImpl.setWorkLocation("");
		}
		else {
			stqEmployeeImpl.setWorkLocation(workLocation);
		}

		stqEmployeeImpl.resetOriginalValues();

		return stqEmployeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		employeeId = objectInput.readLong();

		companyId = objectInput.readLong();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		email = objectInput.readUTF();
		jobTitle = objectInput.readUTF();
		phoneNumber = objectInput.readUTF();
		companyName = objectInput.readUTF();
		department = objectInput.readUTF();
		workLocation = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(employeeId);

		objectOutput.writeLong(companyId);

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		if (email == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(email);
		}

		if (jobTitle == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(jobTitle);
		}

		if (phoneNumber == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(phoneNumber);
		}

		if (companyName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(companyName);
		}

		if (department == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(department);
		}

		if (workLocation == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(workLocation);
		}
	}

	public long employeeId;
	public long companyId;
	public String firstName;
	public String lastName;
	public String email;
	public String jobTitle;
	public String phoneNumber;
	public String companyName;
	public String department;
	public String workLocation;

}