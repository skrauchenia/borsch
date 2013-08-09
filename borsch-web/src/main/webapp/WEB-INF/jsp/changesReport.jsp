    <%--
    Author : Andrew Zhilka
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title><spring:message code="changesReport.title"/></title>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <h3><spring:message code="changesReport.title"/></h3>
            <div class="accordion" id="dayAccordion">
                <c:set var="dayNumber" value="0"/>
                <c:forEach var="day" items="${report}">
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse"
                               data-parent="#dayAccordion" href="#collapse${dayNumber}">
                                <c:set var="dayOfWeek" value="home.day${day.key + 1}"/>
                                <spring:message code="${dayOfWeek}"/>
                            </a>
                        </div>
                        <div id="collapse${dayNumber}" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <table class="table table-striped">
                                    <tr>
                                        <th><spring:message code="menu.table.name"/></th>
                                        <th><spring:message code="menu.table.dish"/></th>
                                        <th><spring:message code="menu.table.action"/></th>
                                    </tr>
                                    <c:forEach var="order" items="${day.value}">
                                        <tr>
                                            <td>
                                                <c:out value="${order.userName}"/>
                                            </td>
                                            <td>
                                                <c:out value="${order.dishName}"/>
                                            </td>
                                            <td>
                                                <c:out value="${order.committedAction.getName()}"/>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                    <c:set var="dayNumber" value="${dayNumber + 1}" scope="page"/>
                </c:forEach>
            </div>
        </div>
    </jsp:body>
</t:genericpage>