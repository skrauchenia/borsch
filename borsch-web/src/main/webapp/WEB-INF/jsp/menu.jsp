<%--
    Document   : menu
    Created on : Jul 19, 2013, 10:56:21 AM
    Author     : Tima, Andrew Zhilka
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
            $("#navMenu").addClass("active");
            function loadModal(method, id) {
                
                if (arguments.length === 1) {
                    src += "edit/dish/" + method + "?internal=true";
                }
                else {
                    src += "edit/dish/" + id + "/" + method + "?internal=true";
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
            function addDish(course) {
                var src = "${contextPath}/";
                src += "edit/dish/add/" + course + "?internal=true";
                load(src);
            }
            function editDish(id) {
                var src = "${contextPath}/";
                src += "edit/dish/" + id + "/edit" + "?internal=true";
                load(src);
            }
            function removeDish(id) {
                var src = "${contextPath}/edit/dish/" + id + "/remove";
                $.post(src, {removeId: id});
                $('#row' + id).hide(100, function() {
                    $('#row' + id).remove();
                });
            }

            function dishAction() {
//                $.post($('#dishForm').attr('action'), $('#dishForm').serialize())
                  var form = $('#dishForm',  $("#myModal iframe").contents())
                  $.ajax({
                    url: form.attr('action'),
                    data: form.serialize(),
                    type: 'POST',
                    dataType: "json",
                    error: function(request,error)
                    {
                      console.log(arguments);
                      alert ( " Can't do because of " + error );
                    }
                  }).done(function(response){
                            var tableId = "#"+response.course + "Table";

                            if (form.attr('action').search("add") != -1) {
                                var newRow = $(tableId + " tr:nth-child(2)").clone();
                                newRow.attr("id", "row"+response.id);
                                newRow.children("[id^=index]").html($(tableId + " tr").length);
                                newRow.children("[id^=index]").attr("id", "index" + response.id);

                                newRow.children("[id^=dishName]").html(response.name);
                                newRow.children("[id^=dishName]").attr("id", "dishName" + response.id);

                                newRow.children("[id^=dishPrice]").html(response.price);
                                newRow.children("[id^=dishPrice]").attr("id", "dishPrice" + response.id);

                                var editBtn = newRow.find("[id^=editBtn]");
                                editBtn.removeAttr("onclick");
                                editBtn.on("click", function(){editDish(response.id)});
                                editBtn.attr("id", "editBtn" + response.id);
                                console.log(editBtn.attr("id"));

                                var removeBtn = newRow.find("[id^=removeBtn]");
                                removeBtn.removeAttr("onclick");
                                removeBtn.on("click", function(){removeDish(response.id)});
                                removeBtn.attr("id", "removeBtn" + response.id);

                                $(tableId).append(newRow);
                            } else {
//                                var editedRow = $(tableId).find()
                                $("#row"+response.id).find("[id^=dishName]").html(response.name);
                                $("#row"+response.id).find("[id^=dishPrice]").html(response.price);
                            }
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
                            <table class="table table-condensed" id="firstCourseTable">
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th>
                                        <button type="submit" class="btn btn-success" style="float: right" onclick="addDish('FIRST_COURSE')">
                                            <i class="icon-plus icon-white"></i> Add
                                        </button>
                                    </th>
                                </tr>
                                <c:forEach var="dish" items="${courseList.firstCourse}" varStatus="st">
                                    <tr id="row${dish.id}">
                                        <td id="index${dish.id}">${st.index+1}</td>
                                        <td id="dishName${dish.id}">${dish.name}</td>
                                        <td id="dishPrice${dish.id}">${dish.price}</td>
                                        <td>
                                            <button type="submit" class="btn btn-success">
                                                <i class="icon-ok icon-white"></i> Add to order
                                            </button>
                                            <button type="submit" class="btn btn-danger">
                                                <i class="icon-remove icon-white"></i> Remove from order
                                            </button>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-info" id="editBtn${dish.id}" onclick="editDish('${dish.id}')">
                                                <i class="icon-pencil icon-white"></i> Edit
                                            </button>
                                            <button type="submit" class="btn btn-danger" id="removeBtn${dish.id}" onclick="removeDish('${dish.id}')">
                                                <i class="icon-remove icon-white"></i> Remove
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
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
                            <table class="table table-condensed"  id="secondCourseTable">
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th>
                                        <button type="submit" class="btn btn-success" style="float: right" onclick="addDish('SECOND_COURSE')">
                                            <i class="icon-plus icon-white"></i> Add
                                        </button>
                                    </th>
                                </tr>
                                <c:forEach var="dish" items="${courseList.secondCourse}" varStatus="st">
                                    <tr id="row${dish.id}">
                                        <td id="index${dish.id}">${st.index+1}</td>
                                        <td id="dishName${dish.id}">${dish.name}</td>
                                        <td id="dishPrice${dish.id}">${dish.price}</td>
                                        <td>
                                            <button type="submit" class="btn btn-success">
                                                <i class="icon-ok icon-white"></i> Add to order
                                            </button>
                                            <button type="submit" class="btn btn-danger">
                                                <i class="icon-remove icon-white"></i> Remove from order
                                            </button>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-info" id="editBtn${dish.id}" onclick="editDish('${dish.id}')">
                                                <i class="icon-pencil icon-white"></i> Edit
                                            </button>
                                            <button type="submit" class="btn btn-danger" id="removeBtn${dish.id}" onclick="removeDish('${dish.id}')">
                                                <i class="icon-remove icon-white"></i> Remove
                                            </button>
                                        </td>
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
                            <table class="table table-condensed" id="dessertTable">
                                <tr>
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th>
                                        <button type="submit" class="btn btn-success" style="float: right" onclick="addDish('DESSERT')">
                                            <i class="icon-plus icon-white"></i> Add
                                        </button>
                                    </th>
                                </tr>
                                <c:forEach var="dish" items="${courseList.dessert}" varStatus="st">
                                    <tr id="row${dish.id}">
                                        <td id="index${dish.id}">${st.index+1}</td>
                                        <td id="dishName${dish.id}">${dish.name}</td>
                                        <td id="dishPrice${dish.id}">${dish.price}</td>
                                        <td>
                                            <button type="submit" class="btn btn-success">
                                                <i class="icon-ok icon-white"></i> Add to order
                                            </button>
                                            <button type="submit" class="btn btn-danger">
                                                <i class="icon-remove icon-white"></i> Remove from order
                                            </button>
                                        </td>
                                        <td>
                                            <button type="submit" class="btn btn-info" id="editBtn${dish.id}" onclick="editDish('${dish.id}')">
                                                <i class="icon-pencil icon-white"></i> Edit
                                            </button>
                                            <button type="submit" class="btn btn-danger" id="removeBtn${dish.id}" onclick="removeDish('${dish.id}')">
                                                <i class="icon-remove icon-white"></i> Remove
                                            </button>
                                        </td>
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
                <button id="save" class="btn btn-success" type="button" onclick="dishAction()">
                    <i class="icon-ok icon-white"></i> Save
                </button>
            </div>
        </div>
    </jsp:body>

</t:genericpage>