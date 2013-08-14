<%--
    Author : Andrew Zhilka
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title><spring:message code="reportSummary.title"/></title>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#navReports").addClass("active");
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <c:forEach var="daySummary" items="${summary}">
                <h4><spring:message code="home.day${daySummary.key}"/></h4>
                <table id="day${daySummary.key}" class="table table-bordered reportSummary">
                    <tr>
                        <th colspan="75%"><spring:message code="menu.table.dish"/></th>
                        <th colspan="25%"><spring:message code="menu.table.totalNumber"/></th>
                    </tr>
                    <c:forEach var="dishSummary" items="${daySummary.value}">
                        <tr>
                            <td colspan="75%"><c:out value="${dishSummary.key}"/></td>
                            <td colspan="25%"><c:out value="${dishSummary.value}"/></td>
                        </tr>
                    </c:forEach>
                </table>
            </c:forEach>
        </div>
    </jsp:body>
</t:genericpage>