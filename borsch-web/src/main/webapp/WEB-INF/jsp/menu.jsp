<%-- 
    Document   : menu
    Created on : Jul 19, 2013, 10:56:21 AM
    Author     : Fedor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags/" %>

<t:genericpage>
    
    <jsp:attribute name="head">
        <title>Menu</title>
    </jsp:attribute>
        
    <jsp:attribute name="scripts">
        <script>
            $("#navHome").removeClass();
            $("#navUsers").removeClass();
            $("#navMenu").addClass("active");
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
                                    <th>#</th>
                                    <th>Name</th>
                                    <th>Price</th>
                                    <th></th>
                                    <th><button type="submit" class="btn btn-success" style="float: right"><i class="icon-plus icon-white"></i> Add</button></th>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>Семки</td>
                                    <td>228</td>
                                    <th>
                                        <button type="submit" class="btn btn-success"><i class="icon-ok icon-white"></i> Add to order</button>
                                        <button type="submit" class="btn btn-danger"><i class="icon-remove icon-white"></i> Remove from order</button>
                                    </th>
                                    <th>
                                        <button type="submit" class="btn btn-info"><i class="icon-pencil icon-white"></i> Edit</button>
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
                            Main course:
                        </a>
                    </div>
                    <div id="collapseTwo" class="accordion-body collapse">
                        <div class="accordion-inner">
                            Anim pariatur cliche...
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
                            Anim pariatur cliche...
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
        
</t:genericpage>