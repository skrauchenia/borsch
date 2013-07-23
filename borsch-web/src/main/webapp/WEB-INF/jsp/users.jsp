<%-- 
    Document   : users
    Created on : Jul 22, 2013, 11:27:26 AM
    Author     : Fedor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Users</title>
    </head>
    <body>
        <jsp:directive.include file="header.jspf"/>
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
        <script>
            $("#navHome").removeClass();
            $("#navUsers").addClass("active");
            $("#navMenu").removeClass();
        </script>
    </body>
</html>
