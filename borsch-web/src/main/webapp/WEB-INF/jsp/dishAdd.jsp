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

    <jsp:body>
        <form class="form-horizontal" id="dishForm" action="${contextPath}/edit/dish/${action}/save" method="POST">
            <input type="hidden" name="id" value="${id}"/>
            <input type="hidden" name="course" value="${course}"/>
            <div class="container">
                <div class="offset2">
                    <c:if test="${action eq 'add'}">
                        <h4 id="myModalLabel">Add dish:</h4>
                    </c:if>
                    <c:if test="${action eq 'edit'}">
                        <h4 id="myModalLabel">Edit dish:</h4>
                    </c:if>    
                </div>
                <div class="control-group">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                        <input type="text" id="name" name="name" value="${name}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="price">Price</label>
                    <div class="controls">
                        <input type="text" id="price" name="price" value="${price}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="description">Description</label>
                    <div class="controls">
                        <textarea class="form-horizontal" id="description" name="description" rows="3" placeholder="Description..."></textarea>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>

</t:genericpage>