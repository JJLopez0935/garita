/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.controller;

import java.io.StringWriter;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bean.ResponseGeneral;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.service.usuario.UsuarioService;


/**
 *
 * @author johnny
 */
@Controller
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    
    
    @Autowired
    UsuarioService usuarioService;
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> login(@RequestBody @Valid @NotNull  UsuarioDTO usuarioDto) {
        try{
            if(usuarioService.login(usuarioDto))
                
                return new ResponseEntity<>(new ResponseGeneral(200, "Éxito", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ResponseGeneral(403, "No se encuentra registrado usuario", null), HttpStatus.NOT_ACCEPTABLE);
            
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(new ResponseGeneral(500, sw.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> insertarUsuario(@RequestBody @Valid @NotNull  UsuarioDTO usuarioDto) {
        try{
            if(usuarioService.saveUsuario(usuarioDto))
                return new ResponseEntity<>(new ResponseGeneral(200, "Insertado satisfactoriamente", null), HttpStatus.OK);
            else
                return new ResponseEntity<>(new ResponseGeneral(405, "Ya existe usuario registrado", null), HttpStatus.CONFLICT);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(new ResponseGeneral(500, sw.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    
}
