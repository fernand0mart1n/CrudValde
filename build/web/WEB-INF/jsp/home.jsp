<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp"></jsp:include>
    <body>
        <div class="row">
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
                                        <button class="btn btn-danger"><span class="glyphicon glyphicon-trash"></span> Eliminar</button>
                                    </div>                                    
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <hr>
                <a class="btn btn-success pull-right" href="/CrudValde/nuevo"><span class="glyphicon glyphicon-plus"></span> Nuevo cliente</a>
            </div>
        </div>
    </body>
</html>
