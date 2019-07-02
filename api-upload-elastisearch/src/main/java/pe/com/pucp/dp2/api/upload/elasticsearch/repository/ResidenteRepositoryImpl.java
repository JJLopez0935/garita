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
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ResidenteDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaDTO;

/**
 *
 * @author johnny
 */
@Repository
public class ResidenteRepositoryImpl {
 
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public Boolean saveUsuario(ResidenteDTO r) {
        
        
        String query = "INSERT INTO residente"
                + " (idVivienda, nombres, apeMaterno, apePaterno, fecNacimiento, genero, email, activo, fecRegistro) "
                + " VALUE(?,?,?,?,?,?,?,?,Curdate())";
        
        System.out.println("llega antes de insertar");
        
        
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                         System.out.println("entra aqui");
                ps.setInt(1, r.getIdVivienda());  
                ps.setString(2, r.getNombres());  
                ps.setString(3, r.getApeMaterno());
                ps.setString(4, r.getApePaterno()); 
                ps.setDate(5, r.getFecNacimiento()); 
                ps.setString(6, r.getGenero());
                ps.setString(7, r.getEmail());
                ps.setBoolean(8, r.isActivo());

                return ps.execute();  

            }  
            });  
    }
    
}
