/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ValdeUtils.Conexion;
import java.io.IOException;
import java.sql.Connection;
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
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

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
        
        try {
            response.setContentType("text/html;charset=UTF-8");
        
            HttpSession sesion = request.getSession();
            String usu, pass;
            usu = request.getParameter("nombre_usuario");
            pass = request.getParameter("password");

            Connection conn = Conexion.getConnection();

            Usuario usuario = Usuario.getUsuario(usu, pass, conn);
            
            conn.close();

            if(usuario != null && sesion.getAttribute("usuario") == null){
                //si coincide usuario y password y además no hay sesión iniciada
                sesion.setAttribute("usuario", usu);
                //redirijo a página con información de login exitoso
                response.sendRedirect("/CrudValde/home");
            }else{
                
                sesion.setAttribute("error", "Usuario y/o contraseña incorrectos.");
                
                response.sendRedirect("/CrudValde/login");
            }
        } catch (Exception e) {
        
        }
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            
            request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
          
        } catch (Exception e) {
        
        }
    }
}
