<%-- 
    Document   : navbar
    Created on : Jul 23, 2013, 5:54:57 PM
    Author     : zubr
--%>

<%@tag description="Produce bootstrap navigation bar" pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div class="btn-group" style="float: right">
        <a class="btn dropdown-toggle btn-info" data-toggle="dropdown" href="#">
            <i class="icon-user icon-white"></i>
            User
            <span class="caret"></span>
        </a>
        <ul class="dropdown-menu">
            <li><a href="#">Edit</a></li>
            <li>
                <table>
                    <tr>
                        <td>Locale</td>
                        <td>
                            <div class="btn-group">
                                <button class="btn active">Eng</button>
                                <button class="btn">Rus</button>
                            </div>
                        </td>
                    </tr>
                </table>
            </li>
            <li class="divider"></li>
            <li><a href="#">Logout</a></li>
        </ul>
    </div>
    <h3 class="muted">Borsch</h3>
    <div id="myNav" class="navbar">
        <div class="navbar-inner">
                <ul class="nav">
                    <li id="navHome"><a href="home">Home</a></li>
                    <li id="navMenu"><a href="menu">Menu</a></li>
                    <li id="navUsers"><a href="users">Users</a></li>
                </ul>
        </div>
    </div>
</div>