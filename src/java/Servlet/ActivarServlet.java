/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            
            Connection conn = ValdeUtils.Conexion.getConnection();
            
            Integer clienteId = Integer.parseInt(request.getParameter("id"));
            Boolean activo = Boolean.parseBoolean(request.getParameter("activo"));
            
            Cliente cliente = Cliente.getCliente(clienteId, conn);
            
            if (cliente.getActivo()) {
                cliente.setActivo(false);
            } else {
                cliente.setActivo(true);
            }
            
            cliente.activar(conn);
            
            conn.close();
            
            response.sendRedirect("/CrudValde/home");
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
