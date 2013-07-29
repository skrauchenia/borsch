
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Orders</title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script type="text/javascript">
            $("#navOrders").addClass("active");
//            function sendResponse(userId, rowId) {
//                $.post("${contextPath}/edit/user/"+userId+"/remove",{removeId: userId});
//                $('#'+rowId).hide(1000, function () {
//                    $(this).remove();
//                });
//            }
//            function openModal(userId) {
//                var frame = $(".modal-body").children('iframe');
//                frame.attr('src','${contextPath}/edit/user/'+userId+"/?internal=true");
//                frame.load(function() {
//                    frame.contents().find('.btn').hide();
//                    var form = frame.contents().find('.form-horizontal');
//                    $('.btn-primary').click(function () {
//                        form.find('.btn').trigger('click');
//                    });
//                });
//            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <table class="table table-striped">
                <tr>
                    <th><spring:message code="users.table.number"/></th>
                    <th><spring:message code="users.table.name"/></th>
                </tr>
                <c:forEach var="order" items="${orders}" varStatus="st">
                    <tr id="${st.index}">
                        <td>${st.index+1}</td>
                        <td>${order.owner.name}</td>
                        <td>${order.name}</td>
                        <td>${order.email}</td>
                    </tr>
                </c:forEach>
            </table>

        </div>
    </jsp:body>

</t:genericpage>