<%--
    author: Andrey Zhilka
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    <jsp:attribute name="head">
        <title>Order Report</title>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#navHome").removeClass();
            $("#navUsers").removeClass();
            $("#navMenu").removeClass();
            $("#navReport").addClass("active");

            function markAsPaid(menuItemId, weekOrderId){
                //TODO: finish this section\
                $.ajax({
                    url: "${contextPath}/report/setPaid/"+ weekOrderId + "/" + menuItemId,
                    type: 'POST',
                    dataType: "json"
                }).done(function(response){
                            if(response.responseSucceed == true) {
                                $("#orderNotPaid").hide("slow");
                                $("#orderPaid").show("slow");
                            } else {
                                alert("<spring:message code="request.fail"/>");
                                $("#orderPaid").hide("slow");
                                $("#orderNotPaid").show("slow");
                            }
                        })
                  .fail(function(){
                            alert("Ajax Request failed");
                        });
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <c:set var="dayNumber" value="0" scope="page"/>
            <div class="accordion" id="dayAccordion">
                <c:forEach var="day" items="${report}">
                    <div class="accordion-group">
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#dayAccordion" href="#collapse${dayNumber}">
                                <c:set var="dayOfWeek" value="home.day${(dayNumber % workingDays) + 1}"/>
                                <spring:message code="${dayOfWeek}"/>
                            </a>
                        </div>
                        <div id="collapse${dayNumber}" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <table class="table table-striped">
                                    <tr>
                                        <th>Name</th>
                                        <th>Total</th>
                                        <th>Payment status</th>
                                    </tr>
                                    <c:forEach var="order" items="${day}">
                                        <c:if test="${(dayNumber + 1) == order.weekDay}">
                                            <tr>
                                                <td>
                                                    <c:out value="${order.user.name}"/>
                                                </td>
                                                <td>
                                                    <c:out value="${order.total}"/>
                                                </td>
                                                <td>
                                                    <button class="btn btn-success
                                                        <c:if test="${order.menuItem.isPaid != true}">
                                                         hide</c:if>" id="orderPaid" disabled>
                                                            <i class="icon-ok icon-white"></i>
                                                        </button>
                                                        <button class="btn btn-danger
                                                        <c:if test="${order.menuItem.isPaid == true}">
                                                         hide</c:if>" id="orderNotPaid"
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
        <div>
    </jsp:body>
</t:genericpage>