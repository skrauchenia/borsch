<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title>Edit profile</title>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <form class="form-horizontal" action="${contextPath}/edit/user/${user.id}/edit">
                <div class="control-group">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                        <input type="text" id="name" value="${user.name}">
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="eMail">Email</label>
                    <div class="controls">
                        <input type="text" id="eMail" value="${user.email}">
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button type="submit" class="btn">Submit</button>
                    </div>
                </div>
            </form>
        </div>
    </jsp:body>
        
</t:genericpage>