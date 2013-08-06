<%-- 
    Document   : dishNew
    Created on : Jul 28, 2013, 12:13:35 PM
    Author     : Tima
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<t:genericpage>

    <jsp:attribute name="head">
        <title>Dish</title>
    </jsp:attribute>
    <jsp:attribute name="scripts">
        <script>
            $("#emptyName").hide();
            $("#emptyPrice").hide();
            $("#notNumber").hide();
            function validate() {
//                alert("aa");
                var price = $("#price").val();
                var name = $("#name").val();
                if ((price.length === 0) || (isNaN(price)) || (name.length === 0)) {
                    return false;
                } else {
                    return true;
                }
            }
            
            if (!validate()) {
                $('#save', window.parent.document).attr("disabled", "");
            } else {
                $('#save', window.parent.document).removeAttr("disabled");
            }
            var flagNum;
            var flagName;
            if(validate()) {
                flagNum = flagName = true;
            } else {
                flagNum = false;
            }            
            function isNumber() {
                flagNum = true;
                $("#cgPrice").removeClass("error");
                $("#cgPrice").removeClass("success");
                $("#emptyPrice").hide();
                $("#notNumber").hide();
                var value = $("#price").val();
//                alert(value);
                if (isNaN(value)) {
                    $("#notNumber").show();
                    $('#save', window.parent.document).attr("disabled", "");
                    flagNum = false;
                }
                if (value.length === 0) {
                    $("#emptyPrice").show();
                    $('#save', window.parent.document).attr("disabled", "");
                    flagNum = false;
                }
                if(!flagNum) {
                    $("#cgPrice").addClass("error");
                } else {
                    $("#cgPrice").addClass("success");
                }
                if(flagNum && flagName) {
                    $('#save', window.parent.document).removeAttr("disabled");
                }
            }
            function isEmptyName() {
                flagName = true;
                $("#cgName").removeClass("error");
                $("#cgName").removeClass("success");
                $("#emptyName").hide();
                var value = $("#name").val();
//                alert(value);
                if (value.length === 0) {
                    $("#emptyName").show();
                    $('#save', window.parent.document).attr("disabled", "");
                    flagName = false;
                }
                if(!flagName) {
                    $("#cgName").addClass("error");
                } else {
                    $("#cgName").addClass("success");
                }
                if(flagNum && flagName) {
                    $('#save', window.parent.document).removeAttr("disabled");
                }
            }
        </script>
    </jsp:attribute>
    <jsp:body>
        <form class="form-horizontal" id="dishForm" action="${contextPath}/edit/dish/${action}/save" method="POST">
            <input type="hidden" name="id" value="${id}"/>
            <input type="hidden" name="course" value="${course}"/>
            <div class="container">
                <div class="offset1">
                    <c:if test="${action eq 'add'}">
                        <h4 id="myModalLabel">Add dish:</h4>
                    </c:if>
                    <c:if test="${action eq 'edit'}">
                        <h4 id="myModalLabel">Edit dish:</h4>
                    </c:if>    
                </div>
                <div class="control-group" id="cgName">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                        <input type="text" id="name" name="name" value="${dish.name}" onkeyup="isEmptyName()"/>
                        <span id="emptyName" class="label label-important">Can not be empty!</span>
                    </div>
                </div>
                <div class="control-group" id="cgPrice">
                    <label class="control-label" for="price">Price</label>
                    <div class="controls">
                        <input type="text" id="price" name="price" value="${dish.price}" onkeyup="isNumber()"/>
                        <span id="notNumber" class="label label-important">Not a number!</span>
                        <span id="emptyPrice" class="label label-important">Can not be empty!</span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="description">Description</label>
                    <div class="controls">
                        <textarea class="form-horizontal" id="description" name="description" rows="3" placeholder="Description...">${dish.description}</textarea>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>

</t:genericpage>