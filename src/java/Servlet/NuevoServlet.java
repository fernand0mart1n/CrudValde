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
import java.text.SimpleDateFormat;
import java.util.Date;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author universidad
 */
@WebServlet(name = "NuevoServlet", urlPatterns = {"/nuevo"})
public class NuevoServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            
            Connection conn = ValdeUtils.Conexion.getConnection();
            
            String sql = "SELECT * FROM clientes.nacionalidades";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            List <HashMap<String, Object>> nacionalidad = new LinkedList();
            
            while(rs.next()){
                HashMap row = new HashMap();
                row.put("id", rs.getInt("id"));
                row.put("nacionalidad", rs.getString("nacionalidad"));
                nacionalidad.add(row);
            }
            
            request.setAttribute("nacionalidad", nacionalidad);
            
            request.setAttribute("title", "Nuevo cliente");
         
            request.getRequestDispatcher("WEB-INF/jsp/nuevo.jsp").forward(request, response);
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String fecha_nacimiento = request.getParameter("fecha_nac");
            Integer nacionalidad = Integer.valueOf(request.getParameter("nacionalidad"));
            
            Boolean activo = false;
                    
            if ("1".equals(request.getParameter("activo"))) {
                activo = true;
            }
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            Date fecha_nac = null;
            
            try {
                fecha_nac = df.parse(fecha_nacimiento);
            } catch (Exception e) {
                Logger.getLogger(NuevoServlet.class.getName()).log(Level.SEVERE, null, e);
            }
            
            Connection conn = ValdeUtils.Conexion.getConnection();
            
            String sql = "INSERT INTO clientes.clientes "
                    + "(nombre,"
                    + "apellido,"
                    + "fecha_nac,"
                    + "nacionalidad_id,"
                    + "activo) "
                    + "VALUES(?,"
                    + "?,"
                    + "?,"
                    + "?,"
                    + "?)";
            
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, nombre);
            pstmt.setString(2, apellido);
            pstmt.setDate(3, new java.sql.Date(fecha_nac.getTime()));
            pstmt.setInt(4, nacionalidad);
            pstmt.setBoolean(5, activo);
            
            pstmt.execute();
            
            pstmt.close();
            conn.close();
            
            response.sendRedirect("/CrudValde/home");
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(NuevoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
