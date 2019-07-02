/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.Formulario;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.Pregunta;

/**
 *
 * @author johnny
 */
@Repository
@Transactional
public class FormularioRepositoryImpl {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Autowired
    PreguntaRepositoryImpl preguntaRepositoryImpl;
    
    public Boolean saveUsuario(Formulario f)  {
        
        
        try {
            String query = "INSERT INTO formulario"
                    + " (nombre, tipo, estado) "
                    + " VALUE(?,?,?)";
            
            
            jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps)   
                     {  
                try {
                    ps.setString(1, f.getNombre());
                    ps.setString(2, f.getTipo());
                    ps.setString(3, f.getEstado());
                    
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(FormularioRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                
            }  
        });
            
            
            
            int idFormulario = obtenerIdFormulario();
            
            
            for(Pregunta p : f.getPreguntas()){
                preguntaRepositoryImpl.saveUsuario(p);
                int idPregunta = preguntaRepositoryImpl.obtenerUltimoIdPregunta();
                String queryInsertOpcion = "INSERT INTO preguntaFormulario"
                        + " (idFormulario, idPregunta, activo)"
                        + " VALUE(?,?,?)";
                
                
                
                jdbcTemplate.execute(queryInsertOpcion,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) 
                     {  
                try {
                    ps.setInt(1, idFormulario);
                    ps.setInt(2, idPregunta);
                    ps.setBoolean(3, true);
                    
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(FormularioRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                
            }  
        });
                
               
                
       
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FormularioRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }
    
    public int obtenerIdFormulario() throws SQLException{    
        
        
        
        
        
        Integer rs = jdbcTemplate.queryForObject(
                    "SELECT MAX(idformulario) FROM formulario", Integer.class);
        
        if(rs!=null && rs!=0){
            return rs;
        }else{
            return 0;     
        }
        

        
        
    }
    
}
