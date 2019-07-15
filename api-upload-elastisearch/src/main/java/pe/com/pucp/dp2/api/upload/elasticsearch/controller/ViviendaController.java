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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.bean.ResponseGeneral;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.UsuarioDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.model.dto.ViviendaRespuestaDTO;
import pe.com.pucp.dp2.api.upload.elasticsearch.repository.ViviendaRepositoryImpl;

/**
 *
 * @author johnny
 */
@Controller
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
@RequestMapping(value = "/api/vivienda")
public class ViviendaController {
    
    
    @Autowired
    ViviendaRepositoryImpl viviendaRepositoryImpl;
    
    @RequestMapping(value = "/crear", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> insertarUsuario(@RequestBody @Valid @NotNull ViviendaDTO usuarioDto) {
        try{
            
            
            if(viviendaRepositoryImpl.saveUsuario(usuarioDto))
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
    
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ViviendaDTO>> getUsuarios() {
        try{
            
            return new ResponseEntity<>(viviendaRepositoryImpl.getViviendas(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            System.out.println(e.getCause());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    @RequestMapping(value = "/crearRespuestaVivienda", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> crearRespuestaVivienda(@RequestBody @Valid @NotNull ViviendaRespuestaDTO usuarioDto) {
        try{
            
            viviendaRepositoryImpl.crearCensoVivienda(usuarioDto);
            return new ResponseEntity<>(new ResponseGeneral(200, "Insertado satisfactoriamente", null), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(new ResponseGeneral(500, sw.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    
    @RequestMapping(value = "/insertarRespuesta", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> insertarVivienda(@RequestBody @Valid @NotNull List<ViviendaRespuestaDTO> usuarioDto) {
        try{
            
            viviendaRepositoryImpl.updateViviendaRespuesta(usuarioDto);
            return new ResponseEntity<>(new ResponseGeneral(200, "Insertado satisfactoriamente", null), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(new ResponseGeneral(500, sw.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ResponseGeneral> actualizarUsuario(@RequestBody @Valid @NotNull ViviendaDTO usuarioDto) {
        try{
            
            
            viviendaRepositoryImpl.actualizarUsuario(usuarioDto);
            return new ResponseEntity<>(new ResponseGeneral(200, "Modificado satisfactoriamente", null), HttpStatus.OK);
            
            
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            StringWriter sw = new StringWriter();
            return new ResponseEntity<>(new ResponseGeneral(500, sw.toString(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    
    @RequestMapping(value = "/getViviendas", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ViviendaDTO>> getUsuarios22() {
        try{
            
            return new ResponseEntity<>(viviendaRepositoryImpl.getViviendasSinResidentes(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            System.out.println(e.getCause());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
    @RequestMapping(value = "/getCalvo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<ViviendaDTO>> getUsuarios2233() {
        try{
            
            return new ResponseEntity<>(viviendaRepositoryImpl.getCalvo(), HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            System.out.println(e);
            System.out.println(e.toString());
            System.out.println(e.getCause());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }
    
}
