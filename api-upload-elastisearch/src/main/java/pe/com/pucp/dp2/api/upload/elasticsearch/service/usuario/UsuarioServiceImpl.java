/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.service.usuario;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.LoginDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.repository.Usuario.UsuarioRepository;

/**
 *
 * @author johnny
 */
@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired 
    UsuarioRepository usuarioRepository;
    
    @Override
    public boolean saveUsuario(UsuarioDTO u) {
        return usuarioRepository.saveUsuario(u);
        
    }

    @Override
    public LoginDTO login(UsuarioDTO u) {
        return usuarioRepository.login(u);
    }

    @Override
    public List<UsuarioDTO> listUsuarios() {
        return usuarioRepository.getUsuarios();
    }

    @Override
    public boolean actualizarUsuario(UsuarioDTO u) {
        return usuarioRepository.actualizarUsuario(u);
    }
    
}
