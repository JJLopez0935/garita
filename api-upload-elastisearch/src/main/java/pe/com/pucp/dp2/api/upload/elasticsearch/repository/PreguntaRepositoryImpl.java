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
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.FormularioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.Pregunta;

/**
 *
 * @author johnny
 */
@Repository
@Transactional
public class PreguntaRepositoryImpl {
    
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    public Boolean saveUsuario(Pregunta p)  {
        
        String query = "INSERT INTO pregunta"
                + " (TipoPregunta, pregunta, clave) "
                + " VALUE(?,?,?)";
        jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
            {  
                try {
                    ps.setString(1, p.getTipoPregunta());
                    ps.setString(2, p.getPregunta());
                    ps.setString(3, p.getClave());
                    
                    ps.executeUpdate();
                    
                    return true;
                } catch (SQLException ex) {
                    Logger.getLogger(PreguntaRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                    return false;
                }
                
            }  
        });
        System.out.println("antes de insertar OPCION");
        if(p.getTipoPregunta().equalsIgnoreCase("opciones")){
            
            
            int idOpcion = obtenerUltimoIdOpcion()+1; 
            int idPregunta = obtenerUltimoIdPregunta(); 
            for(String st: p.getOpciones()){
                String queryInsertOpcion = "INSERT INTO opcion"
                        + " (nombre, descripcion)"
                        + " VALUE(?,?)";
                
                
                jdbcTemplate.execute(queryInsertOpcion,new PreparedStatementCallback<Boolean>(){
                    @Override
                    public Boolean doInPreparedStatement(PreparedStatement ps)
                    {
                        try {
                            ps.setString(1, st);
                            ps.setString(2, st);
                            
                            ps.executeUpdate();
                            
                            return true;
                        } catch (SQLException ex) {
                            Logger.getLogger(PreguntaRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
                            return false;
                        }
                        
                    }
                });
                
                
                jdbcTemplate.update(
                        "INSERT INTO opcionPregunta (idPregunta, idOpcion, activo) VALUES (?, ?, ?)", idPregunta, idOpcion++, true);
                
                
                
            }
            
            
        }
        return true;
    }
    
    
    public int obtenerUltimoIdPregunta(){

        
        
        Integer rs = jdbcTemplate.queryForObject(
                    "SELECT MAX(idPregunta) FROM pregunta", Integer.class);
        
        
        
        if(rs!=null && rs!=0){
            return rs;
        }else{
            return 0;     
        }
        
    }
    
    public int obtenerUltimoIdOpcion() {
              System.out.println("mierdam ierda");
        Integer rs = jdbcTemplate.queryForObject(
                    "SELECT MAX(idOpcion) FROM opcion", Integer.class);
        
        
        if(rs!=null && rs!=0){
          return rs;
        }else{
            return 0;     
        }
    }
                }
