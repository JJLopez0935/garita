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
    public ResponseEntity<String> login(@RequestBody @Valid @NotNull  UsuarioDTO usuarioDto) {
        try{
            if(usuarioService.login(usuarioDto))
                
                return new ResponseEntity<>("OK", HttpStatus.OK);
            else
                return new ResponseEntity<>("NO", HttpStatus.OK);
            
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    @RequestMapping(value = "/insertar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> insertarUsuario(@RequestBody @Valid @NotNull  UsuarioDTO usuarioDto) {
        try{
            return new ResponseEntity<>("Ok", HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(sw.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    
}
