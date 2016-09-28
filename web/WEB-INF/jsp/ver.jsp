<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp"></jsp:include>
    <body>
        <div class="col-xs-offset-3 col-xs-6">
            <legend>Ver cliente</legend>
            <form class="form form-horizontal" method="POST" action="/CrudValdeJava/nuevo">
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="nombre"><b>Nombre:</b></label>
                    <div class="col-xs-8">
                        <input type="text" value="<c:out value="${cliente.nombre}"/>" id="nombre" name="nombre" class="form-control" placeholder="Nombre" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="apellido"><b>Apellido:</b></label>
                    <div class="col-xs-8">
                        <input type="text" value="${cliente.apellido}" id="apellido" name="apellido" class="form-control" placeholder="Apellido" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="fecha_nac"><b>Fecha de nacimiento:</b></label>
                    <div class="col-xs-8">
                        <input type="date" value="${cliente.fechaNac}" id="fecha_nac" name="fecha_nac" class="form-control" disabled>
                    </div>
                </div>
                <div class="form-group">
                    <label for="nacionalidad" class="col-xs-4 control-label">Nacionalidad:</label>
                    <div class="col-xs-8">
                        <select class="form-control" id="nacionalidad" name="nacionalidad" disabled>
                            <option value="${cliente.nacionalidad.id}">${cliente.nacionalidad.nacionalidad}</option>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="activo"><b>Activo:</b></label>
                    <div class="col-xs-8">
                        <c:if test="${cliente.activo}">
                            <input type="radio" id="activo" name="activo" value="${cliente.activo}" checked readonly> Si<br>
                            <input type="radio" id="activo" name="activo" value="${cliente.activo}" readonly> No<br>
                        </c:if>
                        <c:if test="${!cliente.activo}">
                            <input type="radio" id="activo" name="activo" value="${cliente.activo}" readonly> Si<br>
                            <input type="radio" id="activo" name="activo" value="${cliente.activo}" checked readonly> No<br>
                        </c:if>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-4 col-xs-8">
                        <a class="btn btn-primary" href="/CrudValde/home">
                            <span class="glyphicon glyphicon-chevron-left"></span> Volver al listado
                        </a>
                        <a class="btn btn-warning pull-right" href="/CrudValde/editar?id=${cliente.id}">
                            Editar cliente <span class="glyphicon glyphicon-pencil"></span>
                        </a>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
