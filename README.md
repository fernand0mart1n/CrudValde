# CrudValdeJava

Requerimientos
- Bower

Pasos para el "deploy"

- Clonar el repo
- Crear la base de datos "clientes" en MySQL
- Editar el archivo context.xml, usando los datos del que está en este repo (/config/context.xml)
- Editar los datos de la etiqueta GlobalNamingResources el archivo server.xml, usando los datos del que está en este repo (/config/server.xml)
- Importar el SQL ubicado en /sql/clientes.sql
- Ubicarse en la carpeta assets y hacer "bower install"
- Correr preferentemente sobre tomcat e ingresar a /CrudValde
- ¡Voilá!

Una vez corriendo la aplicación, usar los siguientes usuarios y contraseñas:

- usuario:usuario
- administrador:administrador
