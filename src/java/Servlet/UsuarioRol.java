/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ValdeUtils.ValdeException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public static List <String> getRoles(Integer usuarioId, Connection conn) throws ValdeException {
        
        List <String> roles = new LinkedList();
        
        try {
            
            String sql =
                    "   SELECT"
                    + "     r.nombre AS rol"
                    + " FROM"
                    + "     usuario_rol ur,"
                    + "     roles r"
                    + " WHERE"
                    + "     ur.usuario = ?"
                    + "     AND r.id = ur.rol";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, usuarioId);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
                String rol = rs.getString("rol");
                
                roles.add(rol);
            }
            
        } catch (Exception e) {
            throw new ValdeException(e.getMessage());
        }
        
        return roles;
    }
}
