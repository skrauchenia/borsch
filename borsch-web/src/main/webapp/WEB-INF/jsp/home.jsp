<%-- 
    Document   : index
    Created on : Jul 18, 2013, 2:56:51 PM
    Author     : tima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title><spring:message code="home.title"/></title>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script>
            $(function() {
                $("#navHome").addClass("active");
                
                var emptyOrderTemplate = "<tr class=\"order-rows\">\
                                            <td colspan=\"4\" class=\"centered-line\">&lt;<spring:message code="message.empty"/>&gt;</td>\
                                          </tr>";
                var orderTemplate = "<tr class=\"order-rows\" id=\"{3}\">\
                                       <td>{0}</td>\
                                       <td>{1}</td>\
                                       <td>{2}</td>\
                                       <td>\
                                         <a href=\"#\" role=\"button\" class=\"btn btn-danger btn-mini order-remove\">\
                                           <spring:message code="menu.order.remove"/>\
                                         </a>\
                                       </td>\
                                     </tr>";
                var summaryTemplate = "<tr class='order-rows'>\
                                         <td colspan='2'><spring:message code='home.order.sum'/></td>\
                                         <td>{0}</td>\\n\
                                         <td></td>\
                                       </tr>";
        
                // Some library functions
                if (!String.prototype.format) {
                    String.prototype.format = function() {
                        var args = arguments;
                        return this.replace(/{(\d+)}/g, function(match, number) { 
                            return (typeof args[number] != 'undefined') ? args[number] : match;
                        });
                    };
                }
                
                // Weekday navbar control
                function switchDay(id) {
                    var dayCount = $("#days > li").length;
                    for (var i = 0; i < dayCount; i++) {
                        $("#day" + i).removeClass();
                        $("#day" + i + "info").hide(400);
                    }
                    updateOrders(id);
                    $("#day" + id).addClass("active");
                    $("#day" + id + "info").show(400, function() {
                        
                    });
                }
                
                var currentDay = (new Date()).getDay() - 1;
                if (currentDay < 0 || currentDay > 4)
                    currentDay = 0;
                switchDay(currentDay);

                $("#days > li > a").click(function(e) {
                    e.preventDefault();
                    switchDay($(this).parent().attr("id").substr(3));
                });
                
                function getTable(day) {
                    return $("#day" + day + "info table");
                }
                
                // Order management
                function processOrder(day, orderId, button) {
                    var date = getTable(day).parent().find(".btn:last").data("date");
                    $.post("${contextPath}/home/orders/" + date + "/" + orderId, {}, function(result) {
                        if (result.status === "added") {
                            addOrder(day, result.dish);
                        }
                        else {
                            removeOrder(day, result.dish.id);
                        }
                    });
                }
                function addOrder(day, dish) {
                    var table = getTable(day);
                    var order = orderTemplate.format(0,
                        dish.name, dish.price, dish.id);
                    table.append($(order));
                    updateOrders(day);
                }
                function removeOrder(day, orderId) {
                    var table = getTable(day);
                    table.find("#" + orderId).hide("fast", function() {
                        $(this).remove();
                        updateOrders(day);
                    });
                }
                function updateOrders(day) {
                    var empty = true;
                    var table = getTable(day);
                    var sum = 0;
                    table.find("tr:gt(0):not([id])").remove();
                    table.find("tr:gt(0)").each(function(idx) {
                        $(this).find("td:first").text(idx + 1);
                        sum += +$(this).find("td:eq(2)").text();
                        empty = false;
                    });
                    if (empty)
                        table.append($(emptyOrderTemplate));
                    else {
                        table.append($(summaryTemplate.format(sum)));
                    }
                }
                
                function toggleOrderButton(button) {
                    button.toggleClass("btn-success");
                    button.toggleClass("btn-danger");
                    if (button.hasClass("btn-danger"))
                        button.html("<spring:message code='menu.order.remove'/>");
                    else
                        button.html("<spring:message code='menu.order.add'/>");
                }
                
                function showEditOrderPage(day) {
                    var modal = $("#myModal");
                    var frame = modal.find("iframe");
                    var table = getTable(day);
                    frame[0].contentWindow.location.reload();
                    <joda:format var="priceDate" value="${order.startDate}" pattern="dd-MM-yyyy"/>
                    frame.attr("src", "${contextPath}/menu/${priceDate}?internal=true");
                    modal.modal("show");
                    
                    frame.off("load");
                    frame.load(function() {
                        var addToOrderButton = $(".add-to-order", frame.contents()[0]);
                        addToOrderButton.off('click');
                        addToOrderButton.each(function() {
                            var orderId = $(this).parents("tr").attr("id").substr(3);
                            if (table.find("#" + orderId).length > 0)
                                toggleOrderButton($(this));
                        });
                        addToOrderButton.click(function() {
                            var $this = $(this);
                            var orderId = $this.parents("tr").attr("id").substr(3);
                            processOrder(day, orderId, $this);
                            toggleOrderButton($this);
                        });
                    });
                }
                
                function getDay(elem) {
                    var dayInfo = elem.parents(".day-info");
                    return parseInt(dayInfo.attr("id").substr(3));
                }
                
                $(".day-info").on("click", ".order-remove", function(e) {
                    e.preventDefault();
                    
                    var $this = $(this);
                    var item = $this.parents('tr').attr("id");
                    processOrder(getDay($this), item, null);
                    removeOrder(getDay($this), item);
                });
                $(".order-edit").click(function(e) {
                    e.preventDefault();
                    
                    showEditOrderPage(getDay($(this)));
                });
            });
        </script>
    </jsp:attribute>
        
    <jsp:body>
        <div class="container">
            <br/>
            <h3 class="muted"><spring:message code="week.${currentWeekCode}"/></h3>
            <br/>
            <div class="container-fluid">
                <div class="span2">
                    <ul class="nav nav-pills nav-stacked" id="days">
                        <c:forEach begin="0" end="4" var="idx">
                            <li id="day${idx}"><a href="#"><spring:message code="home.day${idx + 1}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
                <c:forEach items="${order.order}" var="menuItem" varStatus="st">
                    <div id="day${st.index}info" class="span7 day-info hide">
                        <h3><spring:message code="home.order.title"/></h3>
                        <c:choose>
                            <c:when test="${menuItem.isPaid == true}">
                                <span class="badge badge-success"><i class="icon-ok icon-white"></i></span>
                                <spring:message code="menuItem.status.paid"/>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-warning">
                                    <i class="icon-remove icon-white"></i><spring:message code="menuItem.status.unpaid"/>
                                </span>
                            </c:otherwise>
                        </c:choose>
                        <hr/>
                        <table class="table table-striped">
                            <tr>
                                <th><spring:message code="menu.table.number"/></th>
                                <th><spring:message code="menu.table.name"/></th>
                                <th><spring:message code="menu.table.price"/></th>
                                <th><spring:message code="actions.remove"/></th>
                            </tr>
                            <c:if test="${empty menuItem.choices}">
                                <tr class="order-rows">
                                    <td colspan="4" class="centered-line">&lt;<spring:message code="message.empty"/>&gt;</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${menuItem.choices}" var="item" varStatus="itemStatus">
                                <tr class="order-rows" id="${item.id}">
                                    <td>${itemStatus.index + 1}</td>
                                    <td>${item.name}</td>
                                    <td>${item.price}</td>
                                    <td>
                                        <a href="#" role="button" class="btn btn-danger btn-mini order-remove">
                                            <spring:message code="menu.order.remove"/>
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                        <hr/>
                        <joda:format var="itemDate" value="${menuItem.date}" pattern="dd-MM-yyyy"/>
                        <a href="#" role="button" class="btn btn-primary order-edit" data-date="${itemDate}" style="float: right">
                            <spring:message code="home.order.edit"/>
                        </a>
                    </div>
                </c:forEach>
                <!-- Edit order item modal -->
                <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel"><spring:message code="action.customize"/></h3>
                    </div>
                    <div class="modal-body">
                        <iframe src="" frameborder="0" width="99.6%" height="100%"></iframe>
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-primary" data-dismiss="modal" aria-hidden="true"><spring:message code="action.close"/></button>
                        <%--<button class="btn btn-primary"><spring:message code="actiom.save.changes"/></button>--%>
                    </div>
                </div>
            </div>
        
            <br/>
            <c:if test="${not empty prevWeek}">
            <a class="btn btn-info" style="float: left" href="${contextPath}/home/${prevWeek}">
                <spring:message code="week.0"/></a>
            </c:if>
            <c:if test="${not empty nextWeek}">
            <a class="btn btn-info" style="float: right" href="${contextPath}/home/${nextWeek}">
                <spring:message code="week.2"/></a>
            </c:if>
        </div>
    </jsp:body>
        
</t:genericpage>
