package com.solteq.liferay.training.common.liferay.api;

import com.liferay.portal.kernel.model.User;

public interface UserService {

    User createUser(String firstName, String lastName, String emailAddress, String phoneNumber, String jobTitle);

    User updateUser(long userId, String firstName, String lastName, String emailAddress, String phoneNumber, String jobTitle);

}