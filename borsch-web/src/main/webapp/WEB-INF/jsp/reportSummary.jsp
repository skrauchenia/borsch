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

    <jsp:body>
        <c:forEach var="daySummary" items="${summary}">
            <h4 class="offset3"><spring:message code="home.day${daySummary.key}"/></h4>
            <table id="day${daySummary.key}" class="reportSummary">
                <tr>
                    <th><spring:message code="menu.table.name"/></th>
                    <th><spring:message code="menu.table.totalNumber"/></th>
                </tr>
                <c:forEach var="dishSummary" items="${daySummary.value}">
                    <tr>
                        <td colspan="10"><c:out value="${dishSummary.key}"/></td>
                        <td colspan="5"><c:out value="${dishSummary.value}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </c:forEach>
    </jsp:body>
</t:genericpage>