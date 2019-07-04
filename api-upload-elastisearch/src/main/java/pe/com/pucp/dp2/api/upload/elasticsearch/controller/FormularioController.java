/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.controller;

import java.io.StringWriter;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bean.ResponseGeneral;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.FormularioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.repository.FormularioRepositoryImpl;

/**
 *
 * @author johnny
 */
@Controller
@RequestMapping(value = "/api/formulario")
public class FormularioController {
    
    @Autowired
    FormularioRepositoryImpl formularioRepositoryImpl;
    
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> insertarUsuario(@RequestBody @Valid @NotNull List<FormularioDTO> formularios) {
        try{
            for(FormularioDTO formulario :formularios)
                formularioRepositoryImpl.saveUsuario(formulario);
            return new ResponseEntity<>(new ResponseGeneral(200, "Insertado satisfactoriamente", null), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(new ResponseGeneral(500, sw.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    
}
