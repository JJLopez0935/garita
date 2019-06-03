/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pe.com.pucp.dp2.api.upload.elasticsearch.dao.csv.FamiliaDAO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.FamiliaUnificado;
import pe.com.pucp.dp2.api.upload.elasticsearch.repository.FamiliaUnificadoRepository;

/**
 *
 * @author johnny
 */
@Service
public class FamiliaUnificadoServiceImpl implements FamiliaUnificadoService{

    @Autowired
    FamiliaUnificadoRepository familiaRepository;
    
    @Autowired
    FamiliaDAO familiaDAOimpl;
    
    @Override
    public void uploadCsv(MultipartFile file) throws Exception{
        System.out.println("entra service upload");
        List<FamiliaUnificado> listFamilia = familiaDAOimpl.getListFamiliaUnificado(file);
        familiaRepository.saveAll(listFamilia);
    }
    
}
