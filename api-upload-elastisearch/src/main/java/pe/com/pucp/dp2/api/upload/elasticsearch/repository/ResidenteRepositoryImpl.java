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
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ResidenteDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.RolDTO;
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
    
    public List<ResidenteDTO> getUsuarios() {
        String SQL = "select * from residente";           
            
        List <ResidenteDTO> students = jdbcTemplate.query(SQL, 
           new ResultSetExtractor<List<ResidenteDTO>>(){

           public List<ResidenteDTO> extractData(
              ResultSet rs) throws SQLException, DataAccessException {

              List<ResidenteDTO> list = new ArrayList<ResidenteDTO>();  
              while(rs.next()){  
                 ResidenteDTO student = new ResidenteDTO();
                student.setIdResidente(rs.getInt("idResidente"));
                student.setNombres(rs.getString("nombres"));
                student.setApePaterno(rs.getString("apePaterno"));
                student.setApeMaterno(rs.getString("apeMaterno"));
                student.setEmail(rs.getString("email"));
                student.setFecNacimiento(rs.getDate("fecNacimiento"));
                student.setActivo(rs.getBoolean("activo"));
                student.setGenero(rs.getString("genero"));
                
                 list.add(student);  

             
              }  
              return list;  
           }    	  
        });
        return students;
    }
    
    public ResidenteDTO getUsuariosId(String id) {
        String SQL = "select * from residente where idResidente="+id;           
            
        ResidenteDTO students = jdbcTemplate.query(SQL, 
           new ResultSetExtractor<ResidenteDTO>(){

           public ResidenteDTO extractData(
              ResultSet rs) throws SQLException, DataAccessException {

              ResidenteDTO list = new ResidenteDTO();  
              while(rs.next()){  
                 ResidenteDTO student = new ResidenteDTO();
                student.setIdResidente(rs.getInt("idResidente"));
                student.setNombres(rs.getString("nombres"));
                student.setApePaterno(rs.getString("apePaterno"));
                student.setApeMaterno(rs.getString("apeMaterno"));
                student.setEmail(rs.getString("email"));
                student.setFecNacimiento(rs.getDate("fecNacimiento"));
                student.setActivo(rs.getBoolean("activo"));
                student.setGenero(rs.getString("genero"));
                
                 

             
              }  
              return list;  
           }    	  
        });
        return students;
    }
    
    
}
