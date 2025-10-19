[Home](../../README.md) 

# 1. OSGi Basics

### Goals

- Learn OSGi basics, components implementing and referencing;
- Understanding bundle and component lifecycle;
- Hands-on practice on resolving dependencies;
- Using Groovy Scripts to invoke OSGi services;
- Understanding service ranking for prioritizing components;
- Understanding bundle module types (API, Service, Client);
- Understanding Dynamic and Mutiple references. 

### Scope

In a scope of this module the InMemory repository for the Employee Registry should be implemented, using data for from a JSON file.

```markdown
modules
└── stq-employees
    └── stq-employees-domain
    |    ├── stq-employees-domain-api
    |    └── stq-employees-domain-impl
    └── stq-employees-repository
        ├── stq-employees-repository-api
        └── stq-employees-repository-inmemory
```

### Instructions

#### 1. API Module

Navigate to `modules` directory in the project's workspace.

Generate an API module with Blade CLI:

    blade create -t api -d stq-employees -p com.solteq.liferay.training.employees -c EmployeeService stq-employees-api

Create an additional `com.solteq.liferay.training.employees.model` package inside the generated module and add `Employee` interface:

```java
public interface Employee {

    long getEmployeeId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getJobTitle();

    String getPhoneNumber();

    String getCompanyName();

    String getDepartment();

    String getWorkLocation();
}
```

Make `EmployeeService` return the list of employees:

```java
public interface EmployeeService {
    
    List<Employee> getEmployees();
    
}
```

Adjust `Bundle-Name` in `bnd.bnd` file according to Code Conventions, and deploy the module.

Verify in Gogo Shell that the module is deployed and Active using `lb solteq`.

#### 2. Service Module

Generate a Service module with Blade CLI:

     blade create -t service -d stq-employees -p com.solteq.liferay.training.employees.impl -c EmployeeServiceImpl -s com.solteq.liferay.training.employees.api.EmployeeService stq-employees-impl

Add a dependency on the API module, and implement the `getEmployees()` method to return a mock list of employees from the [JSON file](../../static-content/data/employees.json).




###### © [Vitaliy Koshelenko](https://www.linkedin.com/in/vitaliy-koshelenko) 2025 | Solteq | Jyväskylä, Finland