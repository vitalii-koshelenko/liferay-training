package com.solteq.liferay.training.employees.domain.enumeration;

import com.liferay.portal.kernel.util.StringUtil;
import com.solteq.liferay.training.employees.domain.constants.WorkLocationConstants;

public enum WorkLocation {

    JYVASKYLA(WorkLocationConstants.JYVASKYLA, WorkLocationConstants.JYVASKYLA_LABEL),
    TAMPERE(WorkLocationConstants.TAMPERE, WorkLocationConstants.TAMPERE_LABEL),
    TURKU(WorkLocationConstants.TURKU, WorkLocationConstants.TURKU_LABEL),
    ESPOO(WorkLocationConstants.ESPOO, WorkLocationConstants.ESPOO_LABEL),
    BRATISLAVA(WorkLocationConstants.BRATISLAVA, WorkLocationConstants.BRATISLAVA_LABEL);

    private final String key;
    private final String label;

    WorkLocation(String key, String label) {
        this.key = key;
        this.label = label;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public static WorkLocation fromKey(String key) {
        for (WorkLocation workLocation : WorkLocation.values()) {
            if (StringUtil.equalsIgnoreCase(workLocation.getKey(), key)) {
                return workLocation;
            }
        }
        return null;
    }

}