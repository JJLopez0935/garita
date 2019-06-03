/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import pe.com.pucp.dp2.api.upload.elasticsearch.service.FamiliaService;
import pe.com.pucp.dp2.api.upload.elasticsearch.service.FamiliaUnificadoService;

/**
 *
 * @author johnny
 */
@Controller
@RequestMapping(value = "/api/familia")
public class FamiliaController {
    
    
    @Autowired
    FamiliaUnificadoService familiaUnificadoService;
    
    @Autowired
    FamiliaService familiaService;
    
    @GetMapping("/resumen")
    public ResponseEntity<String> getResumen() {
        try{
            System.out.println("gggggg");
            return new ResponseEntity<>("gggg",HttpStatus.OK);
        }catch (Exception e){
            
            return new ResponseEntity<>("error",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    
    @RequestMapping(value = "/batchUpload", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> importCsv(@RequestPart(value = "file", required = false) MultipartFile file) {
        try{
            System.out.println("llega");
            if (file!=null && !file.isEmpty()) {
                familiaUnificadoService.uploadCsv(file);
            }
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return new ResponseEntity<>("Mal", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    @RequestMapping(value = "/batchFamilia", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> importCsv2(@RequestPart(value = "file", required = false) MultipartFile file) {
        try{
            System.out.println("llega");
            if (file!=null && !file.isEmpty()) {
                familiaService.uploadCsv(file);
            }
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            return new ResponseEntity<>("Mal", HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
}
