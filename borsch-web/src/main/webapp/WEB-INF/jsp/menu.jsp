<%-- 
    Document   : menu
    Created on : Jul 19, 2013, 10:56:21 AM
    Author     : Fedor
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<t:genericpage>

    <jsp:attribute name="head">
        <title>Menu</title>
    </jsp:attribute>

    <jsp:attribute name="scripts">
        <script>
            $("#navHome").removeClass();
            $("#navUsers").removeClass();
            $("#navMenu").addClass("active");
            function loadModal(method, id) {
                var src = "${contextPath}/";
                if (arguments.length === 1) {
                    src += method + "/dish?internal=true";
                }
                else {
                    src += method + "/dish/" + id + "?internal=true";
                }
                load(src);
            }
            function load(src) {
                var frame = $(".modal-body").children('iframe');
                frame.attr("src", src);
                $('#myModal').modal({show: true});
                frame.load(function() {
                    var form = frame.contents().find('.form-horizontal');
                    $('#save').click(function() {
                        form.submit();
                        $('#myModal').modal('hide');
                    });
                });
            }
            function removeDish(id, rowId) {
                var src = "${contextPath}/edit/dish/" + id + "/remove";
                $.post(src, {removeId: id});
                $('#row' + rowId).hide(1000, function() {
                    $('#row' + rowId).remove();
                });
            }
        </script>
    </jsp:attribute>

    <jsp:body>
        <div class="container">
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                            First course:
                        </a>
                    </div>
                    <div id="collapseOne" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <table class="table table-condensed">
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th>
                                        <button type="submit" class="btn btn-success" style="float: right" onclick="loadModal('add')">
                                            <i class="icon-plus icon-white"></i> Add
                                        </button>
                                    </th>
                                </tr>
                                <c:forEach var="dish" items="${firstCourses}" varStatus="st">
                                    <tr id="row${st.index}">
                                        <th>${st.index+1}</th>
                                        <th>${dish.name}</th>
                                        <th>${dish.price}</th>
                                        <th>
                                            <button type="submit" class="btn btn-success">
                                                <i class="icon-ok icon-white"></i> Add to order
                                            </button>
                                            <button type="submit" class="btn btn-danger">
                                                <i class="icon-remove icon-white"></i> Remove from order
                                            </button>
                                        </th>
                                        <th>
                                            <button type="submit" class="btn btn-info" onclick="loadModal('edit', '${dish.id}')">
                                                <i class="icon-pencil icon-white"></i> Edit
                                            </button>
                                            <button type="submit" class="btn btn-danger" onclick="removeDish('${dish.id}', '${st.index}')">
                                                <i class="icon-remove icon-white"></i> Remove
                                            </button>
                                        </th>
                                    </tr>
                                </c:forEach>
                                <tr id="rowsemki">
                                    <td>Семки</td>
                                    <td>228</td>
                                    <th>
                                        <button type="submit" class="btn btn-success">
                                            <i class="icon-ok icon-white"></i> Add to order
                                        </button>
                                        <button type="submit" class="btn btn-danger">
                                            <i class="icon-remove icon-white"></i> Remove from order
                                        </button>
                                    </th>
                                    <th>
                                        <button type="button" onclick="loadModal('edit', '123')" class="btn btn-success" data-toggle="modal">
                                            <i class="icon-pencil icon-white"></i> Edit
                                        </button>
                                        <button type="button" class="btn btn-danger" onclick="removeDish('123','semki')">
                                            <i class="icon-remove icon-white"></i> Remove
                                        </button>
                                    </th>
                                </tr>

                            </table>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo">
                            Second course:
                        </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse">
                        <div class="accordion-inner">
                            <table class="table table-condensed">
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th>
                                        <button type="submit" class="btn btn-success" style="float: right">
                                            <i class="icon-plus icon-white"></i> Add
                                        </button>
                                    </th>
                                </tr>
                                <c:forEach var="dish" items="${secondCourses}">
                                    <tr>
                                        <th>${dish.name}</th>
                                        <th>${dish.price}</th>
                                        <th>
                                            <button type="submit" class="btn btn-success" value="${dish.id}">
                                                <i class="icon-ok icon-white"></i> Add to order
                                            </button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}">
                                                <i class="icon-remove icon-white"></i> Remove from order
                                            </button>
                                        </th>
                                        <th>
                                            <button type="submit" class="btn btn-info" value="${dish.id}">
                                                <i class="icon-pencil icon-white"></i> Edit
                                            </button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}">
                                                <i class="icon-remove icon-white"></i> Remove
                                            </button>
                                        </th>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseThree">
                            Dessert:
                        </a>
                    </div>
                    <div id="collapseThree" class="accordion-body collapse">
                        <div class="accordion-inner">
                            <table class="table table-condensed">
                                <tr>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th>
                                        <button type="submit" class="btn btn-success" style="float: right">
                                            <i class="icon-plus icon-white"></i> Add
                                        </button>
                                    </th>
                                </tr>
                                <c:forEach var="dish" items="${dessert}">
                                    <tr>
                                        <th>${dish.name}</th>
                                        <th>${dish.price}</th>
                                        <th>
                                            <button type="submit" class="btn btn-success" value="${dish.id}">
                                                <i class="icon-ok icon-white"></i> Add to order
                                            </button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}">
                                                <i class="icon-remove icon-white"></i> Remove from order
                                            </button>
                                        </th>
                                        <th>
                                            <button type="submit" class="btn btn-info" value="${dish.id}">
                                                <i class="icon-pencil icon-white"></i> Edit
                                            </button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}">
                                                <i class="icon-remove icon-white"></i> Remove
                                            </button>
                                        </th>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h3 id="myModalLabel">Dish</h3>
            </div>
            <div class="modal-body offset1">
                <iframe src="" style="zoom:0.60;" frameborder="0" height="650" width="99.6%"></iframe>
            </div>
            <div class="modal-footer">
                <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true"><i class="icon-ban-circle icon-white"></i> Cancel</button>
                <button id="save" class="btn btn-success" type="submit"><i class="icon-ok icon-white"></i> Save</button>
            </div>
        </div>
    </jsp:body>

</t:genericpage>