/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public boolean login(UsuarioDTO u) {
        return usuarioRepository.login(u);
    }
    
}
