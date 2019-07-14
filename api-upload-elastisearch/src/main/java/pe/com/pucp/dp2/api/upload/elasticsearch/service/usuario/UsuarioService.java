/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.service.usuario;

import java.util.List;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.LoginDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;

/**
 *
 * @author johnny
 */
public interface UsuarioService {
    
    boolean saveUsuario(UsuarioDTO u);
    LoginDTO login(UsuarioDTO u);
    List<UsuarioDTO> listUsuarios();
    
}
