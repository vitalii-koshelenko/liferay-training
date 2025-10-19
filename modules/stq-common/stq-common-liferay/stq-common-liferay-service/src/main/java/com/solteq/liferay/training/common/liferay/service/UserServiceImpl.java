package com.solteq.liferay.training.common.liferay.service;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.solteq.liferay.training.common.liferay.api.UserService;
import com.solteq.liferay.training.common.liferay.constants.LocaleConstants;
import com.solteq.liferay.training.common.liferay.util.ServiceContextUtil;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = UserService.class)
public class UserServiceImpl implements UserService {

    private static final String PASSWORD_DEFAULT = "1111";

    @Override
    public User createUser(String firstName, String lastName, String emailAddress, String phoneNumber, String jobTitle) {
        User user = null;
        try {
            ServiceContext serviceContext = ServiceContextUtil.getServiceContext();
            user = userLocalService.addUserWithWorkflow(
                    serviceContext.getUserId(),
                    serviceContext.getCompanyId(),
                    false,
                    PASSWORD_DEFAULT,
                    PASSWORD_DEFAULT,
                    true,
                    null,
                    emailAddress,
                    LocaleConstants.DEFAULT_LOCALE,
                    firstName,
                    null,
                    lastName,
                    0,
                    0,
                    true,
                    0,
                    1,
                    1970,
                    jobTitle,
                    UserConstants.TYPE_REGULAR,
                    new long[]{},
                    new long[]{},
                    new long[]{},
                    new long[]{},
                    false,
                    serviceContext
            );
            user.setPasswordReset(false);
            user = userLocalService.updateUser(user);
        } catch (Exception e) {
            String errorMsg = String.format("Failed to create user %s, cause: %s.", emailAddress, e.getMessage());
            throw new RuntimeException(errorMsg, e);
        }
        return user;
    }

    @Override
    public User updateUser(long userId, String firstName, String lastName, String emailAddress, String phoneNumber, String jobTitle) {
        try {
            // Update user info
            User user = userLocalService.fetchUser(userId);
            if (user == null) {
                throw new RuntimeException(String.format("User with userId=%d not found.", userId));
            }
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmailAddress(emailAddress);
            user.setJobTitle(jobTitle);
            return userLocalService.updateUser(user);
        } catch (Exception e) {
            String errorMsg = String.format("Failed to update user %s, cause: %s.", emailAddress, e.getMessage());
            throw new RuntimeException(errorMsg, e);
        }
    }

    @Reference
    private UserLocalService userLocalService;
}
