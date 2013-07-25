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
        <title>Edit profile</title>
    </jsp:attribute>

    <jsp:body>
        <div class="container" >
            <div class="control-group">
                <label class="control-label" for="name">Name</label>
                <div class="controls">
                    <form:input type="text" name="name" path="name"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="dish">Price</label>
                <div class="controls">
                    <form:input type="text" name="price" path="price"/>
                </div>
            </div>
            <div class="control-group">
                <label class="control-label" for="corse">Course</label>
                <div class="controls">
                    
                </div>
            </div>
        </div>
    </jsp:body>

</t:genericpage>
