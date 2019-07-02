/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaDTO;

/**
 *
 * @author johnny
 */
@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public Boolean saveUsuario(UsuarioDTO u) {
        
        System.out.println(u.getNombres());
        
        String query = "INSERT INTO usuario"
                + " (nombres, apePaterno, apeMaterno, fecNacimiento, email, activo, idRol, usuario, password, fecRegistro) "
                + " VALUE(?,?,?,?,?,?,?,?,?,Curdate())";
        
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                ps.setString(1, u.getNombres());  
                ps.setString(2, u.getApeMaterno());  
                ps.setString(3, u.getApeMaterno());
                ps.setDate(4, u.getFecNacimiento()); 
                ps.setString(5, u.getEmail()); 
                ps.setBoolean(6, u.isActivo());
                ps.setInt(7, u.getIdRol());
                ps.setString(8, u.getUsuario());
                ps.setString(9, u.getPassword());

                return ps.execute();  

            }  
            });  
        
//        jdbcTemplate.execute(query);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean login(UsuarioDTO u) {
       
            String SQL = "select * from usuario where usuario = '" +  u.getUsuario() + 
                         "' and password = '" + u.getPassword() + "'";
            
            
            
            List <UsuarioDTO> students = jdbcTemplate.query(SQL, 
               new ResultSetExtractor<List<UsuarioDTO>>(){

               public List<UsuarioDTO> extractData(
                  ResultSet rs) throws SQLException, DataAccessException {

                  List<UsuarioDTO> list = new ArrayList<UsuarioDTO>();  
                  while(rs.next()){  
                     UsuarioDTO student = new UsuarioDTO();
                     
                     list.add(student);  
                  }  
                  return list;  
               }    	  
            });
            
            if(students.size()>0){
                return true;
            }else{
                String SQL2 = "select * from vivienda where usuario = '" +  u.getUsuario() + 
                         "' and password = '" + u.getPassword() + "'";
                        
            
                List <ViviendaDTO> students2 = jdbcTemplate.query(SQL2, 
                   new ResultSetExtractor<List<ViviendaDTO>>(){

                   public List<ViviendaDTO> extractData(
                      ResultSet rs) throws SQLException, DataAccessException {

                      List<ViviendaDTO> list2 = new ArrayList<ViviendaDTO>();  
                      while(rs.next()){  
                         ViviendaDTO student = new ViviendaDTO();

                         list2.add(student);  
                      }  
                      return list2;  
                   }    	  
                });
                
                if(students2.size()>0)
                    return true;
                else
                    return false;
            }
            
            
       
        
        
    }
    
}
