package com.solteq.liferay.training.employees.repository.objects.upgrade.v_1_0_1;

import com.liferay.object.admin.rest.dto.v1_0.ObjectField;
import com.liferay.object.admin.rest.resource.v1_0.ObjectFieldResource;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.util.StringUtil;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.repository.objects.constants.UserObjectConstants;

public class EmployeeObjectFieldsUpgradeProcess extends UpgradeProcess {

    public static final String JSON_PATH = "upgrade/v_1_0_1/object-fields.json";

    private final ObjectFieldResource.Factory objectFieldResourceFactory;

    public EmployeeObjectFieldsUpgradeProcess(ObjectFieldResource.Factory objectFieldResourceFactory) {
        this.objectFieldResourceFactory = objectFieldResourceFactory;
    }

    @Override
    protected void doUpgrade() throws Exception {
        ObjectFieldResource objectFieldResource = objectFieldResourceFactory.create().user(LiferayUtil.getAdminUser()).build();
        ClassLoader classLoader = EmployeeObjectFieldsUpgradeProcess.class.getClassLoader();
        String objectFieldsJson = StringUtil.read(classLoader, JSON_PATH);
        JSONArray objectFieldsJsonArray = JSONFactoryUtil.createJSONArray(objectFieldsJson);
        for (Object objectFieldJson : objectFieldsJsonArray) {
            JSONObject objectFieldJsonObject = (JSONObject) objectFieldJson;
            ObjectField objectField = objectFieldResource.postObjectDefinitionByExternalReferenceCodeObjectField(
                    UserObjectConstants.ERC_USER,
                    ObjectField.toDTO(objectFieldJsonObject.toString())
            );
            _log.info(String.format("Added object field #%d '%s' for Object '%s'.", objectField.getId(), objectField.getName(), UserObjectConstants.ERC_USER));
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(EmployeeObjectFieldsUpgradeProcess.class);
}