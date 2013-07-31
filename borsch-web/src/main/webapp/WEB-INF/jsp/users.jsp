<%--
    Document   : users
    Created on : Jul 22, 2013, 11:27:26 AM
    Author     : Fedor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Users</title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $("#navUsers").addClass("active");
            function sendResponse(userId, rowId) {
                $.post("${contextPath}/edit/user/"+userId+"/remove",{removeId: userId});
                $('#'+rowId).hide(1000, function () {
                    $(this).remove();
                });
            }
            function openModal(userId) {
                var frame = $(".modal-body").children('iframe');
                frame.attr('src','${contextPath}/edit/user/'+userId+"/?internal=true");
                frame.load(function() {
                    frame.contents().find('.btn').hide();
                    var form = frame.contents().find('.form-horizontal');
                    $('.btn-primary').click(function () {
                        form.find('.btn').trigger('click');
                    });
                });

            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <table class="table table-striped">
                <tr>
                    <th><spring:message code="users.table.number"/></th>
                    <th><spring:message code="users.table.login"/></th>
                    <th><spring:message code="users.table.name"/></th>
                    <th><spring:message code="users.table.email"/></th>
                    <th><a href="${contextPath}/add/user" type="submit" class="btn btn-success hide" style="float: right"><i class="icon-plus icon-white"></i> <spring:message code="users.table.add.user"/></a></th>
                </tr>
                <c:forEach var="curUser" items="${users}" varStatus="st">
                    <tr id="${st.index}">
                        <td>${st.index+1}</td>
                        <td>${curUser.login}</td>
                        <td>${curUser.name}</td>
                        <td>${curUser.email}</td>
                        <td>
                            <a href="#editModal" onclick="openModal('${curUser.id}');" class="btn btn-info" data-toggle="modal" ><i class="icon-pencil icon-white"></i> <spring:message code="users.table.edit"/></a>
                            <a onclick="sendResponse('${curUser.id}',${st.index});" class="btn btn-danger"><i class="icon-remove icon-white"></i> <spring:message code="users.table.remove"/></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <!-- Modal -->
            <div id="editModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel"><spring:message code="users.table.edit"/></h3>
                </div>
                <div class="modal-body">
                    <iframe src="" frameborder="0" height="200" width="99.6%"></iframe>
                </div>
                <div class="modal-footer">
                    <button class="btn" data-dismiss="modal" aria-hidden="true"><spring:message code="users.table.modal.close"/></button>
                    <button class="btn btn-primary"><spring:message code="users.table.modal.save"/></button>
                </div>
            </div>
        </div>
    </jsp:body>

</t:genericpage>