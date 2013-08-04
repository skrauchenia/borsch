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
                $("#navHome").addClass("active");
                
                var emptyOrderTemplate = "<tr class=\"order-rows\">\
                                            <td colspan=\"4\" class=\"centered-line\">&lt;currently empty&gt;</td>\
                                          </tr>";
                var orderTemplate = "<tr class=\"order-rows\">\
                                       <td>{0}</td>\
                                       <td>{1}</td>\
                                       <td>{2}</td>\
                                       <td>\
                                         <a href=\"#\" role=\"button\" class=\"btn btn-danger btn-mini order-remove\">\
                                           <spring:message code="menu.order.remove"/>\
                                         </a>\
                                       </td>\
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
                    $("#day" + id).addClass("active");
                    $("#day" + id + "info").show(400);
                }
                
                var currentDay = (new Date()).getDay() - 1;
                if (currentDay < 0 || currentDay > 4)
                    currentDay = 0;
                switchDay(currentDay);

                $("#days > li").click(function() {
                    switchDay($(this).attr("id").substr(3));
                });
                
                // Order management
                function removeOrder(day, order) {
                    var table = $("#day" + day + "info table");
                    $.post("${contextPath}/home/orders/" + day + "/remove/" + order, {}, function() {
                        table.find("tr:eq(" + (order + 1) + ")").hide("fast", function() {
                            $(this).remove();
                        });
                    });
                }
                
                function updateOrders(day) {
                    var table = $("#day" + day + "info table");
                    $.get("${contextPath}/home/orders/" + day, {}, function(items) {
                        console.log(items);
                        table.find("tr:gt(0)").remove();
                        if (items.length === 0) {
                            table.append($(emptyOrderTemplate));
                            return;
                        }
                        $.each(items, function(idx, value) {
                            var order = orderTemplate.format(idx + 1,
                                value.name, value.price);
                            table.append($(order));
                        });
                    });
                }
                
                function showEditOrderPage(day) {
                    var modal = $("#myModal");
                    var frame = modal.find("iframe");
                    frame.attr("src", "${contextPath}/menu/?internal=true");
                    modal.modal("show");
                    
                    frame.ready(function() {
                        updateOrders(day);
                    });
                }
                
                function getDay(elem) {
                    var dayInfo = elem.parents(".day-info");
                    return parseInt(dayInfo.attr("id").substr(3));
                }
                
                $(".order-remove").click(function(e) {
                    e.preventDefault();
                    
                    var $this = $(this);
                    var item = $this.parents('tr').index() - 1;
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
            <div class="container-fluid">
                <div class="span2">
                    <ul class="nav nav-pills nav-stacked" id="days">
                        <c:forEach begin="0" end="4" var="idx">
                            <li id="day${idx}"><a><spring:message code="home.day${idx + 1}"/></a></li>
                        </c:forEach>
                    </ul>
                </div>
                <c:forEach items="${order.order}" var="menuItem" varStatus="st">
                    <div id="day${st.index}info" class="span7 day-info hide">
                        <h3><spring:message code="home.order.title"/></h3>
                        <c:choose>
                            <c:when test="${menuItem.isPaid == true}">
                                <button type="button" class="btn-small btn-success" disabled="true">
                                    <i class="icon-ok icon-white"></i><spring:message code="menuItem.status.paid"/>
                                </button>
                            </c:when>
                            <c:otherwise>
                                <button type="button" class="btn-small btn-danger" disabled="true">
                                    <i class="icon-remove icon-white"></i><spring:message code="menuItem.status.unpaid"/>
                                </button>
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
                                    <td colspan="4" class="centered-line">&lt;currently empty&gt;</td>
                                </tr>
                            </c:if>
                            <c:forEach items="${menuItem.choices}" var="item" varStatus="itemStatus">
                                <tr class="order-rows">
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
                        <a href="#" role="button" class="btn btn-primary order-edit" style="float: right">
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
