/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.FamiliaUnificado;

/**
 *
 * @author johnny
 */
@Repository
public interface FamiliaUnificadoRepository extends ElasticsearchRepository<FamiliaUnificado, String>{
    
}
