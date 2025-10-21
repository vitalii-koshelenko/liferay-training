[Home](../../README.md) 

# 3. Service Builder

### Goals

- Understanding Service Builder's concept;
- Hands-on experience on Service Builder implementation;
- Implementation of custom methods;
- Understanding model-hints;
- Familiarizing with DSL.

### Scope

In the scope of this module, the Database repository for the Employee Registry should be implemented, using Service Builder.

### Instructions

#### 3.1. Service Builder Module Generation

Generate Service Builder module:

    blade create -t service-builder -d stq-employees/stq-employees-repository -p com.solteq.liferay.training.employees.repository.db stq-employees-repository-db

Define service.xml

```xml
<?xml version="1.0"?>
<!DOCTYPE service-builder PUBLIC "-//Liferay//DTD Service Builder 7.4.0//EN" "http://www.liferay.com/dtd/liferay-service-builder_7_4_0.dtd">

<service-builder dependency-injector="ds" package-path="com.solteq.liferay.training.employees.repository.db">

    <author>Solteq</author>
    <namespace>stq</namespace>

    <entity name="STQEmployee" local-service="true" remote-service="false">

        <!-- PK fields -->
        <column name="employeeId" primary="true" type="long" />

        <!-- Other fields -->
        <column name="firstName" type="String" />
        <column name="lastName" type="String" />
        <column name="email" type="String" />
        <column name="jobTitle" type="String" />
        <column name="phoneNumber" type="String" />
        <column name="companyName" type="String" />
        <column name="department" type="String" />
        <column name="workLocation" type="String" />

        <!-- Finder methods -->
        <finder name="Email" return-type="STQEmployee" unique="true">
            <finder-column name="email" />
        </finder>

    </entity>

</service-builder>
```

Adjust bnd.bnd files and add dependency on stq-employees-domain module.

Run `buildService` to generate Service Builder sources based on service.xml.

Implement define getEmployeeByEmail method in EmployeeServiceImpl, and re-generate Service Builder.

#### 3.2. Database Repository Implementation

Make STQEmployeeImpl implement Employee interface and re-generate Service Builder.

Implement DatabaseEmployeeRepository that delegates calls to STQEmployeeLocalServiceImpl.

###### © [Vitaliy Koshelenko](https://www.linkedin.com/in/vitaliy-koshelenko) 2025 | Solteq | Jyväskylä, Finland