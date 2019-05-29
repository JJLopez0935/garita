/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.model.bd;

import org.springframework.data.annotation.Id;

/**
 *
 * @author johnny
 */
@org.springframework.data.elasticsearch.annotations.Document(indexName = "familia", type = "familia")
public class Familia {

    
    @Id
    private String id;
    
    private String idFamily;
    private String direccion;
    private String manzana;
    private int lote;
    private int viviendaMetros;
    private int viviendaConstruidos;
    private int integrantes;
    private int cantPerros;
    private int cantGatos;
    private int otrosAnimales;
    private boolean jardin;
    private float jardinMetros;
    private int nroIntegrante;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private int edad;
    private String educacion;
    
    public Familia(){}
    
    public Familia(String idFamily,
                    String direccion,
                    String manzana,
                    int lote,
                    int viviendaMetros,
                    int viviendaConstruidos,
                    int integrantes,
                    int cantPerros,
                    int cantGatos,
                    int otrosAnimales,
                    boolean jardin,
                    float jardinMetros,
                    int nroIntegrante,
                    String nombre,
                    String apellidoPaterno,
                    String apellidoMaterno,
                    int edad,
                    String educacion){
        
        this.idFamily = idFamily;
        this.direccion = direccion;
        this.manzana = manzana;
        this.lote = lote;
        this.viviendaMetros = viviendaMetros;
        this.viviendaConstruidos = viviendaConstruidos;
        this.integrantes = integrantes;
        this.cantPerros = cantPerros;
        this.cantGatos = cantGatos;
        this.otrosAnimales = otrosAnimales;
        this.jardin = jardin;
        this.nroIntegrante = nroIntegrante;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = edad;
        this.educacion = educacion;
        
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdFamily() {
        return idFamily;
    }

    public void setIdFamily(String idFamily) {
        this.idFamily = idFamily;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public int getLote() {
        return lote;
    }

    public void setLote(int lote) {
        this.lote = lote;
    }

    public int getViviendaMetros() {
        return viviendaMetros;
    }

    public void setViviendaMetros(int viviendaMetros) {
        this.viviendaMetros = viviendaMetros;
    }

    public int getViviendaConstruidos() {
        return viviendaConstruidos;
    }

    public void setViviendaConstruidos(int viviendaConstruidos) {
        this.viviendaConstruidos = viviendaConstruidos;
    }

    public int getIntegrantes() {
        return integrantes;
    }

    public void setIntegrantes(int integrantes) {
        this.integrantes = integrantes;
    }

    public int getCantPerros() {
        return cantPerros;
    }

    public void setCantPerros(int cantPerros) {
        this.cantPerros = cantPerros;
    }

    public int getCantGatos() {
        return cantGatos;
    }

    public void setCantGatos(int cantGatos) {
        this.cantGatos = cantGatos;
    }

    public int getOtrosAnimales() {
        return otrosAnimales;
    }

    public void setOtrosAnimales(int otrosAnimales) {
        this.otrosAnimales = otrosAnimales;
    }

    public boolean isJardin() {
        return jardin;
    }

    public void setJardin(boolean jardin) {
        this.jardin = jardin;
    }

    public float getJardinMetros() {
        return jardinMetros;
    }

    public void setJardinMetros(float jardinMetros) {
        this.jardinMetros = jardinMetros;
    }

    public int getNroIntegrante() {
        return nroIntegrante;
    }

    public void setNroIntegrante(int nroIntegrante) {
        this.nroIntegrante = nroIntegrante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEducacion() {
        return educacion;
    }

    public void setEducacion(String educacion) {
        this.educacion = educacion;
    }
    
}
