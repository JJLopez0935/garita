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
import pe.com.pucp.dp2.api.upload.elasticsearch.repository.FamiliaRepository;
import pe.com.pucp.dp2.api.upload.elasticsearch.dao.csv.FamiliaDAO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.Familia;

/**
 *
 * @author johnny
 */
@Service
public class FamiliaServiceImpl implements FamiliaService{

    @Autowired
    FamiliaRepository familiaRepository;
    
    @Autowired
    FamiliaDAO familiaDAOimpl;
    
    @Override
    public void uploadCsv(MultipartFile file) {
        List<Familia> listFamilia = familiaDAOimpl.getListFamilia(file);
        familiaRepository.saveAll(listFamilia);
    }
    
}
