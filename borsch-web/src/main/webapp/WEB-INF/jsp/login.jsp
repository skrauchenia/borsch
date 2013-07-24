<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <c:set var="contextPath" value="${pageContext.request.contextPath}"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign in</title>
        <link href="${contextPath}/assets/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="${contextPath}/assets/css/signin.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${contextPath}/assets/js/jquery-2.0.3.min.js"></script>
        <script type="text/javascript" src="${contextPath}/assets/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong>Error!</strong> Incorrect login or password
                </div>
            </c:if>
            <form action="${contextPath}/j_spring_security_check" class="form-signin" method="post">
                <h2 class="form-signin-heading">Sign in</h2>
                <input type="text" class="input-block-level" placeholder="login" required="true" name="j_username">
                <input type="password" class="input-block-level" placeholder="password" required="true" name="j_password">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
                <button class="btn btn-large btn-primary" type="submit">Sign in</button>
            </form>
        </div>
    </body>
</html>
