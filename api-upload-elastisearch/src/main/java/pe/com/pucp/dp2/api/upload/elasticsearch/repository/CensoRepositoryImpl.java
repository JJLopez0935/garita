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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.CensoDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.FormularioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;

/**
 *
 * @author johnny
 */
@Repository
@Transactional
public class CensoRepositoryImpl {
    
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    FormularioRepositoryImpl formularioRepositoryImpl;
    
    public Boolean saveUsuario(CensoDTO c) {
        
        try {
            String query = "INSERT INTO censo"
                    + " (fechaRegistro, fechaInicio, periodo, estado, viviendas, vivEncuestadas) "
                    + " VALUE(?,?,?,?,?,?)";
            
            jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
                @Override
                public Boolean doInPreparedStatement(PreparedStatement ps){
                    try {
                        ps.setDate(1, c.getFechaRegistro());
                        ps.setDate(2, c.getFechaInicio());
                        ps.setInt(3, c.getPeriodo());
                        ps.setString(4, c.getEstado());
                        ps.setInt(5, c.getViviendas());
                        ps.setInt(6, c.getVivEncuestadas());
                        
                        ps.executeUpdate();
                        
                        return true;
                    } catch (SQLException ex) {
                        Logger.getLogger(CensoRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                        return false;
                    }
                    
                }
            });
            
            
            int censo = obtenerIdFormulario();
            
            for(Integer f: c.getFormulariosId()){
                
                
                String queryInsertOpcion = "INSERT INTO formulariosCenso"
                        + " (idCenso, idFormulario, activo)"
                        + " VALUE(?,?,?)";
                
                
                jdbcTemplate.execute(queryInsertOpcion,new PreparedStatementCallback<Boolean>(){
                    @Override                
                    public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException
                    {
                        ps.setInt(1, censo);
                        ps.setInt(2, f);
                        ps.setBoolean(3, true);
                        
                        ps.executeUpdate();
                        
                        return true;
                        
                    }
                });
                
                
                
                
            }
                   
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CensoRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    private int obtenerIdFormulario() throws SQLException{
        
        
        Integer rs = jdbcTemplate.queryForObject(
                    "SELECT MAX(idCenso) FROM censo", Integer.class);
        
        if(rs!=null && rs!=0){
            return rs;
        }else{
            return 0;     
        }
        
        
       
        
    }
    
    public List<CensoDTO> getCensos() {
        String SQL = "select * from censo " +
                    "inner join formulariosCenso fc " +
                    "on censo.idCenso = fc.idCenso";           
            
        List <CensoDTO> students = jdbcTemplate.query(SQL, 
           new ResultSetExtractor<List<CensoDTO>>(){

           public List<CensoDTO> extractData(
              ResultSet rs) throws SQLException, DataAccessException {

              List<CensoDTO> list = new ArrayList<CensoDTO>();  
              while(rs.next()){  
                 CensoDTO student = new CensoDTO();
//                student.setActivo(rs.getBoolean("activo"));
//                student.setApeMaterno(rs.getString("apePaterno")); 
//                student.setApePaterno(rs.getString("apeMaterno"));
//                student.setEmail(rs.getString("email"));
//                student.setFecNacimiento(rs.getDate("fecNacimiento"));
//                student.setIdRol(rs.getInt("idRol"));
//                student.setNombres(rs.getString("nombres"));
//                student.setPassword(rs.getString("password"));
//                student.setUsuario(rs.getString("usuario"));
//                student.setUsuarioId(rs.getInt("idUsuario"));
                 list.add(student);  

                 
              }  
              return list;  
           }    	  
        });
        return students;
    }
    
}
