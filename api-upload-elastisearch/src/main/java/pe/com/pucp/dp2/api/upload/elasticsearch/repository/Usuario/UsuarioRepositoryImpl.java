/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository.Usuario;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.LoginDTO;
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
        if(!verificarExiste(u)){
            String query = "INSERT INTO usuario"
                + " (nombres, apePaterno, apeMaterno, fecNacimiento, email, activo, idRol, usuario, password, fecRegistro) "
                + " VALUE(?,?,?,?,?,?,?,?,?,Curdate())";
        
        
            return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps)   
                         {  
                    try {
                        ps.setString(1, u.getNombres());
                        ps.setString(2, u.getApeMaterno());
                        ps.setString(3, u.getApeMaterno());
                        ps.setDate(4, u.getFecNacimiento());
                        ps.setString(5, u.getEmail());
                        ps.setBoolean(6, u.isActivo());
                        ps.setInt(7, u.getIdRol());
                        ps.setString(8, u.getUsuario());
                        ps.setString(9, u.getPassword());

                        ps.execute();
                        return true;
                    } catch (SQLException ex) {

                        return false;                
                    }
                }  
                });  
        }
        return false;
        
    }
    
    private boolean verificarExiste(UsuarioDTO u){
        
        
        String SQL = "select * from usuario where usuario = '" +  u.getUsuario() + 
                         "' ";
            
            
            
            boolean students = jdbcTemplate.query(SQL, 
               new ResultSetExtractor<Boolean>(){

               public Boolean extractData(
                  ResultSet rs) throws SQLException, DataAccessException {

                  
                  if(rs.next()){  
                                    
                     
                     return true; 
                  }  
                  return false;
               }    	  
            });
            
            return students;
        
    }

    @Override
    public LoginDTO login(UsuarioDTO u) {
        
        System.out.println("llega a login");
       
            String SQL = "select * from usuario where usuario = '" +  u.getUsuario() + 
                         "' and password = '" + u.getPassword() + "'";
            
            
            
            LoginDTO students = jdbcTemplate.query(SQL, 
               new ResultSetExtractor<LoginDTO>(){

               public LoginDTO extractData(
                  ResultSet rs) throws SQLException, DataAccessException {

                  LoginDTO student = new LoginDTO();
                  if(rs.next()){  
                     
                     student.setId(rs.getInt("idUsuario"));
                     if(rs.getInt("idUsuario") == 1){
                        student.setIdRol(rs.getInt("idRol"));
                        student.setPassword(rs.getString("password"));
                        student.setUsuario(rs.getString("usuario"));
                     }else{
                        student.setIdRol(rs.getInt("idRol")); 
                        student.setEmail(rs.getString("email")); 
                        student.setNombre(rs.getString("nombres").concat(" ").concat(rs.getString("apePaterno")).concat(" ").concat(rs.getString("apeMaterno")));
                        student.setPassword(rs.getString("password"));
                        student.setUsuario(rs.getString("usuario"));
                     }
                     
                     return student; 
                  }  
                  return null;
               }    	  
            });
            
            if(students != null){
                
                students.setCode(200);
                students.setMsg("Éxito");
                return students;
            }else{
                String SQL2 = "select * from vivienda where usuario = '" +  u.getUsuario() + 
                         "' and password = '" + u.getPassword() + "'";
                        
           
                students = jdbcTemplate.query(SQL2, 
                new ResultSetExtractor<LoginDTO>(){

                public LoginDTO extractData(
                    ResultSet rs) throws SQLException, DataAccessException {

                        LoginDTO student = new LoginDTO(); 
                        if(rs.next()){  
                            
                           
                           student.setId(rs.getInt("idVivienda")); 
                           student.setIdRol(rs.getInt("idRol")); 
                           student.setEmail(rs.getString("email")); 
                           student.setNombre(rs.getString("nombreContacto"));
                           student.setPassword(rs.getString("password"));
                           student.setUsuario(rs.getString("usuario"));
                           return student; 
                        }  
                        return null;
                    }    	  
                });
                
                if(students!=null){
                    students.setCode(200);
                    students.setMsg("Éxito");
                    return students;
                }else{
                    System.out.println("no encontro nda");
                    LoginDTO loginDTO = new LoginDTO();
                    loginDTO.setCode(303);
                    loginDTO.setMsg("No se encuentra registrado usuario");
                    return loginDTO;
                }
                    
            }            
            
        
        
    }

    @Override
    public List<UsuarioDTO> getUsuarios() {
        String SQL = "select * from usuario";           
            
        List <UsuarioDTO> students = jdbcTemplate.query(SQL, 
           new ResultSetExtractor<List<UsuarioDTO>>(){

           public List<UsuarioDTO> extractData(
              ResultSet rs) throws SQLException, DataAccessException {

              List<UsuarioDTO> list = new ArrayList<UsuarioDTO>();  
              while(rs.next()){  
                 UsuarioDTO student = new UsuarioDTO();
                student.setActivo(rs.getBoolean("activo"));
                student.setApeMaterno(rs.getString("apePaterno")); 
                student.setApePaterno(rs.getString("apeMaterno"));
                student.setEmail(rs.getString("email"));
                student.setFecNacimiento(rs.getDate("fecNacimiento"));
                student.setIdRol(rs.getInt("idRol"));
                student.setNombres(rs.getString("nombres"));
                student.setPassword(rs.getString("password"));
                student.setUsuario(rs.getString("usuario"));
                student.setUsuarioId(rs.getInt("idUsuario"));
                 list.add(student);  

                 
              }  
              return list;  
           }    	  
        });
        return students;
    }
    
}
