/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ValdeUtils;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.LinkedList;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 *
 * @author universidad
 */
public class Conexion {
    
    // encapsulamiento de la conexión a la BD
    public static Connection getConnection() throws NamingException, SQLException{
     
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:comp/env");
        DataSource ds = (DataSource) envContext.lookup("jdbc/clientes");
        Connection conn = ds.getConnection();
        return conn;
        
    }
    
    // verificamos si está logueado indagando en la variable de sesión
    public static Boolean estaLogueado(HttpSession sesion) throws IOException {
        if(sesion.getAttribute("usuario") == null){
                
            return false;
            
        } else {
            return true;
        }
    }
    
    // comprobamos permisos
    public static Boolean tienePermiso(HttpSession sesion, String permiso) throws IOException {
        return ((LinkedList)sesion.getAttribute("roles")).contains(permiso);
    }
    
    
    public static void irAlLogin(HttpServletResponse response) throws IOException {
        
        // patea al login si no está logueado
        response.sendRedirect("/CrudValde/login");
        
    }
    
    public static Integer calcularEdad(Date fecha){
        
        Calendar fechaNacimiento = Calendar.getInstance();
        //Se crea un objeto con la fecha actual
        Calendar fechaActual = Calendar.getInstance();
        //Se asigna la fecha recibida a la fecha de nacimiento.
        fechaNacimiento.setTime(fecha);
        //Se restan la fecha actual y la fecha de nacimiento
        int año = fechaActual.get(Calendar.YEAR)- fechaNacimiento.get(Calendar.YEAR);
        int mes =fechaActual.get(Calendar.MONTH)- fechaNacimiento.get(Calendar.MONTH);
        int dia = fechaActual.get(Calendar.DATE)- fechaNacimiento.get(Calendar.DATE);
        //Se ajusta el año dependiendo el mes y el día
        if(mes<0 || (mes==0 && dia<0)){
            año--;
        }
        //Regresa la edad en base a la fecha de nacimiento
        return año;
    }
}
