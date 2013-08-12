<%--
    author: Andrey Zhilka
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title><spring:message code="orderReport.title"/></title>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#navReports").addClass("active");

            function markAsPaid(menuItemId, weekOrderId){
                $.ajax({
                    url: "${contextPath}/report/setPaid/"+ weekOrderId + "/" + menuItemId,
                    type: 'POST'
                }).done(function() {
                            $("#orderNotPaid"+menuItemId).hide("slow");
                            $("#orderPaid"+menuItemId).show("slow");
                        })
                  .fail(function(status, error) {
                            console.log(status);
                            alert(error);
                            $("#orderPaid"+menuItemId).hide("slow");
                            $("#orderNotPaid"+menuItemId).show("slow");
                        });
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <h3 class="muted"><spring:message code="week.${week}"/></h3>
            <c:set var="dayNumber" value="0" scope="page"/>
            <div class="accordion" id="dayAccordion">
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
                                        <th><spring:message code="menu.table.total"/></th>
                                        <th><spring:message code="menu.table.payment"/></th>
                                    </tr>
                                    <c:forEach var="order" items="${day.value}">
                                        <c:if test="${(dayNumber + 1) == order.weekDay}">
                                            <tr>
                                                <td>
                                                    <c:out value="${order.user.name}"/>n
                                                </td>
                                                <td>
                                                    <c:out value="${order.total}"/>
                                                </td>
                                                <td>
                                                    <button class="btn btn-success
                                                                    <c:if test="${order.menuItem.isPaid != true}">
                                                                     hide</c:if>" id="orderPaid${order.menuItem.id}"
                                                            title="<spring:message code="menu.table.payment.status.paid"/>"
                                                            disabled>
                                                        <i class="icon-ok icon-white"></i>
                                                    </button>
                                                    <button class="btn btn-danger
                                                                    <c:if test="${order.menuItem.isPaid == true}">
                                                                     hide</c:if>"
                                                            id="orderNotPaid${order.menuItem.id}"
                                                            title="<spring:message code="menu.table.payment.status.unpaid"/>"
                                                            onclick="markAsPaid('${order.menuItem.id}',
                                                                    '${order.weekOrderId}')">
                                                        <i class="icon-remove icon-white"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                    <c:set var="dayNumber" value="${dayNumber + 1}" scope="page"/>
                </c:forEach>
            </div>
            <a class="btn btn-info" style="float: left" href="${contextPath}/report/${week - 1}"
                    <c:if test="${week eq 0}">disabled="true"</c:if>>
                <spring:message code="week.0"/></a>
            <a class="btn btn-info" style="float: right" href="${contextPath}/report/${week + 1}"
               <c:if test="${week eq 2}">disabled="true"</c:if>>
                <spring:message code="week.2"/></a>
        </div>
    </jsp:body>
</t:genericpage>
