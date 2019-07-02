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
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.RolDTO;

/**
 *
 * @author johnny
 */

@Repository
public class RolRepositoryImpl implements RolRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Boolean saveRol(RolDTO rol) {
        
        String query = "INSERT INTO rol"
                + " (nombre, descripcion) "
                + " VALUE(?,?)";
        
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                ps.setString(1, rol.getNombre());  
                ps.setString(2, rol.getDescripcion()); 

                return ps.execute();  

            }  
            });  
    }
    
}
