/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ValdeUtils.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author fer
 */
@WebServlet(name = "BajaServlet", urlPatterns = {"/baja"})
public class BajaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                    
        HttpSession sesion = request.getSession();
            
        if(Conexion.estaLogueado(sesion)){
            
            try {

                Connection conn = ValdeUtils.Conexion.getConnection();

                Integer id = Integer.valueOf(request.getParameter("id"));

                Cliente cliente = Cliente.getCliente(id, conn);

                String nombre = cliente.getNombre() + " " + cliente.getApellido();
                
                cliente.delete(conn);

                conn.close();

                sesion.setAttribute("info", "El cliente " + nombre + " fué dado de baja éxitosamente");
                
                response.sendRedirect("/CrudValde/home");

            } catch (Exception ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            
            Conexion.irAlLogin(response);
            
        }
    }
}
