<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title>Edit profile</title>
        <link href="${contextPath}/assets/css/bootstrap-select.min.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript" src="${contextPath}/assets/js/bootstrap-select.min.js"></script>
        <script type="text/javascript">

            function ajaxSubmit()
            {
                var form = $('#profileEditForm');
                $.post(form.attr('action'), form.serialize())
                        .fail(function() {
                            $('.alertFail').alert();
                        })
                        .done(function(response) {
                            $.each(response, function(key, value){
                               if(value == true) {
                                   var target = '.' + key;
                                   $(target).removeClass("hide");
                               }
                            });
                        });
            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="alertFail alert-danger hide">
            <button type="button" class="close" data-dismiss="alert">&times;</button>
            <strong>Fail!</strong> Failed to send request.
        </div>
        <div class="container">
            <form:form class="form-horizontal" id="profileEditForm" commandName="userCommand"
                  action="${contextPath}/edit/user/${userCommand.id}/edit" method="post">
                <div class="control-group">
                    <label class="control-label" for="name">Name</label>
                    <div class="controls">
                        <form:input id="name" name="name" title="name" path="name"></form:input>
                        <span class="help-inline alertName hide">
                            <strong>Error!</strong><spring:message code="form.validation.size"/>.
                        </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="locale_select">Language</label>
                    <div class="controls">
                        <form:select class="selectpicker" path="locale" id="locale_select">
                            <form:option value="">Select Locale</form:option>
                            <form:option value="en_US">English(US)</form:option>
                            <form:option value="ru_RU">Русский</form:option>
                        </form:select>
                        <span class="help-inline alertName hide">
                            <strong>Error!</strong><spring:message code="form.validation.notEmpty"/>.
                        </span>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_EDIT_PROFILE')">
                    <div class="controls" name="role">
                        <form:checkboxes path="rights" items="${allRights}"/>
                        <span class="help-inline alertName hide">
                            <strong>Error!</strong><spring:message code="form.validation.notNull"/>.
                        </span>
                    </div>
                </sec:authorize>
                <div class="control-group">
                    <div class="controls">
                        <button type="button" class="btn" onclick="ajaxSubmit()">Submit</button>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:body>
        
</t:genericpage>