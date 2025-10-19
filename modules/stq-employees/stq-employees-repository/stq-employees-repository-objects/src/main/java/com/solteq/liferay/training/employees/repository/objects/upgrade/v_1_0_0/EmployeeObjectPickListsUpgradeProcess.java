package com.solteq.liferay.training.employees.repository.objects.upgrade.v_1_0_0;

import com.liferay.list.type.model.ListTypeDefinition;
import com.liferay.list.type.model.ListTypeEntry;
import com.liferay.list.type.service.ListTypeDefinitionLocalService;
import com.liferay.list.type.service.ListTypeEntryLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.domain.enumeration.CompanyName;
import com.solteq.liferay.training.employees.domain.enumeration.Department;
import com.solteq.liferay.training.employees.domain.enumeration.WorkLocation;

import java.util.ArrayList;
import java.util.List;

public class EmployeeObjectPickListsUpgradeProcess extends UpgradeProcess {

    public static final String LABEL_COMPANY_NAME = "Company Name";
    public static final String ERC_COMPANY_NAME = "COMPANY_NAME";

    public static final String LABEL_DEPARTMENT = "Department";
    public static final String ERC_DEPARTMENT = "DEPARTMENT";

    public static final String LABEL_WORK_LOCATION = "Work Location";
    public static final String ERC_WORK_LOCATION = "WORK_LOCATION";

    private final ListTypeDefinitionLocalService listTypeDefinitionLocalService;
    private final ListTypeEntryLocalService listTypeEntryLocalService;

    public EmployeeObjectPickListsUpgradeProcess(ListTypeDefinitionLocalService listTypeDefinitionLocalService, ListTypeEntryLocalService listTypeEntryLocalService) {
        this.listTypeDefinitionLocalService = listTypeDefinitionLocalService;
        this.listTypeEntryLocalService = listTypeEntryLocalService;
    }

    @Override
    protected void doUpgrade() throws Exception {
        long companyId = LiferayUtil.getDefaultCompanyId();
        long userId = LiferayUtil.getAdminUserId();

        // Company Name
        addCompanyNamePickList(companyId, userId);

        // Department
        addDepartmentPickList(companyId, userId);

        // Work Location
        addWorkLocationPickList(companyId, userId);
    }

    private void addCompanyNamePickList(long companyId, long userId) throws PortalException {
        ListTypeDefinition listTypeDefinition = listTypeDefinitionLocalService.fetchListTypeDefinitionByExternalReferenceCode(ERC_COMPANY_NAME, companyId);
        if (listTypeDefinition == null) {
            List<ListTypeEntry> listTypeEntries = new ArrayList<>();
            CompanyName[] companyNames = CompanyName.values();
            for (CompanyName companyName: companyNames) {
                ListTypeEntry listTypeEntry = listTypeEntryLocalService.createListTypeEntry(0L);
                listTypeEntry.setExternalReferenceCode(companyName.name());
                listTypeEntry.setKey(companyName.getKey());
                listTypeEntry.setNameMap(LiferayUtil.getLocalizedMap(companyName.getLabel()));
                listTypeEntries.add(listTypeEntry);
            }
            listTypeDefinition = listTypeDefinitionLocalService.addListTypeDefinition(
                    ERC_COMPANY_NAME,
                    userId,
                    LiferayUtil.getLocalizedMap(LABEL_COMPANY_NAME),
                    false,
                    listTypeEntries
            );
            _log.info(String.format("Added PickList #%d '%s'.", listTypeDefinition.getListTypeDefinitionId(), ERC_COMPANY_NAME));
        }
    }

    private void addDepartmentPickList(long companyId, long userId) throws PortalException {
        ListTypeDefinition listTypeDefinition = listTypeDefinitionLocalService.fetchListTypeDefinitionByExternalReferenceCode(ERC_DEPARTMENT, companyId);
        if (listTypeDefinition == null) {
            List<ListTypeEntry> listTypeEntries = new ArrayList<>();
            Department[] departments = Department.values();
            for (Department department: departments) {
                ListTypeEntry listTypeEntry = listTypeEntryLocalService.createListTypeEntry(0L);
                listTypeEntry.setExternalReferenceCode(department.name());
                listTypeEntry.setKey(department.getKey());
                listTypeEntry.setNameMap(LiferayUtil.getLocalizedMap(department.getLabel()));
                listTypeEntries.add(listTypeEntry);
            }
            listTypeDefinition = listTypeDefinitionLocalService.addListTypeDefinition(
                    ERC_DEPARTMENT,
                    userId,
                    LiferayUtil.getLocalizedMap(LABEL_DEPARTMENT),
                    false,
                    listTypeEntries
            );
            _log.info(String.format("Added PickList #%d '%s'.", listTypeDefinition.getListTypeDefinitionId(), ERC_DEPARTMENT));
        }
    }

    private void addWorkLocationPickList(long companyId, long userId) throws PortalException {
        ListTypeDefinition listTypeDefinition = listTypeDefinitionLocalService.fetchListTypeDefinitionByExternalReferenceCode(ERC_WORK_LOCATION, companyId);
        if (listTypeDefinition == null) {
            List<ListTypeEntry> listTypeEntries = new ArrayList<>();
            WorkLocation[] workLocations = WorkLocation.values();
            for (WorkLocation workLocation: workLocations) {
                ListTypeEntry listTypeEntry = listTypeEntryLocalService.createListTypeEntry(0L);
                listTypeEntry.setExternalReferenceCode(workLocation.name());
                listTypeEntry.setKey(workLocation.getKey());
                listTypeEntry.setNameMap(LiferayUtil.getLocalizedMap(workLocation.getLabel()));
                listTypeEntries.add(listTypeEntry);
            }
            listTypeDefinition = listTypeDefinitionLocalService.addListTypeDefinition(
                    ERC_WORK_LOCATION,
                    userId,
                    LiferayUtil.getLocalizedMap(LABEL_WORK_LOCATION),
                    false,
                    listTypeEntries
            );
            _log.info(String.format("Added PickList #%d '%s'.", listTypeDefinition.getListTypeDefinitionId(), ERC_WORK_LOCATION));
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(EmployeeObjectPickListsUpgradeProcess.class);
}