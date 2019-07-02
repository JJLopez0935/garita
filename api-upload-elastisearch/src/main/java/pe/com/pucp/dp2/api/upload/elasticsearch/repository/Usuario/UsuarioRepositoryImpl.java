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
                return true;
            }else{
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioRepositoryImpl.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
        
    }
    
}
