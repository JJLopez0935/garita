/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository;

import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.RolDTO;

/**
 *
 * @author johnny
 */
public interface RolRepository {
    
    Boolean saveRol(RolDTO rol);
    
}
