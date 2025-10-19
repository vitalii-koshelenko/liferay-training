package com.solteq.liferay.training.common.liferay.util;

import com.liferay.portal.kernel.service.ServiceContext;

public final class ServiceContextUtil {

    private ServiceContextUtil() {}

    public static ServiceContext getServiceContext() {
        return getServiceContext(LiferayUtil.getAdminUserId());
    }

    public static ServiceContext getServiceContext(long userId) {
        ServiceContext serviceContext = new ServiceContext();
        serviceContext.setCompanyId(LiferayUtil.getDefaultCompanyId());
        serviceContext.setUserId(userId);
        return serviceContext;
    }

}
