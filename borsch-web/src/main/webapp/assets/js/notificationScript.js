/**
 * Author : Andrew Zhilka
 */

setInterval(notificationLookup, 60 * 1000);

function notificationLookup() {
    $.ajax({
        url : "/notification",
        type : 'POST'
    }).done(function(response){
            if (response.length == 0) {
                return;
            }
            $("#notificationMessage").html(response);
            $(".notification").show();
        })
        .fail(function(jqXHR, errorStatus){
            console.log("Ajax Notification Request : Error occured");
            console.log(jqXHR);
            console.log(errorStatus);
        });
}
