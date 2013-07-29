<%--
    author: Andrey Zhilka
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:attribute name="head">Order Report</jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#navHome").removeClass();
            $("#navUsers").removeClass();
            $("#navMenu").removeClass();
            $("#navReport").addClass("active");

            function markAsPaid(menuItemId, weekOrderId){
                //TODO: finish this section\
                $.ajax({
                    url: "/report/setPaid/"+ weekOrderId + "/" + menuItemId,
                    type: 'POST'
                }).done(function(response){
                        var responseState = $.parseJSON(response);
                        if(responseState.responseSucceed === true) {
                            $("#orderPaid").show("slow");
                            $("#orderNotPaid").hide("slow");
                        } else {
                            alert("<spring:message code="request.fail"/>");
                            $("#orderPaid").hide("slow");
                            $("#orderNotPaid").show("slow");
                        }
                });
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <c:set var="dayNumber" value="0" scope="page"/>
            <c:forEach var="day" items="report">
                <div class="accordion" id="dayAccordion">
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#dayAccordion">
                                <spring:message code="day.${count}"/>
                                <c:set var="count" value="${count + 1}" scope="page"/>
                            </a>
                        </div>
                        <div id="collapseDayNumber" class="accordion-body collapse in">
                            <div class="accordion-inner">
                                <table class="table table-striped">
                                    <tr>
                                        <th>Name</th>
                                        <th>Total</th>
                                        <th>Payment status</th>
                                    </tr>
                                    <c:forEach var="order" items="day">
                                        <tr>
                                            <td>
                                                <c:out value="${order.user.name}"/>
                                            </td>
                                            <td>
                                                <c:out value="${order.totalPrice}"/>
                                            </td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${order.menuItem.isPaid == true}">
                                                        <button class="btn btn-success" id="orderPaid" disabled>
                                                            <i class="icon-ok icon-white"></i>
                                                        </button>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <button class="btn btn-danger" id="orderNotPaid"
                                                                onclick="markAsPaid(${order.menuItem.id},
                                                                            ${order.weekOrderId})">
                                                            <i class="icon-remove icon-white"></i>
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        <div>
    </jsp:body>
</t:genericpage>