<%-- 
    Document   : index
    Created on : Jul 18, 2013, 2:56:51 PM
    Author     : tima
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title>Home</title>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script>
            $("#navHome").addClass("active")
            $("#navUsers").removeClass();
            $("#navMenu").removeClass();
            window.onload = function() {
                for (var i = 1; i < 6; i++) {
                    if (1 != i) {
                        $("#day" + i + "info").hide();
                    }
                }
            }();
            function rotate(id) {
                var dayCount = $("#days > li").length;
                for (var i = 1; i <= dayCount; i++) {
                    $(("#day" + i)).removeClass();
                    $(("#day" + i + "info")).hide(400);
                }
                $("#day" + id).addClass("active");
                $("#day" + id + "info").show(400);
            }

            $("#days > li").click(function() {
                rotate(this.id.substr(3));
            });
        </script>
    </jsp:attribute>
        
    <jsp:body>
        <div class="container">
            <br/>
            <div class="container-fluid">
                <div class="span3">
                    <ul class="nav nav-pills nav-stacked" id="days">
                        <li id="day1" class="active"><a>Monday</a></li>
                        <li id="day2"><a>Tuesday</a></li>
                        <li id="day3"><a>Wednesday</a></li>
                        <li id="day4"><a>Thursday</a></li>
                        <li id="day5"><a>Friday</a></li>
                    </ul>
                </div>
                <div id="day1info" class="span6">
                    <h3>Monday</h3>
                    <hr/>
                    Here will be the table
                    <hr/>
                    <a href="#myModal" role="button" class="btn btn-success" data-toggle="modal" style="float: right">More info</a>
                </div>
                <div id="day2info" class="span6">
                    Tuesday
                </div>
                <div id="day3info" class="span6">
                    Wednesday
                </div>
                <div id="day4info" class="span6">
                    Thursday
                </div>
                <div id="day5info" class="span6">
                    Friday
                </div>
                <!-- Modal -->
                <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="myModalLabel">Modal header</h3>
                    </div>
                    <div class="modal-body">
                        <iframe src="${contextPath}/menu?internal=true" style="zoom:0.60;" frameborder="0" height="250" width="99.6%"></iframe>
                    </div>
                    <div class="modal-footer">
                        <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
        
</t:genericpage>