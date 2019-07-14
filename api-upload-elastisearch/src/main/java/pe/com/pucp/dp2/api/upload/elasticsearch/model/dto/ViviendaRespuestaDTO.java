/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.model.dto;

/**
 *
 * @author johnny
 */
public class ViviendaRespuestaDTO {
 
    private int idCensoVivienda;
    private int idCenso;
    private int idusuario;
    private int idVivienda;
    private String respuesta;
    private String estado;
    
    public ViviendaRespuestaDTO(){}

    public int getIdCensoVivienda() {
        return idCensoVivienda;
    }

    public void setIdCensoVivienda(int idCensoVivienda) {
        this.idCensoVivienda = idCensoVivienda;
    }

    public int getIdCenso() {
        return idCenso;
    }

    public void setIdCenso(int idCenso) {
        this.idCenso = idCenso;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdVivienda() {
        return idVivienda;
    }

    public void setIdVivienda(int idVivienda) {
        this.idVivienda = idVivienda;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}
