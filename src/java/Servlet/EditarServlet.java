/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
@WebServlet(name = "EditarServlet", urlPatterns = {"/editar"})
public class EditarServlet extends HttpServlet {

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
            
            Integer clienteId = Integer.parseInt(request.getParameter("id"));
            
            Connection conn = ValdeUtils.Conexion.getConnection();
            
            String sql;
            sql = "SELECT * FROM clientes.clientes WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, clienteId);
            ResultSet rs = pstmt.executeQuery();        

            if (rs.next()) {
                Cliente cliente = new Cliente();
                
                cliente.setNombre(rs.getString("nombre"));
                cliente.setActivo(rs.getBoolean("activo"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setFechaNac(rs.getDate("fecha_nac"));
                cliente.setNacionalidad(new Nacionalidad(rs.getInt("nacionalidad_id"), conn));
                
                request.setAttribute("cliente", cliente);
                
                request.setAttribute("title", "Editar cliente " + cliente.toString());
            }
            
            List<Nacionalidad> nacionalidades = Nacionalidad.all(conn);
            
            request.setAttribute("cliente_id", clienteId);
            request.setAttribute("nacionalidades", nacionalidades);
            
            pstmt.close();
            conn.close();
            
            request.getRequestDispatcher("WEB-INF/jsp/editar.jsp").forward(request, response);
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            response.setContentType("text/html;charset=UTF-8");
            
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            
            Connection conn = ValdeUtils.Conexion.getConnection();
            
            Integer clienteId = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String fechaNacimiento = request.getParameter("fecha_nac");
            Boolean activo = Boolean.parseBoolean(request.getParameter("activo"));
            Integer nacionalidad = Integer.parseInt(request.getParameter("nacionalidad"));
            Date fechaNac = null;
            
            try {
                fechaNac = df.parse(fechaNacimiento);
            } catch (Exception e) {
                Logger.getLogger(NuevoServlet.class.getName()).log(Level.SEVERE, null, e);
            }
            
            Cliente cliente = Cliente.getCliente(clienteId, conn);
            
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setFechaNac(fechaNac);
            cliente.setActivo(activo);
            cliente.setNacionalidad(new Nacionalidad(nacionalidad, conn));
            
            
            cliente.update(conn);
            
            conn.close();
            
            response.sendRedirect("/CrudValde/home");
            
        } catch (NamingException | SQLException ex) {
            Logger.getLogger(HomeServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
