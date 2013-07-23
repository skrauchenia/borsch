<%-- 
    Document   : genericpage
    Created on : Jul 23, 2013, 5:44:55 PM
    Author     : zubr
--%>

<%@tag description="The generic page template. Uses bootstrap, jquery and navbar"
       pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<%@attribute name="scripts" fragment="true"%>
<%@attribute name="head" fragment="true"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>

<!DOCTYPE html>
<html>
    <head>
        <t:defaulthead />
        <jsp:invoke fragment="head"/>
    </head>
    <body>
        <t:navbar />
        <jsp:doBody />
        <t:defaultscripts />
        <jsp:invoke fragment="scripts"/>
    </body>
</html>
