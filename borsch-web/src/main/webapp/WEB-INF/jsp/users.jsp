<%-- 
    Document   : users
    Created on : Jul 22, 2013, 11:27:26 AM
    Author     : Fedor
--%>

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
                    <th><button type="submit" class="btn btn-success" style="float: right"><i class="icon-plus icon-white"></i> Add</button></th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>s.kravchenya</td>
                    <td>Serhey Kravchenya</td>
                    <td>s.kravchenya@exadel.com</td>
                    <th >
                        <button type="submit" class="btn btn-info"><i class="icon-pencil icon-white"></i> Edit</button>
                        <button type="submit" class="btn btn-danger"><i class="icon-remove icon-white"></i> Remove</button>
                    </th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>s.kravchenya</td>
                    <td>Serhey Kravchenya</td>
                    <td>s.kravchenya@exadel.com</td>
                    <th >
                        <button type="submit" class="btn btn-info"><i class="icon-pencil icon-white"></i> Edit</button>
                        <button type="submit" class="btn btn-danger"><i class="icon-remove icon-white"></i> Remove</button>
                    </th>
                </tr>

            </table>
        </div>
    </jsp:body>
        
</t:genericpage>