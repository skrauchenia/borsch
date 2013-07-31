<%--
    Document   : navbar
    Created on : Jul 23, 2013, 5:54:57 PM
    Author     : zubr
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <div class="btn-group" style="float: right">
        <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
            <i class="icon-user icon-white"></i>
            <sec:authentication property="principal.user.name" />
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <c:set var="userId">
                <sec:authentication property="principal.user.id"/>
            </c:set>
            <li><a href="${contextPath}/edit/user/${userId}"><spring:message code="navbar.user.edit"/></a></li>
            <li class="divider"></li>
            <li><a href="${contextPath}/j_spring_security_logout"><spring:message code="navbar.user.logout"/></a></li>
        </ul>
    </div>
    <h3 class="muted">Borsch</h3>
    <div id="myNav" class="navbar">
        <div class="navbar-inner">
                <ul class="nav">
                    <li id="navHome"><a href="${contextPath}/home"><spring:message code="navbar.home"/></a></li>
                    <li id="navMenu"><a href="${contextPath}/menu"><spring:message code="navbar.menu"/></a></li>
                    <sec:authorize access="hasRole('ROLE_EDIT_PROFILE')">
                        <li id="navUsers"><a href="${contextPath}/users"><spring:message code="navbar.users"/></a></li>
                    </sec:authorize>
                    <sec:authorize access="hasRole('ROLE_PRINT_ORDER')">
                        <li id="navReport"><a href="${contextPath}/report"><spring:message code="navbar.report"/></a></li>
                    </sec:authorize>
                </ul>
        </div>
    </div>
</div>