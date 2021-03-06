/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.dao.csv;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.Familia;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.FamiliaUnificado;

/**
 *
 * @author johnny
 */
public interface FamiliaDAO {
    
    List<FamiliaUnificado> getListFamiliaUnificado(MultipartFile file) throws Exception;
    List<Familia> getListFamilia(MultipartFile file) throws Exception;
    
}
