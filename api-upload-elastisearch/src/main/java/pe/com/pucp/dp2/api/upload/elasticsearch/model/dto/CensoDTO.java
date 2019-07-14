/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.model.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johnny
 */
public class CensoDTO {

    public List<FormularioDTO> getFormularios() {
        return formularios;
    }

    public void setFormularios(List<FormularioDTO> formularios) {
        this.formularios = formularios;
    }
    
    private int idCenso;
    private Date fechaRegistro;
    private Date fechaInicio;
    private int periodo;
    private String estado;
    private int viviendas;
    private int vivEncuestadas;
    private List<Integer> formulariosId;
    private List<FormularioDTO> formularios;
    
    public CensoDTO(){
        formulariosId = new ArrayList<>();
        formularios = new ArrayList<>();
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getViviendas() {
        return viviendas;
    }

    public void setViviendas(int viviendas) {
        this.viviendas = viviendas;
    }

    public int getVivEncuestadas() {
        return vivEncuestadas;
    }

    public void setVivEncuestadas(int vivEncuestadas) {
        this.vivEncuestadas = vivEncuestadas;
    }

    public List<Integer> getFormulariosId() {
        return formulariosId;
    }

    public void setFormulariosId(List<Integer> formulariosId) {
        this.formulariosId = formulariosId;
    }

    public int getIdCenso() {
        return idCenso;
    }

    public void setIdCenso(int idCenso) {
        this.idCenso = idCenso;
    }
    
    
    
}
