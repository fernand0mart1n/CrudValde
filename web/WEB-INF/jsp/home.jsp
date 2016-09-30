<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp"></jsp:include>
        <div id="myModal" class="modal fade" tabindex="-1" role="dialog">
          <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header modal-danger">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel"><i class="fa fa-exclamation-triangle"></i> Eliminar cliente</h4>
                </div>
                <div class="modal-body">
                    ¿Está seguro que desea eliminar este cliente?
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button id="btnDel" type="button" class="btn btn-danger">Eliminar</button>
                </div>
            </div>
          </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->
        <div class="col-xs-offset-2 col-xs-8 ">
            <table class="table table-responsive table-striped table-hover table-condensed">
                <thead>
                    <h3>Clientes</h3>
                </thead>
                <tbody>
                    <tr>
                        <th>
                            Nombre
                        </th>
                        <th>
                            Apellido
                        </th>
                        <th>
                            Edad
                        </th>
                        <th>
                            Nacionalidad
                        </th>
                        <th>
                            Activo
                        </th>
                        <th>
                            Acciones
                        </th>
                    </tr>
                    <c:forEach var="cliente" items="${resultado}">
                        <tr>
                            <td>
                                ${cliente.nombre}
                            </td>
                            <td>
                                ${cliente.apellido}
                            </td>
                            <td>
                                ${cliente.fecha_nac}
                            </td>
                            <td>
                                ${cliente.nacionalidad.nacionalidad}
                            </td>
                            <td>
                                <c:if test="${cliente.activo == 1}">
                                    Si
                                </c:if>
                                <c:if test="${cliente.activo == 0}">
                                    No
                                </c:if>
                            </td>
                            <td>
                                <div class="btn-group">
                                    <a class="btn btn-info" href="/CrudValde/ver?id=${cliente.id}"><span class="glyphicon glyphicon-eye-open"></span> Ver</a>
                                    <a class="btn btn-primary" href="/CrudValde/editar?id=${cliente.id}"><span class="glyphicon glyphicon-pencil"></span> Editar</a>
                                    <button type="button" class="btn btn-danger" id="${cliente.id}" onclick="eliminar(${cliente.id})"><span class="glyphicon glyphicon-trash"></span> Eliminar</button>
                                </div>                                    
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <hr>
            <a class="btn btn-success pull-right" href="/CrudValde/nuevo"><span class="glyphicon glyphicon-plus"></span> Nuevo cliente</a>
        </div>
    </body>
</html>
