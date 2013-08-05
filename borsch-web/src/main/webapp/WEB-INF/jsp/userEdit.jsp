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
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
        var doRegistration = true;
        function nameCheck() {
            var nameStr = $('#name').val();
            if (nameStr.length == 0) {
                $('#alertName').show();
                doRegistration &= false;
            } else {
                $('#alertName').hide();
                doRegistration &= true;
            }
        }
        function localeCheck() {
            if ($('#locale_select').val() === "") {
                $('#alertLocale').show();
                doRegistration &= false;
            } else {
                $('#alertLocale').hide();
                doRegistration &= true;
            }
        }
        function validate() {
            doRegistration = true;
            nameCheck();
            localeCheck();
            if (doRegistration) {
                var form = $('#profileEditForm');
                $.post(form.attr('action'),form.serialize())
                        .done(function() {
                            $('.alert-success').show();
                        });
            }
        }
    </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="row">
                <div class="alert alert-danger hide fade in span4 offset1">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <strong><spring:message code="form.submit.fail.title"/></strong> <spring:message code="form.submit.fail"/>.
                </div>
            </div>
            <div class="row">
                <div class="alert alert-success hide fade in span4 offset1">
                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                    <spring:message code="form.submit.success"/>
                </div>
            </div>
            <form:form class="form-horizontal" id="profileEditForm" commandName="userCommand"
                  action="${contextPath}/edit/user/${userCommand.id}/edit" method="post">
                <div class="control-group">
                    <label class="control-label" for="name"><spring:message code="form.name"/></label>
                    <div class="controls">
                        <form:input id="name" name="name" title="name" path="name"></form:input>
                        <form:errors path="name" cssClass="error"></form:errors>
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
                        <form:errors path="locale" cssClass="error"></form:errors>
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
                        <form:errors path="rights" cssClass="error"></form:errors>
                        <span class="help-inline hide" id="alertRights">
                            <strong>Error!</strong> <spring:message code="form.validation.roles"/>.
                        </span>
                    </div>
                </sec:authorize>
                <div class="control-group">
                    <div class="controls">
                        <button type="button" class="btn" onclick="validate()">Submit</button>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:body>

</t:genericpage>