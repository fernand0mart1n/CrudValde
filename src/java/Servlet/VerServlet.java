/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ValdeUtils.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
 * @author universidad
 */
@WebServlet(name = "VerServlet", urlPatterns = {"/ver"})
public class VerServlet extends HttpServlet {

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
            
            Integer id = Integer.valueOf(request.getParameter("id"));
            
            Connection conn = Conexion.getConnection();
            
            String sql;
            sql = "SELECT * FROM clientes.clientes WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();        

            if (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setId(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setActivo(rs.getBoolean("activo"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setFechaNac(rs.getDate("fecha_nac"));
                cliente.setNacionalidad(new Nacionalidad(rs.getInt("nacionalidad_id"), conn));
                
                request.setAttribute("cliente", cliente);
                
                request.setAttribute("title", "Ver cliente " + cliente.toString());
            }
            
            pstmt.close();
            conn.close();

            request.getRequestDispatcher("WEB-INF/jsp/ver.jsp").forward(request, response);
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
