<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Edit profile</title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
        var doRegistration = false;
        function nameCheck() {
            var nameStr = $('#name').val();
            if (nameStr.length == 0) {
                $('#alertName').show();
                doRegistration = false;
            } else {
                $('#alertName').hide();
                doRegistration = true;
            }
        }
        function localeCheck() {
            if ($('#locale_select').val() === "") {
                $('#alertLocale').show();
                doRegistration = false;
            } else {
                $('#alertLocale').hide('hide');
                doRegistration = true;
            }
        }
        function ajaxSubmit()
        {
            if (doRegistration) {
                var form = $('#profileEditForm');
                $.post(form.attr('action'), form.serialize())
                        .fail(function() {
                            $('.alert-danger').show();
                        })
                        .done(function(response) {
                            var valid = true;
                            $.each(response, function(key, value){
                                if(value == true) {
                                    valid = false;
                                    var target = '#' + key;
                                    //$(target).show();
                                }
                            });
                            if (valid) {
                                $('.alert-success').show();
                            }
                        });
            } else {
                if ($('#locale_select').val() === "") {
                    $('#alertLocale').show();
                }
            }
        }
    </script>
    </jsp:attribute>

    <jsp:body>
        <div class="row">
            <div class="alert alert-danger hide fade in span4 offset3">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <strong><spring:message code="form.submit.fail.title"/></strong> <spring:message code="form.submit.fail"/>.
            </div>
        </div>
        <div class="row">
            <div class="alert alert-success hide fade in span4 offset3">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                <spring:message code="form.submit.success"/>
            </div>
        </div>
        <div class="container">
            <form:form class="form-horizontal" id="profileEditForm" commandName="userCommand"
                  action="${contextPath}/edit/user/${userCommand.id}/edit" method="post">
                <div class="control-group">
                    <label class="control-label" for="name"><spring:message code="form.name"/></label>
                    <div class="controls">
                        <form:input id="name" name="name" title="name" path="name" onkeyup="nameCheck()"></form:input>
                        <span class="help-inline hide" id="alertName">
                            <strong>Error!</strong> <spring:message code="form.validation.notEmpty"/>.
                        </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="locale_select"><spring:message code="form.language"/></label>
                    <div class="controls">
                        <form:select class="selectpicker" path="locale" id="locale_select" onchange="localeCheck()">
                            <form:option value="">Select Locale</form:option>
                            <form:option value="en_US">English(US)</form:option>
                            <form:option value="ru_RU">Русский</form:option>
                        </form:select>
                        <span class="help-inline hide" id="alertLocale">
                            <strong>Error!</strong> <spring:message code="form.validation.locale"/>.
                        </span>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label" for="emailNotification"><spring:message code="form.email"/></label>
                    <div class="controls">
                        <form:checkbox id="emailNotification" path="needEmailNotification"/>
                    </div>
                </div>
                <sec:authorize access="hasRole('ROLE_EDIT_PROFILE')">
                    <div class="controls" name="role">
                        <form:checkboxes path="rights" items="${allRights}"/>
                        <span class="help-inline hide" id="alertRights">
                            <strong>Error!</strong> <spring:message code="form.validation.roles"/>.
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