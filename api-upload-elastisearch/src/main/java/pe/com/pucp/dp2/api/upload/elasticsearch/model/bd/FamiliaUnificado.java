/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.pucp.dp2.api.upload.elasticsearch.model.bd;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.text.SimpleDateFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import java.util.Date;

/**
 *
 * @author johnny
 */
@org.springframework.data.elasticsearch.annotations.Document(indexName = "familiaunificado", type = "familiaunificado")
public class FamiliaUnificado {

    
    @Id
    private String id;
    
    @Field( type = FieldType.Date, format = DateFormat.custom, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;
    
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
    
    public FamiliaUnificado(){}
    
    public FamiliaUnificado(String idFamily,
                    String direccion,
                    String manzana,
                    String lote,
                    String viviendaMetros,
                    String viviendaConstruidos,
                    String integrantes,
                    String cantPerros,
                    String cantGatos,
                    String otrosAnimales,
                    String jardin,
                    String jardinMetros,
                    String nroIntegrante,
                    String nombre,
                    String apellidoPaterno,
                    String apellidoMaterno,
                    String edad,
                    String educacion){
        
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
        this.date = new Date(System.currentTimeMillis());  
        
        this.idFamily = idFamily;
        this.direccion = direccion;
        this.manzana = manzana;
        this.lote = Integer.valueOf(lote);
            this.viviendaMetros = Integer.valueOf(viviendaMetros);
        this.viviendaConstruidos = Integer.valueOf(viviendaConstruidos);
        this.integrantes = Integer.valueOf(integrantes);
        this.cantPerros = Integer.valueOf(cantPerros);
        this.cantGatos = Integer.valueOf(cantGatos);
        this.otrosAnimales = Integer.valueOf(otrosAnimales);
        if(jardin=="1")
            this.jardin = true;
        else
            this.jardin = false;
        this.jardinMetros = Float.valueOf(jardinMetros);
        this.nroIntegrante = Integer.valueOf(nroIntegrante);
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.edad = Integer.valueOf(edad);
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
