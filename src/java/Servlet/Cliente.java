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
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author fer
 */
public class Cliente {
    
    private Integer id;
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private Nacionalidad nacionalidad;
    private Boolean activo;
    
    public static Cliente getCliente(Integer clienteId, Connection conn) {
        
        Cliente cliente = new Cliente();
        
        try {
            
            String sql = "SELECT * "
                    + "FROM clientes "
                    + "WHERE id = ?";
            
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setInt(1, clienteId);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                
                cliente.setId(clienteId);
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setFechaNac(rs.getDate("fecha_nac"));
                cliente.setNacionalidad(new Nacionalidad(rs.getInt("nacionalidad_id"), conn));
                cliente.setActivo(rs.getBoolean("activo"));
            }
            
        } catch (SQLException e) {
            
        }
        
        return cliente;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.getApellido() + ", " + this.getNombre();
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }
    
    public void update(Connection conn){
        
        String sql = "UPDATE clientes "
                + "SET nombre = ?,"
                + "apellido = ?,"
                + "fecha_nac = ?,"
                + "activo = ?,"
                + "nacionalidad_id = ? "
                + "WHERE id = ?";

        try {
            
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, this.nombre);
            pstmt.setString(2, this.apellido);
            pstmt.setDate(3, new java.sql.Date(this.fechaNac.getTime()));
            pstmt.setBoolean(4, this.activo);
            pstmt.setInt(5, this.nacionalidad.getId());
            pstmt.setInt(6, this.id);
            
            pstmt.executeUpdate();
            
            pstmt.close();
        } catch (SQLException e) {
        }
        
    }
    
    public void delete(Connection conn) throws ValdeException{
        
        String sql =
                "   DELETE FROM"
                + "     clientes"
                + " WHERE"
                + "     id = ?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, this.id);

            pstmt.executeUpdate();

            pstmt.close();
            
        } catch (SQLException e) {
            throw new ValdeException("uuuh");
        }
        
        

        
    }
}
