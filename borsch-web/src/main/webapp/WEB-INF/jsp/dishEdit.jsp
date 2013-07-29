<%-- 
    Document   : menuEdit
    Created on : Jul 25, 2013, 10:32:48 AM
    Author     : Fedor
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
                        <input type="text" name="name" value="${name}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="dish">Price</label>
                    <div class="controls">
                        <input type="text" name="price" value="${price}"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="dish">Description</label>
                    <div class="controls">
                        <textarea class="form-horizontal" name="description" rows="3" placeholder="Description..."></textarea>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="corse">Course</label>
                    <div class="controls">
                        <select name="course">
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
