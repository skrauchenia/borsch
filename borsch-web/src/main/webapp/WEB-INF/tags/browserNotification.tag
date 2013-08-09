<!--
    author: Andrey Zhilka
-->

<%@tag description="Tag for showing new notifications for User" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="notifications">
    <div class="alert alert-danger hide fade in span4 offset4">
        <button type="button" class="close" data-dismiss="alert" onclick="notificationLookup()">&times;</button>
        <strong><spring:message code="user.notification.attention"/></strong>
        <p id="notificationMessage"></p>
    </div>
</div>

<script>
    var notificationList;

    setInterval(notificationLookup, 2*60*60*1000);

    function notificationLookup() {
        $.ajax({
            url : "/notification",
            type : 'POST'
        }).done(function(response){
                   $("#notificationMessage").html(response);
                   $("#notificationMessage").show();
                })
          .fail(function(jqXHR, errorStatus){
                    console.log("Ajax Notification Request : Error occured");
                    console.log(jqXHR);
                    console.log(errorStatus);
                });
    }

</script>