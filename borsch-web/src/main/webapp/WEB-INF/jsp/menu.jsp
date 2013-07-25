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
            function openModal(id, name, price) {
                var frameSrc = "${contextPath}/edit/dish/" + id + "/" + name + "/" + price + "?internal=true";
                $('iframe').attr("src", frameSrc);
                $('#myModal').modal({show: true});
            }
            /*$('#openModal').click(function(name, price, id) {
             $('#myModal').on('show', function() {
             
             $('iframe').attr("src", frameSrc);
             //$('iframe').attr("src", name);
             //$('iframe').attr("src", price);
             //$('iframe').attr("src",id);
             
             });
             $('#myModal').modal({show: true});
             });*/
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
                                    <th><button type="submit" class="btn btn-success" style="float: right"><i class="icon-plus icon-white"></i> Add</button></th>
                                </tr>
                                <c:forEach var="dish" items="${firstCourses}">
                                    <tr>
                                        <th>${dish.name}</th>
                                        <th>${dish.price}</th>
                                        <th>
                                            <button type="submit" class="btn btn-success" value="${dish.id}"><i class="icon-ok icon-white"></i> Add to order</button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}"><i class="icon-remove icon-white"></i> Remove from order</button>
                                        </th>
                                        <th>
                                            <button type="submit" class="btn btn-info" value="${dish.id}"><i class="icon-pencil icon-white"></i> Edit</button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}"><i class="icon-remove icon-white"></i> Remove</button>
                                        </th>
                                    </tr>
                                </c:forEach>
                                <tr>
                                    <td>Семки</td>
                                    <td>228</td>
                                    <th>
                                        <button type="submit" class="btn btn-success"><i class="icon-ok icon-white"></i> Add to order</button>
                                        <button type="submit" class="btn btn-danger"><i class="icon-remove icon-white"></i> Remove from order</button>
                                    </th>
                                    <th>
                                        <button type="button" onclick="openModal('123', 'asd', '123')" class="btn btn-success" data-toggle="modal"><i class="icon-pencil icon-white"></i> Edit</button>
                                        <button type="submit" class="btn btn-danger"><i class="icon-remove icon-white"></i> Remove</button>
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
                                    <th><button type="submit" class="btn btn-success" style="float: right"><i class="icon-plus icon-white"></i> Add</button></th>
                                </tr>
                                <c:forEach var="dish" items="${secondCourses}">
                                    <tr>
                                        <th>${dish.name}</th>
                                        <th>${dish.price}</th>
                                        <th>
                                            <button type="submit" class="btn btn-success" value="${dish.id}"><i class="icon-ok icon-white"></i> Add to order</button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}"><i class="icon-remove icon-white"></i> Remove from order</button>
                                        </th>
                                        <th>
                                            <button type="submit" class="btn btn-info" value="${dish.id}"><i class="icon-pencil icon-white"></i> Edit</button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}"><i class="icon-remove icon-white"></i> Remove</button>
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
                                    <th><button type="submit" class="btn btn-success" style="float: right"><i class="icon-plus icon-white"></i> Add</button></th>
                                </tr>
                                <c:forEach var="dish" items="${dessert}">
                                    <tr>
                                        <th>${dish.name}</th>
                                        <th>${dish.price}</th>
                                        <th>
                                            <button type="submit" class="btn btn-success" value="${dish.id}"><i class="icon-ok icon-white"></i> Add to order</button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}"><i class="icon-remove icon-white"></i> Remove from order</button>
                                        </th>
                                        <th>
                                            <button type="submit" class="btn btn-info" value="${dish.id}"><i class="icon-pencil icon-white"></i> Edit</button>
                                            <button type="submit" class="btn btn-danger" value="${dish.id}"><i class="icon-remove icon-white"></i> Remove</button>
                                        </th>
                                    </tr>
                                </c:forEach>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <form:form commandName="dish" class="form-horizontal offset1" action="${contextPath}/edit/dish/${id}/save">
            <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                    <h3 id="myModalLabel">Edit dish</h3>
                </div>
                <div class="modal-body offset1">
                    <iframe src="" style="zoom:0.60;" frameborder="0" height="400" width="99.6%"></iframe>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true"><i class="icon-ban-circle icon-white"></i> Cancel</button>
                    <button class="btn btn-success" type="submit"><i class="icon-ok icon-white"></i> Save</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</t:genericpage>