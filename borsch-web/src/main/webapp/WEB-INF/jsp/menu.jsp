<%--
    Document   : menu
    Created on : Jul 19, 2013, 10:56:21 AM
    Author     : Tima, Andrew Zhilka
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<sec:authorize var="hasAdminRights" access="hasRole('ROLE_EDIT_PRICE')"/>
<c:set var="showAdminControls" value="${hasAdminRights && empty param.internal}"/>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Menu</title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
            $("[rel=tooltip]").tooltip();
            $("#navMenu").addClass("active");
            function loadModal(method, id) {

                if (arguments.length === 1) {
                    src += "edit/dish/" + method + "?internal=true";
                }
                else {
                    src += "edit/dish/" + id + "/" + method + "?internal=true";
                }
                load(src);
            }
            function load(src) {
                var frame = $(".modal-body").children('iframe');
                frame.attr("src", src);
                $('#myModal').modal({show: true});
                frame.load(function() {
                    var form = frame.contents().find('.form-horizontal');
                    $('#save').click(function() {
                        //form.submit();
                        $('#myModal').modal('hide');
                    });
                });
            }
            function addDish(index) {
                var course;
                switch(index) {
                    case 0:
                        course = "FIRST_COURSE";
                        break;
                    case 1:
                        course = "SECOND_COURSE";
                        break;
                    case 2:
                        course = "DESSERT";
                        break;
                }
                var src = "${contextPath}/";
                src += "edit/dish/add/" + course + "?internal=true";
                load(src);
            }
            function editDish(id) {
                var src = "${contextPath}/";
                src += "edit/dish/" + id + "/edit" + "?internal=true";
                load(src);
            }
            function removeDish(id) {
                var src = "${contextPath}/edit/dish/" + id + "/remove";
                $.post(src, {removeId: id});
                $('#row' + id).hide(100, function() {
                    $('#row' + id).remove();
                });
            }

            function dishAction() {
//                alert("Action");
                var form = $('#dishForm', $("#myModal iframe").contents())
                $.ajax({
                    url: form.attr('action'),
                    data: form.serialize(),
                    type: 'POST',
                    dataType: "json",
                    error: function(request, error)
                    {
                        console.log(arguments);
                        alert(" Can't do because of " + error);
                    }
                }).done(function(response) {
                    var tableId = "#" + response.index + "Table";

                    if (form.attr('action').search("add") != -1) {
                        var newRow = $(tableId + " tr:nth-child(2)").clone();
                        newRow.attr("id", "row" + response.id);
                        newRow.children("[id^=index]").html($(tableId + " tr").length);
                        newRow.children("[id^=index]").attr("id", "index" + response.id);
                        
//                        var tooltip = document.createElement("a");
                        newRow.find("[id^=dishName]").html(response.name);
                        newRow.find("[id^=dishName]").attr("id", "dishName" + response.id);
                        newRow.find("[id^=dishName]").attr("data-original-title", response.description);

                        newRow.children("[id^=dishPrice]").html(response.price);
                        newRow.children("[id^=dishPrice]").attr("id", "dishPrice" + response.id);

                        var editBtn = newRow.find("[id^=editBtn]");
                        editBtn.removeAttr("onclick");
                        editBtn.on("click", function() {
                            editDish(response.id)
                        });
                        editBtn.attr("id", "editBtn" + response.id);
                        console.log(editBtn.attr("id"));

                        var removeBtn = newRow.find("[id^=removeBtn]");
                        removeBtn.removeAttr("onclick");
                        removeBtn.on("click", function() {
                            removeDish(response.id)
                        });
                        removeBtn.attr("id", "removeBtn" + response.id);

                        $(tableId).append(newRow);
                        $("[rel=tooltip]").tooltip();
                        
                    } else {
//                                var editedRow = $(tableId).find()
                        $("#row" + response.id).find("[id^=dishName]").html(response.name);
                        $("#row" + response.id).find("[id^=dishPrice]").html(response.price);
                    }
                });
            }
            
            $(function() {
                <c:if test="${empty param.internal}">
                $('.add-to-order').datepicker({
                    onRender: function(date) {
                        var dayOfWeek = date.getDay() - 1;
                        if (dayOfWeek < 0 || dayOfWeek > 4)
                            return 'disabled';
                        return '';
                    }
                }).on('changeDate', function(event) {
                    var $button = $(event.target);
                    $button.datepicker('hide');
                    $button.button('loading');
                    var orderId = $button.parents('tr').attr('id').substr(3);
                    var date = $button.data('date');
                    $.post("${contextPath}/home/orders/" + date + "/" + orderId, {}, function(result) {
                        if (result.status === "added") {
                            $button.button('added');
                        }
                        else if (result.status === "removed") {
                            $button.button('removed');
                        }
                        else {
                            $button.button('reset');
                        }
                    });
                });
                </c:if>
            });
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="accordion" id="accordion2">
                <c:forEach var="element" items="${courseList}" varStatus="stAcc">
                    <div class="accordion-group">
                        <c:set var="course" value="${element.key}"/>
                        <c:set var="dishes" value="${element.value}"/>
                        <div class="accordion-heading">
                            <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse${stAcc.index}">
                                <c:choose>
                                    <c:when test="${course eq 0}">First course:</c:when>
                                    <c:when test="${course eq 1}">Second course:</c:when>
                                    <c:when test="${course eq 2}">Dessert:</c:when>   
                                </c:choose>
                            </a>
                        </div>
                        <div id="collapse${stAcc.index}" class="accordion-body collapse">
                            <div class="accordion-inner">
                                <table class="table table-condensed" id="${course}Table">
                                    <tr>
                                        <th>#</th>
                                        <th>Name</th>
                                        <th>Price</th>
                                        <th></th>
                                        <c:if test="${showAdminControls}">
                                            <th>
                                                <button type="submit" class="btn btn-success" style="float: right" onclick="addDish(${course})">
                                                    <i class="icon-plus icon-white"></i> Add
                                                </button>
                                            </th>
                                        </c:if>
                                    </tr>
                                    <c:forEach var="dish" items="${dishes}" varStatus="st">
                                        <tr id="row${dish.id}">
                                            <td id="index${dish.id}">${st.index+1}</td>
                                            <td>
                                                <a href="#" id="dishName${dish.id}" data-toggle="tooltip"
                                                   rel="tooltip" data-placement="right" title="" 
                                                   data-original-title="${dish.description}">
                                                    ${dish.name}
                                                </a>
                                            </td>
                                            <td id="dishPrice${dish.id}">${dish.price}</td>
                                            <td>
                                                <button type="submit" class="btn btn-success add-to-order" data-date="" data-date-format="dd-mm-yyyy" autocomplete="off"
                                                        data-loading-text="<spring:message code="menu.order.processing"/>"
                                                        data-added-text="<spring:message code="menu.order.added"/>"
                                                        data-removed-text="<spring:message code="menu.order.removed"/>">
                                                    <i class="icon-ok icon-white"></i> Add to order
                                                </button>
                                            </td>
                                            <c:if test="${showAdminControls}">
                                                <td>
                                                    <button type="submit" class="btn btn-info" id="editBtn${dish.id}" onclick="editDish('${dish.id}')">
                                                        <i class="icon-pencil icon-white"></i> Edit
                                                    </button>
                                                    <button type="submit" class="btn btn-danger" id="removeBtn${dish.id}" onclick="removeDish('${dish.id}')">
                                                        <i class="icon-remove icon-white"></i> Remove
                                                    </button>
                                                </td>
                                            </c:if>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">Dish</h3>
            </div>
            <div class="modal-body offset1" >
                <iframe src="" style="zoom:0.60;" frameborder="0" height="600px" width="99.6%" scrolling="no"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true"><i class="icon-ban-circle icon-white"></i> Cancel</button>
                <button id="save" class="btn btn-success" type="button" onclick="dishAction()">
                    <i class="icon-ok icon-white"></i> Save
                </button>
            </div>
        </div>
    </jsp:body>

</t:genericpage>