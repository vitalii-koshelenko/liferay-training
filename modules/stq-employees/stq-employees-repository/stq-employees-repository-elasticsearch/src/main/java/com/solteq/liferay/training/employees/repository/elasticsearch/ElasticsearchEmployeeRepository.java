package com.solteq.liferay.training.employees.repository.elasticsearch;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.search.document.Document;
import com.liferay.portal.search.hits.SearchHit;
import com.liferay.portal.search.hits.SearchHits;
import com.liferay.portal.search.query.BooleanQuery;
import com.liferay.portal.search.query.Queries;
import com.liferay.portal.search.searcher.*;
import com.solteq.liferay.training.common.liferay.util.LiferayUtil;
import com.solteq.liferay.training.employees.domain.filter.EmployeeFilter;
import com.solteq.liferay.training.employees.domain.model.BaseEmployee;
import com.solteq.liferay.training.employees.domain.model.Employee;
import com.solteq.liferay.training.employees.domain.search.STQEmployeeField;
import com.solteq.liferay.training.employees.repository.api.EmployeeRepository;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Component(
        immediate = true,
        property = {
                "repository.type=elasticsearch",
                "service.ranking:Integer=103"
        },
        service = EmployeeRepository.class
)
public class ElasticsearchEmployeeRepository implements EmployeeRepository {

    private static final String ENTITY_CLASS_NAME = "com.solteq.liferay.training.employees.repository.db.model.STQEmployee";

    @Override
    public List<Employee> fetchEmployees() {
        return fetchEmployees(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
    }

    @Override
    public int fetchEmployeeCount(EmployeeFilter filter) {
        // Build Search Request
        SearchRequestBuilder searchRequestBuilder = createCountSearchRequestBuilder(filter);
        SearchRequest searchRequest = searchRequestBuilder.build();
        // Obtain Search Response
        SearchResponse searchResponse = searcher.search(searchRequest);
        // Return total count of Search Results
        long searchCount = searchResponse.getTotalHits();
        return (int) searchCount;
    }

    @Override
    public List<Employee> fetchEmployees(int start, int end, EmployeeFilter filter) {
        // Build Search Request
        SearchRequest searchRequest = createSearchRequest(filter, start, end);
        // Obtain Search Response
        SearchResponse searchResponse = searcher.search(searchRequest);
        // Get Hits from Search Response
        SearchHits searchHits = searchResponse.getSearchHits();
        // Convert SearchHits to List of Employees
        List<SearchHit> hits = searchHits.getSearchHits();
        List<Employee> employees = new ArrayList<>();
        if (ListUtil.isNotEmpty(hits)) {
            for (SearchHit searchHit : hits) {
                Employee employee = toEmployee(searchHit.getDocument());
                employees.add(employee);
            }
        }
        return employees;
    }

    private Employee toEmployee(Document document) {
        BaseEmployee employee = new BaseEmployee();
        employee.setEmployeeId(document.getLong(STQEmployeeField.EMPLOYEE_ID));
        employee.setFirstName(document.getString(STQEmployeeField.FIRST_NAME));
        employee.setLastName(document.getString(STQEmployeeField.LAST_NAME));
        employee.setEmail(document.getString(STQEmployeeField.EMAIL));
        employee.setJobTitle(document.getString(STQEmployeeField.JOB_TITLE));
        employee.setPhoneNumber(document.getString(STQEmployeeField.PHONE_NUMBER));
        employee.setCompanyName(document.getString(STQEmployeeField.COMPANY_NAME));
        employee.setDepartment(document.getString(STQEmployeeField.DEPARTMENT));
        employee.setWorkLocation(document.getString(STQEmployeeField.WORK_LOCATION));
        return employee;
    }

    private SearchRequest createSearchRequest(EmployeeFilter filter, int start, int end) {
        SearchRequestBuilder searchRequestBuilder = createCountSearchRequestBuilder(filter);
        searchRequestBuilder.from(start);
        int size = end - start;
        searchRequestBuilder.size(size);
        return searchRequestBuilder.build();
    }

    private SearchRequestBuilder createCountSearchRequestBuilder(EmployeeFilter filter) {
        // Create BooleanQuery
        BooleanQuery booleanQuery = queries.booleanQuery();
        // Apply filter
        applySearchContext(booleanQuery, filter);
        // Create SearchRequestBuilder
        return searchRequestBuilderFactory
                .builder()
                .entryClassNames(ENTITY_CLASS_NAME)
                .emptySearchEnabled(true)
                .withSearchContext(searchContext -> searchContext.setCompanyId(LiferayUtil.getDefaultCompanyId()))
                .query(booleanQuery);
    }

    private void applySearchContext(BooleanQuery booleanQuery, EmployeeFilter filter) {
        if (filter == null) {
            return;
        }
        String searchTerm = filter.getSearch();
        if (Validator.isNotNull(searchTerm)) {
            BooleanQuery subQuery = queries.booleanQuery();
            subQuery.addShouldQueryClauses(
                    queries.match(STQEmployeeField.FIRST_NAME, searchTerm),
                    queries.match(STQEmployeeField.LAST_NAME, searchTerm),
                    queries.match(STQEmployeeField.EMAIL, searchTerm),
                    queries.match(STQEmployeeField.PHONE_NUMBER, searchTerm)
            );
            booleanQuery.addMustQueryClauses(subQuery);
        }
        processFilter(booleanQuery, STQEmployeeField.JOB_TITLE, filter.getJobTitle());
        processFilter(booleanQuery, STQEmployeeField.COMPANY_NAME, filter.getCompanyName());
        processFilter(booleanQuery, STQEmployeeField.DEPARTMENT, filter.getDepartment());
        processFilter(booleanQuery, STQEmployeeField.WORK_LOCATION, filter.getWorkLocation());
    }

    private void processFilter(BooleanQuery booleanQuery, String fieldName, String fieldValue) {
        if (Validator.isNotNull(fieldValue)) {
            booleanQuery.addMustQueryClauses(queries.match(fieldName, fieldValue));
        }
    }

    @Override
    public Employee fetchEmployeeById(long employeeId) {
        return databaseRepository.fetchEmployeeById(employeeId);
    }

    @Override
    public Employee saveEmployee(long employeeId, String firstName, String lastName, String email, String phoneNumber, String companyName, String jobTitle, String department, String workLocation) {
        return databaseRepository.saveEmployee(employeeId, firstName, lastName, email, phoneNumber, companyName, jobTitle, department, workLocation);
    }

    @Override
    public void deleteEmployee(long employeeId) {
        databaseRepository.deleteEmployee(employeeId);
    }

    @Reference
    private Queries queries;
    @Reference
    private Searcher searcher;
    @Reference
    private SearchRequestBuilderFactory searchRequestBuilderFactory;

    @Reference(target = "(component.name=com.solteq.liferay.training.employees.repository.db.service.impl.DatabaseEmployeeRepository)")
    private EmployeeRepository databaseRepository;
}