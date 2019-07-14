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
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ResidenteDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaDTO;

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
    
    public Boolean saveUsuario(FormularioDTO f)  {
        
        
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
    
    public List<FormularioDTO> getFormularios() {
        try{
            String SQL = "select * from formulario f " +
                         "inner join preguntaFormulario pFo on pFo.idFormulario = f.idFormulario " +
                         "inner join pregunta p on p.idPregunta = pFo.idPregunta ";           

            List <FormularioDTO> students = jdbcTemplate.query(SQL, 
               new ResultSetExtractor<List<FormularioDTO>>(){

               public List<FormularioDTO> extractData(
                  ResultSet rs) throws SQLException, DataAccessException {

                  List<FormularioDTO> list = new ArrayList<FormularioDTO>();  
                  while(rs.next()){ 
                    FormularioDTO vi = buscarFormulario(list, rs.getInt("idFormulario"));
                    if(vi!=null){
                        Pregunta residente = new Pregunta();
                        residente.setIdPregunta(rs.getInt("idPregunta"));
                        residente.setTipoPregunta(rs.getString("TipoPregunta"));
                        residente.setPregunta(rs.getString("pregunta"));
                        residente.setClave(rs.getString("clave"));
                        
                        
                        if(residente.getTipoPregunta().equals("opciones")){                            
                            residente.setOpciones(getOpciones(residente.getIdPregunta()));
                        }
                        
                        vi.getPreguntas().add(residente);

                    }else{                        
                        FormularioDTO student = new FormularioDTO();
                        student.setIdFormulario(rs.getInt("idFormulario"));
                        student.setNombre(rs.getString("nombre"));
                        student.setTipo(rs.getString("tipo"));
                        student.setEstado(rs.getString("estado"));
                        
                      
                        Pregunta residente = new Pregunta();
                        residente.setIdPregunta(rs.getInt("idPregunta"));
                        residente.setTipoPregunta(rs.getString("TipoPregunta"));
                        residente.setPregunta(rs.getString("pregunta"));
                        residente.setClave(rs.getString("clave"));
                        
                        if(residente.getTipoPregunta().equals("opciones")){                            
                            residente.setOpciones(getOpciones(residente.getIdPregunta()));
                        }
                        

                        student.getPreguntas().add(residente);
                        list.add(student); 
                    }

                  }  
                  return list;  
               }    	  
            });
            return students;
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
        
    private FormularioDTO buscarFormulario(List<FormularioDTO> list, int id){
        for(FormularioDTO v: list){
            if(v.getIdFormulario()== id)
                return v;            
        }
        return null;
    }
    
    private List<String> getOpciones(int id){
        String sql2 = "select nombre from opcionPregunta op " +
                        "inner join opcion o on o.idOpcion = op.idOpcion " +
                         "where op.idPregunta = '"+id+"'";
                            
        List <String> opciones = jdbcTemplate.query(sql2, 
        new ResultSetExtractor<List<String>>(){

        public List<String> extractData(
            ResultSet rs) throws SQLException, DataAccessException {

                List<String> list = new ArrayList<String>();  
                while(rs.next()){ 
                    list.add(rs.getString("nombre"));
                }  
                return list;  
            }    	  
        });
        
        return opciones;
    }
    
    public FormularioDTO getFormularioById(String id) {
        try{
            String SQL = "select * from formulario f " +
                         "inner join preguntaFormulario pFo on pFo.idFormulario = f.idFormulario " +
                         "inner join pregunta p on p.idPregunta = pFo.idPregunta " +
                         "where f.idFormulario="+id;           

            FormularioDTO students = jdbcTemplate.query(SQL, 
               new ResultSetExtractor<FormularioDTO>(){

               public FormularioDTO extractData(
                  ResultSet rs) throws SQLException, DataAccessException {

                  int cont=0;
                  FormularioDTO student = new FormularioDTO();
                  while(rs.next()){ 
                    
                    if(cont>0){
                        Pregunta residente = new Pregunta();
                        residente.setIdPregunta(rs.getInt("idPregunta"));
                        residente.setTipoPregunta(rs.getString("TipoPregunta"));
                        residente.setPregunta(rs.getString("pregunta"));
                        residente.setClave(rs.getString("clave"));
                        
                        
                        if(residente.getTipoPregunta().equals("opciones")){                            
                            residente.setOpciones(getOpciones(residente.getIdPregunta()));
                        }
                        
                        student.getPreguntas().add(residente);

                    }else{                        
                        
                        student.setIdFormulario(rs.getInt("idFormulario"));
                        student.setNombre(rs.getString("nombre"));
                        student.setTipo(rs.getString("tipo"));
                        student.setEstado(rs.getString("estado"));
                        
                      
                        Pregunta residente = new Pregunta();
                        residente.setIdPregunta(rs.getInt("idPregunta"));
                        residente.setTipoPregunta(rs.getString("TipoPregunta"));
                        residente.setPregunta(rs.getString("pregunta"));
                        residente.setClave(rs.getString("clave"));
                        
                        if(residente.getTipoPregunta().equals("opciones")){                            
                            residente.setOpciones(getOpciones(residente.getIdPregunta()));
                        }
                        

                        student.getPreguntas().add(residente);
                        
                    }
cont++;
                  }  
                  return student;  
               }    	  
            });
            return students;
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }
   
    
}
