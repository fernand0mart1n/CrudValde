<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="layout.jsp"></jsp:include>
        <div class="col-xs-offset-3 col-xs-6">
            <legend>Nuevo cliente</legend>
            <form class="form form-horizontal" method="POST" action="/CrudValde/nuevo">
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="nombre"><b>Nombre:</b></label>
                    <div class="col-xs-8">
                        <input type="text" id="nombre" name="nombre" class="form-control" placeholder="Nombre" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="apellido"><b>Apellido:</b></label>
                    <div class="col-xs-8">
                        <input type="text" id="apellido" name="apellido" class="form-control" placeholder="Apellido" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="fecha_nac"><b>Fecha de nacimiento:</b></label>
                    <div class="col-xs-8">
                        <input type="date" id="fecha_nac" name="fecha_nac" class="form-control datepicker" required autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label for="nacionalidad" class="col-xs-4 control-label">Nacionalidad:</label>
                    <div class="col-xs-8">
                        <select class="form-control" id="nacionalidad" name="nacionalidad" required>
                            <c:forEach var="pais" items="${nacionalidad}">
                                <option value="${pais.id}">${pais.nacionalidad}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-xs-4 control-label" for="activo"><b>Activo:</b></label>
                    <div class="col-xs-8">
                        <input type="radio" id="activo" name="activo" value="1" checked> Si<br>
                        <input type="radio" id="activo" name="activo" value="0"> No<br>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-offset-4 col-xs-8">
                        <a class="btn btn-primary" href="/CrudValde/home">
                            <span class="glyphicon glyphicon-chevron-left"></span> Volver al listado
                        </a>
                        <button type="submit" class="btn btn-success pull-right">
                            Guardar <span class="glyphicon glyphicon-save-file"></span>
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
