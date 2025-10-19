package com.solteq.liferay.training.employees.repository.inmemory;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import com.solteq.liferay.training.employees.repository.inmemory.model.EmployeeImpl;
import org.osgi.service.component.annotations.Component;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "repository.type=in-memory",
                "service.ranking:Integer=101"
        },
        service = EmployeeRepository.class
)
public class InMemoryEmployeeRepository implements EmployeeRepository {

    private List<Employee> employees; // In-Memory Storage

    private static final String EMPLOYEES_JSON_PATH = "data/employees.json";

    @Override
    public List<Employee> fetchEmployees() {
        if (ListUtil.isEmpty(employees)) {
            employees = loadEmployees();
        }
        return employees;
    }

    @Override
    public int fetchEmployeeCount(EmployeeFilter filter) {
        List<Employee> employees = getMatchingEmployees(filter);
        return ListUtil.isNotEmpty(employees) ? employees.size() : 0;
    }

    @Override
    public List<Employee> fetchEmployees(int start, int end, EmployeeFilter filter) {
        List<Employee> employees = getMatchingEmployees(filter);
        return ListUtil.subList(employees, start, end);
    }

    @Override
    public Employee fetchEmployeeById(long employeeId) {
        List<Employee> employees = fetchEmployees();
        if (ListUtil.isEmpty(employees)) {
            return null;
        }
        return employees.stream().filter(employee -> employee.getEmployeeId() == employeeId)
                .findFirst().orElse(null);
    }

    @Override
    public Employee saveEmployee(long employeeId, String firstName, String lastName, String email,
                                 String phoneNumber, String companyName, String jobTitle, String department, String workLocation) {
        Employee employee = null;
        if (Validator.isNull(employeeId)) {
            employeeId = getNextId();
            employee = new EmployeeImpl();
            ((EmployeeImpl)employee).setEmployeeId(employeeId);
            employees.add(employee);
        } else {
            employee = fetchEmployeeById(employeeId);
            if (employee == null) {
                _log.warn("Employee with id " + employeeId + " not found");
                return null;
            }
        }
        ((EmployeeImpl)employee).setFirstName(firstName);
        ((EmployeeImpl)employee).setLastName(lastName);
        ((EmployeeImpl)employee).setEmail(email);
        ((EmployeeImpl)employee).setPhoneNumber(phoneNumber);
        ((EmployeeImpl)employee).setCompanyName(companyName);
        ((EmployeeImpl)employee).setJobTitle(jobTitle);
        ((EmployeeImpl)employee).setDepartment(department);
        ((EmployeeImpl)employee).setWorkLocation(workLocation);
        return employee;
    }

    @Override
    public void deleteEmployee(long employeeId) {
        Employee employee = fetchEmployeeById(employeeId);
        if (employee == null) {
            _log.warn("Employee with id " + employeeId + " not found");
            return;
        }
        employees.remove(employee);
    }

    private long getNextId() {
        long maxId = 0;
        for (Employee employee : employees) {
            if (employee.getEmployeeId() > maxId) {
                maxId = employee.getEmployeeId();
            }
        }
        return maxId + 1;
    }

    private List<Employee> getMatchingEmployees(EmployeeFilter filter) {
        List<Employee> employees = fetchEmployees();
        if (filter == null || ListUtil.isEmpty(employees)) {
            return employees;
        }
        List<Employee> filteredEmployees = new ArrayList<>();
        for (Employee employee : employees) {
            String companyName = filter.getCompanyName();
            if (Validator.isNotNull(companyName) && !StringUtil.equalsIgnoreCase(companyName, employee.getCompanyName())) {
                continue;
            }
            String department = filter.getDepartment();
            if (Validator.isNotNull(department) && !StringUtil.equalsIgnoreCase(department, employee.getDepartment())) {
                continue;
            }
            String workLocation = filter.getWorkLocation();
            if (Validator.isNotNull(workLocation) && !StringUtil.equalsIgnoreCase(workLocation, employee.getWorkLocation())) {
                continue;
            }
            String jobTitle = filter.getJobTitle();
            if (Validator.isNotNull(jobTitle) && !StringUtil.equalsIgnoreCase(jobTitle, employee.getJobTitle())) {
                continue;
            }
            String search = filter.getSearch();
            if (Validator.isNotNull(search)) {
                String searchTerm = normalize(search);
                String firstName = normalize(employee.getFirstName());
                String lastName = normalize(employee.getLastName());
                String email = normalize(employee.getEmail());
                boolean searchMatch = StringUtil.equals(firstName, searchTerm) || StringUtil.equals(lastName, searchTerm)
                        || StringUtil.equals(email, searchTerm);
                if (!searchMatch) {
                    continue;
                }
            }
            filteredEmployees.add(employee);
        }
        return filteredEmployees;
    }

    private String normalize(String str) {
        if (Validator.isNull(str)) {
            return StringPool.BLANK;
        }
        return str.toLowerCase().trim();
    }

    private List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            ClassLoader classLoader = InMemoryEmployeeRepository.class.getClassLoader();
            String employeesJson = StringUtil.read(classLoader, EMPLOYEES_JSON_PATH);
            JSONArray employeesArray = JSONFactoryUtil.createJSONArray(employeesJson);
            for (Object employeeObj : employeesArray) {
                JSONObject employeeJsonObj = (JSONObject) employeeObj;
                String employeeJson = employeeJsonObj.toString();
                Employee employee = JSONFactoryUtil.looseDeserialize(employeeJson, EmployeeImpl.class);
                employees.add(employee);
            }
        } catch (Exception e) {
            _log.error("Error: " + e.getMessage());
        }
        return employees;
    }

    private static final Log _log = LogFactoryUtil.getLog(InMemoryEmployeeRepository.class);
}
