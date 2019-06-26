/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;

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
                + " (nombres, ap_pat, ap_mat, rol_id, email, fecnacimiento, contrasenia) "
                + " VALUE(?,?,?,?,?,Curdate(),?)";
        
        System.out.println("llega antes de insertar");
        
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){  
            @Override  
            public Boolean doInPreparedStatement(PreparedStatement ps) throws SQLException  
                     {  
                         System.out.println("entra aqui");
                ps.setString(1, u.getNombres());  
                ps.setString(2, u.getApPat());  
                ps.setString(3, u.getApMat());
                ps.setInt(4, u.getRolId()); 
                ps.setString(5, u.getEmail()); 
                ps.setString(6, u.getPassword()); 

                return ps.execute();  

            }  
            });  
        
//        jdbcTemplate.execute(query);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean login(UsuarioDTO u) {
        String query = "select * from usuario"
                       + " where email = (?)"
                       + " and contrasenia = (?)";
        
        try {
            System.out.println(u.getEmail());
            System.out.println(u.getPassword());
            PreparedStatement preparedStatement = jdbcTemplate.getDataSource().getConnection().prepareStatement(query);
            preparedStatement.setString(1, u.getEmail());
            preparedStatement.setString(2, u.getPassword());
            ResultSet rs = preparedStatement.executeQuery();
            if(rs.first()){
                System.out.println("si hay");
                return true;
            }else{
                System.out.println("no hay");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
}
