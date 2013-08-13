<!--
    author: Andrey Zhilka
-->

<%@tag description="Tag for showing new notifications for User" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="notifications">
    <div class="alert alert-danger hide fade in offset4 notification">
        <button type="button" class="close" data-dismiss="alert" onclick="notificationLookup()">&times;</button>
        <strong><spring:message code="user.notification.attention"/></strong>
        <p id="notificationMessage"></p>
    </div>
</div>