/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author fer
 */
public class Nacionalidad {
    
    private Integer id;
    private String nacionalidad;

    // método que trae todas las nacionalidades para listar en los selects
    public static List<Nacionalidad> all(Connection conn) {
        
        List<Nacionalidad> ns = new LinkedList<>();
        
        String sql = 
                "   SELECT"
                + "     *"
                + " FROM"
                + "     nacionalidades";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                
                Nacionalidad n = new Nacionalidad();
                
                n.setId(rs.getInt("id"));
                n.setNacionalidad(rs.getString("nacionalidad"));
                
                ns.add(n);
            }
            
            
        } catch (Exception e) {
            
        }
        
        return ns;
    }
    
    public Nacionalidad(Integer id, Connection conn) {
        
        String sql = 
                "   SELECT"
                + "     *"
                + " FROM"
                + "     nacionalidades"
                + " WHERE"
                + "     id = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {                
                
                Nacionalidad n = new Nacionalidad();
                
                this.id = rs.getInt("id");
                this.nacionalidad = rs.getString("nacionalidad");
            }
            
            
        } catch (Exception e) {
            
        }
        
    }
    
    public Nacionalidad() {
        this.nacionalidad = "Argentina";
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
}
