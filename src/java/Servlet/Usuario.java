/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fer
 */
public class Usuario {
    private Integer id;
    private String nombre_usuario;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombre_usuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombre_usuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public static Usuario getUsuario(String nombreUsuario, String pass, Connection conn) {
        
        Usuario usuario = null;
        
        try {
            
            String sql =
                    "   SELECT"
                    + "     * "
                    + " FROM"
                    + "     usuarios "
                    + " WHERE"
                    + "     nombre_usuario = ?"
                    + "     AND password = password(?)";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, nombreUsuario);
            ps.setString(2, pass);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                usuario = new Usuario();
                
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombre_usuario"));
                usuario.setPassword(rs.getString("password"));

            }
            
        } catch (SQLException e) {
            
        }
        
        return usuario;
    }
    
}
