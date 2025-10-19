package com.solteq.liferay.training.employees.repository.sync.dispatch.tasks.model;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SyncInfo {

    private final Map<Long, String> addedUsers = new HashMap<>();
    private final List<String> errors = new ArrayList<>();

    public void addUser(long userId, String emailAddress) {
        addedUsers.put(userId, emailAddress);
    }

    public void addError(String errorMessage) {
        errors.add(errorMessage);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(StringPool.BLANK);
        result.append("<style>");
        result.append(
            ".table-responsive.table-success td, .table-responsive.table-success th { background-color: #DDFFDD; }"
        );
        result.append(
            ".table-responsive.table-error td, .table-responsive.table-error th { background-color: #FFDDDD; }"
        );
        result.append("</style>");
        result.append("<h2 class='mt-3'>Employees Sync Completed</h2>");

        if (MapUtil.isNotEmpty(addedUsers)) {
            result.append("<h4 class='mt-3'>Created Employees</h4>");
            printEmployees(result);
        }
        if (ListUtil.isNotEmpty(errors)) {
            result.append("<h4 class='mt-3'>Errors</h4>");
            printErrors(result);
        }
        return result.toString();
    }

    private void printEmployees(StringBuilder sb) {
        sb.append("<div class=\"table-responsive table-success\">");
        sb.append("<table class=\"table table-autofit table-heading-nowrap table-list\">");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th>#</th>");
        sb.append("<th>User ID</th>");
        sb.append("<th>E-Mail</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        int counter = 1;
        for (Long userId : addedUsers.keySet()) {
            sb.append("<tr>");
            sb.append(String.format("<td>%d</td>", counter++));
            sb.append(String.format("<td>%d</td>", userId));
            sb.append(String.format("<td>%s</td>", addedUsers.get(userId)));
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</div>");
    }

    private void printErrors(StringBuilder sb) {
        sb.append("<div class=\"table-responsive table-error\">");
        sb.append("<table class=\"table table-autofit table-heading-nowrap table-list\">");
        sb.append("<thead>");
        sb.append("<tr>");
        sb.append("<th>#</th>");
        sb.append("<th>Error</th>");
        sb.append("</tr>");
        sb.append("</thead>");
        int counter = 1;
        for (String errorMessage : errors) {
            sb.append("<tr>");
            sb.append(String.format("<td>%d</td>", counter++));
            sb.append(String.format("<td>%s</td>", errorMessage));
            sb.append("</tr>");
        }
        sb.append("</table>");
        sb.append("</div>");
    }
}
