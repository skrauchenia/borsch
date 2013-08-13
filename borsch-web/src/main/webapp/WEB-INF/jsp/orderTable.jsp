<%-- 
    Document   : orderTable
    Created on : Aug 6, 2013, 12:53:46 PM
    Author     : Fedor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSP Page</title>
        <link href="${contextPath}/assets/css/tableReport.css" rel="stylesheet" type="text/css"/>
        <link href="${contextPath}/assets/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="table">
            <table table border="1" cellpadding="1">
                <tr>
                    <th class="name-width" rowspan="2">ФИО</th>
                        <c:forEach var="day" items="${date}">
                        <th class="days-width" colspan="2">${day}</th>
                        </c:forEach>
                </tr>
                <tr>
                    <th>Заказ</th>
                    <th>Стоимость</th>
                    <th>Заказ</th>
                    <th>Стоимость</th>
                    <th>Заказ</th>
                    <th>Стоимость</th>
                    <th>Заказ</th>
                    <th>Стоимость</th>
                    <th>Заказ</th>
                    <th>Стоимость</th>
                </tr>
                <c:forEach var="element" items="${orders}" varStatus="st">
                    <c:set var="buf" value="0" scope="page"/>
                    <c:set var="user" value="${element.key}"/>
                    <c:set var="listMenuItem" value="${element.value}"/>
                    <tr>
                        <td>${user.name}</td>
                        <c:forEach var="menuItem" items="${listMenuItem}">
                            <td>
                                <c:set var="choices" value="${menuItem.choices}"/>
                                <c:forEach var="dish" items="${choices}">
                                    ${dish.name}; 
                                </c:forEach>
                            <td>${menuItem.getTotalPrice()}</td>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
                <tr>
                    <td>Итого</td>
                    <c:forEach var="element" items="${totalPrice}" varStatus="st">
                        <c:set var="day" value="${element.key}"/>
                        <c:set var="dayPrice" value="${element.value}"/>
                        <td></td>
                        <td>${dayPrice}</td>
                    </c:forEach>
                </tr>
            </table>
        </div>
        <div style="position: absolute; right: 5%; bottom: 5%" class="btn-group" id="buttons">
            <form action="${contextPath}/print/pdf" method="POST">
                <button type="submit" class="btn btn-info">
                    <i class="icon-download-alt icon-white"></i>
                </button>
                <input id="src" type="hidden" name="src" value=""/>
            </form>
            <form>
                <button href="" role="button" type="submit" class="btn btn-info" >
                    <i class="icon-print icon-white"></i>
                </button>
            </form>
        </div>
        <script src="${contextPath}/assets/js/jquery-2.0.3.min.js"></script>
        <script type="text/javascript">
//            var src = "<html><head></head><body>" + $("#table").html(); + "</body></html>";
            var src = document.location.href;
            $("#src").attr("value", src);
            $('#buttons').fadeTo(100, 0.2);
            $(function() {
                $("#buttons").mouseover(function() {
                    $('#buttons').stop().fadeTo(100, 1);
                });
                $("#buttons").mouseout(function() {
                    $('#buttons').stop().fadeTo(100, 0.2);
                });

            });</script>
    </body>
</html>
