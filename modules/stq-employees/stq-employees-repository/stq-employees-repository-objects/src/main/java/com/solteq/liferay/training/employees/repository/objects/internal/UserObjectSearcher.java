package com.solteq.liferay.training.employees.repository.objects.internal;

import com.liferay.portal.kernel.model.User;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;

import java.util.List;

public interface UserObjectSearcher {

    int searchUsersCount(EmployeeFilter filter);

    List<User> searchUsers(int start, int end, EmployeeFilter filter);

}