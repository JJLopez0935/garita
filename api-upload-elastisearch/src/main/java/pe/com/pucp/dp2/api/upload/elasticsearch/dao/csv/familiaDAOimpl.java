/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.dao.csv;

import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.FamiliaUnificado;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bd.Familia;

/**
 *
 * @author johnny
 */
@Component
public class familiaDAOimpl implements FamiliaDAO{

    @Override
    public List<FamiliaUnificado> getListFamiliaUnificado(MultipartFile file) throws Exception{
        BufferedReader br;
        List<FamiliaUnificado> listBranch = new ArrayList<>();
        String line;
        InputStream is;

        is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        String[] fields;
       
        br.readLine();
        

        while ((line = br.readLine()) != null){
                fields = line.split(",");
                String id = fields[0]; 
                String direccion = fields[1]; 
                String manzana = fields[2];
                
                String lote = fields[3];
                String viviendaMetros = fields[4];
                String viviendaConstruidos = fields[5];
                String integrantes = fields[6];
                String perros = fields[7];
                String gatos = fields[8];
                
                String otrosAnimales = fields[9];
                String jardin = fields[10];
                String jardinMetros = fields[11];
                String nroIntegrante = fields[12];
                String nombre = fields[13];
                String appelidoP = fields[14];
                String apellidoM = fields[15];
                String edad = fields[16];
                String eduacion = fields[17];
                
                listBranch.add(new FamiliaUnificado(id,
                        direccion,
                        manzana,
                        lote,
                        viviendaMetros,
                        viviendaConstruidos,
                        integrantes,
                        perros,
                        gatos,
                        otrosAnimales,
                        jardin,
                        jardinMetros,
                        nroIntegrante,
                        nombre,
                        appelidoP,
                        apellidoM,
                        edad,
                        eduacion));
            
        }


        return listBranch;
    }

    @Override
    public List<Familia> getListFamilia(MultipartFile file) throws Exception {
        BufferedReader br;
        List<Familia> listBranch = new ArrayList<>();
        String line;
        InputStream is;

        is = file.getInputStream();
        br = new BufferedReader(new InputStreamReader(is));
        String[] fields;
       
        br.readLine();
        

        while ((line = br.readLine()) != null){
                fields = line.split(",");
                String id = fields[0]; 
                String direccion = fields[1]; 
                String manzana = fields[2];
                
                String lote = fields[3];
                String viviendaMetros = fields[4];
                String viviendaConstruidos = fields[5];
                String integrantes = fields[6];
                String perros = fields[7];
                String gatos = fields[8];
                
                String otrosAnimales = fields[9];
                String jardin = fields[10];
                String jardinMetros = fields[11];
                
                
                listBranch.add(new Familia(id,
                        direccion,
                        manzana,
                        lote,
                        viviendaMetros,
                        viviendaConstruidos,
                        integrantes,
                        perros,
                        gatos,
                        otrosAnimales,
                        jardin,
                        jardinMetros
                ));
            
        }


        return listBranch;
    }
    
    
    
}
