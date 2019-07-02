/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaDTO;

/**
 *
 * @author johnny
 */
@Repository
public class ViviendaRepositoryImpl {
    
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public Boolean saveUsuario(ViviendaDTO v) {
        
        
        String query = "INSERT INTO vivienda"
                + " (direccion, nombreContacto, telefonoContacto, activo, idRol, email, usuario, password, fecRegistro) "
                + " VALUE(?,?,?,?,?,?,?,?,Curdate())";
        
        
        
        
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                ps.setString(1, v.getDireccion());  
                ps.setString(2, v.getNombreContacto());  
                ps.setString(3, v.getTelefonoContacto());
                ps.setBoolean(4, v.isActivo()); 
                ps.setInt(5, v.getIdRol()); 
                ps.setString(6, v.getEmail());
                ps.setString(7, v.getUsuario());
                ps.setString(8, v.getPassword());

                return ps.execute();  

            }  
            });  
    }
    
}
