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
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaRespuestaDTO;

/**
 *
 * @author johnny
 */
@Repository
public class ViviendaRepositoryImpl {
    
    
    @Autowired
    JdbcTemplate jdbcTemplate;    
    
    public Boolean saveUsuario(ViviendaDTO v) {
        
        if(!verificarExiste(v)){
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

                ps.execute();  
return true;
            }  
            });  
        }
        
        return false;
    }
    
    
    private boolean verificarExiste(ViviendaDTO u){
        
        String SQL = "select * from vivienda where usuario = '" +  u.getUsuario() + 
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
    
    public List<ViviendaDTO> getViviendas() {
        try{
            String SQL = "select * from vivienda " +
                         "inner join residente re " +
                         "on vivienda.idVivienda = re.idVivienda";           

            List <ViviendaDTO> students = jdbcTemplate.query(SQL, 
               new ResultSetExtractor<List<ViviendaDTO>>(){

               public List<ViviendaDTO> extractData(
                  ResultSet rs) throws SQLException, DataAccessException {

                  List<ViviendaDTO> list = new ArrayList<ViviendaDTO>();  
                  while(rs.next()){ 
                    ViviendaDTO vi = buscarVivienda(list, rs.getInt("idVivienda"));
                    if(vi!=null){
                        ResidenteDTO residente = new ResidenteDTO();
                        residente.setIdResidente(rs.getInt("idResidente"));
                        residente.setNombres(rs.getString("nombres"));
                        residente.setApeMaterno(rs.getString("apeMaterno"));
                        residente.setApePaterno(rs.getString("apePaterno"));
                        residente.setFecNacimiento(rs.getDate("fecNacimiento"));
                        residente.setGenero(rs.getString("genero"));
                        residente.setEmail(rs.getString("email"));
                        residente.setActivo(rs.getBoolean("activo"));
                        vi.getResidentes().add(residente);

                    }else{                        
                        ViviendaDTO student = new ViviendaDTO();
                        student.setActivo(rs.getBoolean("activo"));
                        student.setIdVivienda(rs.getInt("idVivienda"));
                        student.setDireccion(rs.getString("direccion"));
                        student.setNombreContacto(rs.getString("nombreContacto"));
                        student.setTelefonoContacto(rs.getString("telefonoContacto"));
                        student.setIdRol(rs.getInt("idRol"));
                        student.setEmail(rs.getString("email"));
                        student.setUsuario(rs.getString("usuario"));
                        student.setPassword(rs.getString("password"));

                        
                        
                        ResidenteDTO residente = new ResidenteDTO();
                        residente.setIdResidente(rs.getInt("idResidente"));
                        residente.setNombres(rs.getString("nombres"));
                        residente.setApeMaterno(rs.getString("apeMaterno"));
                        residente.setApePaterno(rs.getString("apePaterno"));
                        residente.setFecNacimiento(rs.getDate("fecNacimiento"));
                        residente.setGenero(rs.getString("genero"));
                        residente.setEmail(rs.getString("email"));
                        residente.setActivo(rs.getBoolean("activo"));

                        student.getResidentes().add(residente);
                        list.add(student); 
                    }

                  }  
                  return list;  
               }    	  
            });
            return students;
        }catch(Exception ex){
            return null;
        }
        
    }
    
    private ViviendaDTO buscarVivienda(List<ViviendaDTO> list, int id){
        for(ViviendaDTO v: list){
            if(v.getIdVivienda() == id)
                return v;            
        }
        return null;
    }
    
    
    public boolean crearCensoVivienda(ViviendaRespuestaDTO viviendaRespuestaDTO){       
        
        List<ViviendaDTO> viviendasActuales = getViviendas();
        
        for(ViviendaDTO vivi: viviendasActuales){
            
            String query = "INSERT INTO censoVivienda"
                + " (idCenso, idVivienda, estado) "
                + " VALUE(?,?,?)";
        
         
            jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException{  
                    ps.setInt(1, viviendaRespuestaDTO.getIdCenso());  
                    ps.setInt(2, vivi.getIdVivienda());  
                    ps.setString(3, viviendaRespuestaDTO.getEstado());                    

                    return ps.execute();  

                }  
                });  
            
        }
        
        return true;
    }
    
     public boolean updateViviendaRespuesta(List<ViviendaRespuestaDTO> viviendaRespuestaDTO){       
        
        
        for(ViviendaRespuestaDTO vivi: viviendaRespuestaDTO){
            
            String query = "UPDATE censoVivienda " +
                            "SET respuesta = ? , estado= ? " +
                            "WHERE idCenso = ? and idVivienda = ?";
        
         
            jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
                @Override  
                public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException{  
                    ps.setString(1, vivi.getRespuesta());  
                    ps.setString(2, vivi.getRespuesta());  
                    ps.setInt(3, vivi.getIdCenso());                    
                    ps.setInt(4, vivi.getIdVivienda());

                    return ps.execute();  

                }  
                });  
            
        }
        
        return true;
    }

    public boolean actualizarUsuario(ViviendaDTO usuarioDto) {
        
        
        
        String query = "update vivienda"
        + " set direccion = ?, nombreContacto = ?, telefonoContacto = ?, activo = ?, idRol = ?, email = ?, password = ?, fecRegistro = Curdate() "
        + " where usuario = ?";
        
            
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                ps.setString(1, usuarioDto.getDireccion());  
                ps.setString(2, usuarioDto.getNombreContacto());  
                ps.setString(3, usuarioDto.getTelefonoContacto());
                ps.setBoolean(4, usuarioDto.isActivo()); 
                ps.setInt(5, usuarioDto.getIdRol()); 
                ps.setString(6, usuarioDto.getEmail());
                ps.setString(7, usuarioDto.getPassword());
ps.setString(8, usuarioDto.getUsuario());
                return ps.execute();  

            }  
            });  
        }
        
       
    
    
    
    }
