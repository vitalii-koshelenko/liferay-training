[Home](../../README.md) 

# 1. OSGi Basics

### Goals

- Learn OSGi basics, components implementing and referencing;
- Understanding bundle and component lifecycle;
- Hands-on practice on resolving dependencies and working with Gogo Shell;
- Using Groovy Scripts to invoke OSGi services;
- Understanding service ranking for prioritizing components;
- Understanding bundle module types (API, Service, Client);
- Understanding Dynamic and Mutiple references;
- Designing architecture for the OSGi-based application.

### Scope

In the scope of this module, the InMemory repository for the Employee Registry should be implemented, using data for from a JSON file.

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

#### 1.1. API Module

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

#### 1.2. Service Module

Generate a Service module with Blade CLI:

     blade create -t service -d stq-employees -p com.solteq.liferay.training.employees.impl -c EmployeeServiceImpl -s com.solteq.liferay.training.employees.api.EmployeeService stq-employees-impl

Add a dependency on the API module, and implement the `getEmployees()` method to return the list of employees from the [JSON file](../../static-content/data/employees.json):

- Define a `BaseEmployee` class as `Employee` implementation with fields:

```java
    private long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String phoneNumber;
    private String companyName;
    private String department;
    private String workLocation;
```

and `toString()` method implemented to return employee's data in a JSON format:

```java
    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("{");
        sb.append("\"employeeId\": ").append(employeeId);
        sb.append(", \"firstName\": \"").append(firstName).append("\"");
        sb.append(", \"lastName\": \"").append(lastName).append("\"");
        sb.append(", \"email\": \"").append(email).append("\"");
        sb.append(", \"jobTitle\": \"").append(jobTitle).append("\"");
        sb.append(", \"phoneNumber\": \"").append(phoneNumber).append("\"");
        sb.append(", \"companyName\": \"").append(companyName).append("\"");
        sb.append(", \"department\": \"").append(department).append("\"");
        sb.append(", \"workLocation\": \"").append(workLocation).append("\"");
        sb.append('}');
        return sb.toString();
    }
```

- Implement `getEmployees()` method to return the employees from the JSON file:

```java 
    @Override
    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            ClassLoader classLoader = EmployeeServiceImpl.class.getClassLoader();
            String employeesJson = StringUtil.read(classLoader, JSON_PATH);
            JSONArray employeesArray = JSONFactoryUtil.createJSONArray(employeesJson);
            for (Object employeeObj : employeesArray) {
                JSONObject employeeJsonObj = (JSONObject) employeeObj;
                String employeeJson = employeeJsonObj.toString();
                Employee employee = JSONFactoryUtil.looseDeserialize(employeeJson, BaseEmployee.class);
                employees.add(employee);
            }
        } catch (Exception e) {
            _log.error("Error: " + e.getMessage());
        }
        return employees;
    }
```

Deploy the modules `blade gw deploy`.

Observer the exception:

```log
org.osgi.framework.BundleException: Could not resolve module: com.solteq.liferay.training.employees.impl [1489]_  
    Unresolved requirement: Import-Package: com.solteq.liferay.training.employees.model_ [Sanitized]
```

Inspect in Gogo Shell: `lb solteq`, `diag <bundle-id>`, `start <bundle-id>`.

Add `com.solteq.liferay.training.employees.model` package export to `stq-employees-api` module.

Redeploy the module and make sure the exception is gone.

Invoke the service from the Gogo Shell and make sure the correct data is returned.

```groovy
import org.osgi.framework.FrameworkUtil
import com.solteq.liferay.training.employees.model.Employee
import com.solteq.liferay.training.employees.api.EmployeeService

def bundleContext = FrameworkUtil.getBundle(EmployeeService.class).getBundleContext()
def serviceReference = bundleContext.getServiceReference("com.solteq.liferay.training.employees.api.EmployeeService")
if (serviceReference) {
    def employeeService = bundleContext.getService(serviceReference)
    try {
        employees = employeeService.getEmployees()
        println("employeeService: " + employeeService)
        println(employees)
    } catch (Exception e) {
        println("Error: ${e}")
    } finally {
        bundleContext.ungetService(serviceReference)
    }
} else {
    println("EmployeeService service is not available.")
}
```

#### 1.3. Services Priority

Generate another service module with Blade CLI:

    blade create -t service -d stq-employees -p com.solteq.liferay.training.employees.mock -c EmployeeServiceMock -s com.solteq.liferay.training.employees.api.EmployeeService stq-employees-mock

Implement the `EmployeeServiceMock` with mock auto-generated data, and deploy.

Verify with Gradle script, that JSON-based service is still invoked.

