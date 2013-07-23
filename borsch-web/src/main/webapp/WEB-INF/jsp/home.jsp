<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <body>
        <div id="content">
            <fmt:message key="index.property_message"/>
        </div>
        <p>Value = <c:out value="${value}"/></p>
        <p>User = <c:out value="${user}"/></p>
    </body>
</html>