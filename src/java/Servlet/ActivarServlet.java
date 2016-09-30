/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ValdeUtils.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
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
@WebServlet(name = "ActivarServlet", urlPatterns = {"/activar"})
public class ActivarServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sesion = request.getSession();
            
        if(Conexion.estaLogueado(sesion)){
        
            try {

                response.setContentType("text/html;charset=UTF-8");

                Connection conn = ValdeUtils.Conexion.getConnection();

                Integer clienteId = Integer.parseInt(request.getParameter("id"));
                Boolean activo = Boolean.parseBoolean(request.getParameter("activo"));

                Cliente cliente = Cliente.getCliente(clienteId, conn);

                // si está activo lo desactivo y viceversa
                if (cliente.getActivo()) {
                    cliente.setActivo(false);
                } else {
                    cliente.setActivo(true);
                }
            
                // plasmamos el cambio anterior en la BD
                cliente.activar(conn);

                conn.close();

                sesion.setAttribute("info", "El cliente " + cliente.toString() + " cambió su estado.");
                
                response.sendRedirect("/CrudValde/home");

            } catch (NamingException | SQLException ex) {
                Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            Conexion.irAlLogin(response);
        }
    }
}
