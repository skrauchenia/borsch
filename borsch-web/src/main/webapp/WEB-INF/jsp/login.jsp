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
    </head>
    <body>
        <div class="container">
            <c:if test="${error != null}">
                <div class="alert">
                    <button type="button" class="alert" data-dismiss="alert">&times;</button>
                    <p><strong>Error!</strong>Incorrect login or password</p>
                </div>
            </c:if>
            <form action="j_spring_security_check" class="form-signin" method="post">
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
