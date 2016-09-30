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
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author fer
 */
public class UsuarioRol {
    private Integer usuario;
    private Integer rol;

    public Integer getId() {
        return usuario;
    }

    public void setId(Integer id) {
        this.usuario = id;
    }

    public Integer getRol() {
        return rol;
    }

    public void setRol(Integer rol) {
        this.rol = rol;
    }
    
    // método que me trae los permisos de los usuarios
    public static List <Integer> getRoles(Integer usuarioId, Connection conn) {
                
        List <Integer> resultado = new LinkedList();
        Integer roles = null;
        
        try {
            
            String sql =
                    "   SELECT"
                    + "     rol "
                    + " FROM"
                    + "     usuario_rol "
                    + " WHERE"
                    + "     usuario = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, usuarioId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                roles = rs.getInt("rol");
                
                resultado.add(roles);
            }
            
        } catch (SQLException e) {
            
        }
        
        return resultado;
    }
}