Add priority for EmployeeServiceMock component

```java 
    property = {
        "service.ranking:Integer=100"
    },
```

and re-deploy. Make sure mock data service is invoked now. Play around priorities to ensure the highest priority service is invoked.

#### 1.4. Services Client

Create a new module for the service client:

    blade create -t service -d stq-employees -p com.solteq.liferay.training.employees.client -c EmployeeServiceClient -s EmployeeServiceClient stq-employees-client

Implement `EmployeeServiceClient` that uses `EmployeeService` to get the list of employees, and deploy the module:

```java
@Component(
        property = {
        },
        service = EmployeeServiceClient.class
)
public class EmployeeServiceClient  {

    public List<Employee> getEmployees() {
        return employeeService.getEmployees();
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    @Reference
    private EmployeeService employeeService;
}
```

Export `com.solteq.liferay.training.employees.client` package in `bnd.bnd`.

Check the service client with a Groovy script:

```groovy
import org.osgi.framework.FrameworkUtil
import com.solteq.liferay.training.employees.model.Employee
import com.solteq.liferay.training.employees.client.EmployeeServiceClient

def bundleContext = FrameworkUtil.getBundle(EmployeeServiceClient.class).getBundleContext()
def serviceReference = bundleContext.getServiceReference("com.solteq.liferay.training.employees.client.EmployeeServiceClient")
if (serviceReference) {
    def employeeServiceClient = bundleContext.getService(serviceReference)
    try {
        employees = employeeServiceClient.getEmployees()
        println("employeeServiceClient: " + employeeServiceClient)
        println("employeeService: " + employeeServiceClient.getEmployeeService())
        println(employees)
    } catch (Exception e) {
        println("Error: ${e}")
    } finally {
        bundleContext.ungetService(serviceReference)
    }
} else {
    println("EmployeeService service is not available.")
}
```

The service with a higher priority (EmployeeServiceImpl) should be user.

Increase priority for `EmployeeServiceMock` and re-deploy ONLY it's module.
Verify that the old service is still used: in Groovy Scrips, and also in Gogo Shell using `scr info <ComponentName>`.

#### 1.5. Dynamic Services

Adjust `EmployeeServiceClient` to reference employee service in the following way (with `policy` and `policyOption` properties):

```java
    @Reference(
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY
    )
    private volatile EmployeeService employeeService;
```

Make sure services are re-indejected dynamically now.

#### 1.6. Multiple Services

Make employees client referencing multiple services with additional `cardinality` property:

```java
@Component(immediate = true, service = EmployeeServiceClient.class)
public class EmployeeServiceClient  {

    public List<Employee> getEmployees() {
        List<Employee> employees = new ArrayList<>();
        employeeService.forEach(employeeService -> employees.addAll(employeeService.getEmployees()));
        return employees;
    }

    @Reference(
            policy = ReferencePolicy.DYNAMIC,
            policyOption = ReferencePolicyOption.GREEDY,
            cardinality = ReferenceCardinality.MULTIPLE
    )
    private volatile List<EmployeeService> employeeService;
}
```

Make sure employees from both services are returned now.

### 1.7. Implement In-Memory Repository 

Refactor modules to implement In-Memory Employees Repository for the Employee Registry, and a Domain Service referencing the repository in a dynamic way.

Modules structure:

```markdown
modules
└── stq-employees
    └── stq-employees-domain
    |    ├── stq-employees-domain-api // Employee model + EmployeeService
    |    └── stq-employees-domain-impl // EmployeeServiceImpl
    └── stq-employees-repository
        ├── stq-employees-repository-api // EmployeeRepository
        └── stq-employees-repository-inmemory // InMemoryEmployeeRepository
```

Verify refactored service with a Groovy script:

```groovy
import org.osgi.framework.FrameworkUtil
import com.solteq.liferay.training.employees.domain.model.Employee
import com.solteq.liferay.training.employees.domain.api.EmployeeService

def bundleContext = FrameworkUtil.getBundle(EmployeeService.class).getBundleContext()
def serviceReference = bundleContext.getServiceReference("com.solteq.liferay.training.employees.domain.api.EmployeeService")
if (serviceReference) {
    def employeeService = bundleContext.getService(serviceReference)
    try {
        employees = employeeService.getEmployees()
        println("employeeService: " + employeeService)
        println(employees)
    } catch (Exception e) {
        println("Error: ${e}")
    } finally {
        bundleContext.ungetService(serviceReference)
    }
} else {
    println("EmployeeService service is not available.")
}
```

###### © [Vitaliy Koshelenko](https://www.linkedin.com/in/vitaliy-koshelenko) 2025 | Solteq | Jyväskylä, Finland