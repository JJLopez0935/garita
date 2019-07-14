/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.RolDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;

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
    
    public List<RolDTO> getUsuarios() {
        String SQL = "select * from rol";           
            
        List <RolDTO> students = jdbcTemplate.query(SQL, 
           new ResultSetExtractor<List<RolDTO>>(){

           public List<RolDTO> extractData(
              ResultSet rs) throws SQLException, DataAccessException {

              List<RolDTO> list = new ArrayList<RolDTO>();  
              while(rs.next()){  
                 RolDTO student = new RolDTO();
                student.setIdRol(rs.getInt("idRol"));
                student.setDescripcion(rs.getString("descripcion"));
                student.setNombre(rs.getString("nombre"));
                 list.add(student);  

                 
              }  
              return list;  
           }    	  
        });
        return students;
    }
    
    public Boolean actualizarRol(RolDTO rol) {
        

        
        String query = "update rol"
                + " set nombre = ?, descripcion = ? "
                + " where idRol = ?";
        
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                ps.setString(1, rol.getNombre());  
                ps.setString(2, rol.getDescripcion()); 
                ps.setInt(3, rol.getIdRol());

                return ps.execute();  

            }  
            });  
    }
    
}
