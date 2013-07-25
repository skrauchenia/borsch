<%-- 
    Document   : users
    Created on : Jul 22, 2013, 11:27:26 AM
    Author     : Fedor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title>Users</title>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $("#navHome").removeClass();
            $("#navUsers").addClass("active");
            $("#navMenu").removeClass();
            function sendResponse(userId,rowId) {
                $.post("${contextPath}/edit/user/"+userId+"/remove",{removeId: userId });
                $('#userRow'+rowId).hide(1000, function () {
                    $('#userRow'+rowId).remove();
                });   
            }
        </script>
    </jsp:attribute>
        
    <jsp:body>
        <div class="container">
            <table class="table table-striped">
                <tr>
                    <th>#</th>
                    <th>Login</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th><a href="${contextPath}/add/user" type="submit" class="btn btn-success" style="float: right"><i class="icon-plus icon-white"></i> Add</a></th>
                </tr>
                <c:forEach var="curUser" items="${users}" varStatus="st">
                    <tr id='userRow${st.index}'>
                        <td>${st.index+1}</td>
                        <td>${curUser.login}</td>
                        <td>${curUser.name}</td>
                        <td>${curUser.email}</td>
                        <td>
                            <a href="${contextPath}/edit/user/${curUser.id}" class="btn btn-info"><i class="icon-pencil icon-white"></i> Edit</a>
                            <a onclick="sendResponse('${curUser.id}',${st.index});" class="btn btn-danger"><i class="icon-remove icon-white"></i> Remove</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </jsp:body>
        
</t:genericpage>