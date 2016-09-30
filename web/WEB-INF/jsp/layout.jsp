<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>${title}</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="/CrudValde/assets/bower_components/bootstrap/dist/css/bootstrap.min.css">
        <link rel="stylesheet" href="/CrudValde/assets/bower_components/jquery-ui/themes/smoothness/jquery-ui.min.css">
        <link rel="stylesheet" href="/CrudValde/assets/css/crud.css">
        <script src="/CrudValde/assets/bower_components/jquery/dist/jquery.min.js"></script>
        <script src="/CrudValde/assets/bower_components/jquery-ui/jquery-ui.min.js"></script>
        <script src="/CrudValde/assets/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
        <script src="/CrudValde/assets/js/crud.js"></script>
    </head
    <body> 
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
              </button>
              <a class="navbar-brand" href="">Gestor de clientes</a>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
              <ul class="nav navbar-nav navbar-right">
                  <c:if test="${sessionScope.usuario != null}">
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><span class="glyphicon glyphicon-user"></span> ${sessionScope.usuario} <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <li><a href="/CrudValde/logout">Cerrar sesión</a></li>
                      </ul>
                    </li>
                  </c:if>
                  <c:if test="${sessionScope.usuario == null}">
                    <li class="dropdown">
                      <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"> Ingresar <span class="caret"></span></a>
                      <ul class="dropdown-menu">
                        <div class="container-fluid">
                            <form class="form" method="POST" action="/CrudValde/login">
                                <div class="form-group">
                                    <label for="nombre">Usuario:</label>
                                    <input type="text" name="nombre_usuario" id="nombre_usuario" class="form-control" placeholder="Usuario" required autofocus>
                                </div>
                                <div class="form-group">
                                    <label for="password">Contraseña:</label>
                                    <input type="password" name="password" id="password"  class="form-control" placeholder="Contraseña" required>
                                </div>
                                <input type="hidden" name="accion" id="accion" value="login">
                                <button type="submit" class="center-block btn btn-success">Ingresar <span class="glyphicon glyphicon-log-in"></span></button>
                            </form>
                        </div>
                      </ul>
                    </li>
                  </c:if>
              </ul>
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav>