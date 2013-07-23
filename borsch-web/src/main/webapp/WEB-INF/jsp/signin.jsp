<%-- 
    Document   : login
    Created on : Jul 22, 2013, 10:42:23 AM
    Author     : Fedor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign in</title>
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/signin.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div class="container">
            <form action="login" class="form-signin" method="POST">
                <h2 class="form-signin-heading">Sign in</h2>
                <input type="text" class="input-block-level" placeholder="Login" required="true">
                <input type="password" class="input-block-level" placeholder="Password" required="true">
                <label class="checkbox">
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
                <button class="btn btn-large btn-primary" type="submit">Sign in</button>
            </form>
        </div>
    </body>
</html>
