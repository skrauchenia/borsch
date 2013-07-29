<%-- 
    Document   : menuEdit
    Created on : Jul 25, 2013, 10:32:48 AM
    Author     : Tima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<t:genericpage>

    <jsp:attribute name="head">
        <title>Edit dish</title>
    </jsp:attribute>

    <jsp:body>
        <form class="form-horizontal" action="${contextPath}/edit/dish/${id}/save" method="POST">
            <div class="container" >
                <div class="offset2">
                    <h4 id="myModalLabel">Edit dish:</h4>
                </div>
                <input type="hidden" name="id" value="${id}"/>
                <div class="control-group">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                        <input type="text" id="name" value="${name}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="price">Price</label>
                    <div class="controls">
                        <input type="text" id="price" value="${price}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="description">Description</label>
                    <div class="controls">
                        <textarea class="form-horizontal" id="description" rows="3" placeholder="Description..."></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="course">Course</label>
                    <div class="controls">
                        <select id="course">
                            <option value="firstCourse">First course</option>
                            <option value="secondCourse">Second course</option>
                            <option value="dessert">Dessert</option>
                        </select>
                    </div>
                </div>
            </div>
        </form>
    </jsp:body>

</t:genericpage>
