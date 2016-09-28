/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
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
@WebServlet(name = "HomeServlet", urlPatterns = {"/home"})
public class HomeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        
            response.setContentType("text/html;charset=UTF-8");
            
            Connection conn = ValdeUtils.Conexion.getConnection();
            
            String sql = "SELECT * FROM clientes.clientes";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List <HashMap<String, Object>> resultado = new LinkedList();
            
                while(rs.next()){
                    HashMap row = new HashMap();
                    row.put("id", rs.getInt("id"));
                    row.put("nombre", rs.getString("nombre"));
                    row.put("apellido", rs.getString("apellido"));
                    row.put("fecha_nac", rs.getDate("fecha_nac"));
                    row.put("nacionalidad", new Nacionalidad(rs.getInt("nacionalidad_id"), conn));
                    row.put("activo", rs.getInt("activo"));
                    resultado.add(row);
                }
            
            request.setAttribute("resultado", resultado);
            
            pstmt.close();
            conn.close();
            
            String title = null;

            request.setAttribute("title", "Listado de clientes");

            request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
