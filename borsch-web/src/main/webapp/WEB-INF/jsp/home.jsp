<%-- 
    Document   : index
    Created on : Jul 18, 2013, 2:56:51 PM
    Author     : tima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title><spring:message code="home.title"/></title>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script>
            $(function() {
                $("#navHome").addClass("active")
                
                function switchDay(id) {
                    var dayCount = $("#days > li").length;
                    for (var i = 1; i <= dayCount; i++) {
                        $(("#day" + i)).removeClass();
                        $(("#day" + i + "info")).hide(400);
                    }
                    $("#day" + id).addClass("active");
                    $("#day" + id + "info").show(400);
                }
                
                var currentDay = (new Date()).getDay();
                if (currentDay < 1 || currentDay > 5)
                    currentDay = 1;
                switchDay(currentDay);

                $("#days > li").click(function() {
                    switchDay(this.id.substr(3));
                });
            });
        </script>
    </jsp:attribute>
        
    <jsp:body>
        <div class="container">
            <br/>
            <div class="container-fluid">
                <div class="span2">
                    <ul class="nav nav-pills nav-stacked" id="days">
                        <c:forEach begin="1" end="5" var="idx">
                            <li id="day${idx}"><a><spring:message code="home.day${idx}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
                <c:forEach items="${order.order}" var="menuItem" varStatus="st">
                    <div id="day${st.index + 1}info" class="span7 day-info hide">
                        <h3><spring:message code="home.order.title"/></h3>
                        <hr/>
                        <table class="table table-striped">
                            <tr>
                                <th><spring:message code="menu.table.number"/></th>
                                <th><spring:message code="menu.table.name"/></th>
                                <th><spring:message code="menu.table.price"/></th>
                                <th><spring:message code="actions.remove"/></th>
                            </tr>
                            <c:if test="${empty menuItem.choices}">
                                <tr>
                                    <td colspan="4" class="centered-line">&lt;currently empty&gt;</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${menuItem.choices}" var="item" varStatus="itemStatus">
                                <tr>
                                    <td>${itemStatus.index + 1}</td>
                                    <td>${item.name}</td>
                                    <td>${item.price}</td>
                                    <td>
                                        <a href="#" role="button" class="btn btn-danger">
                                            <spring:message code="menu.order.remove"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <hr/>
                        <a href="#myModal" role="button" class="btn btn-primary" data-toggle="modal" style="float: right">
                            <spring:message code="home.order.edit"/>
                        </a>
                    </div>
                </c:forEach>
                <!-- Edit order item modal -->
                <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Customize order</h3>
                    </div>
                    <div class="modal-body">
                        <iframe src="" frameborder="0" width="99.6%"></iframe>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
        
</t:genericpage>

