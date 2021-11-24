package com.urgencias.paciente.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Paciente {
    @Id
    private String documento;
    private String tipoDocumento;
    private String nombre;
    private Integer edad;
    private String genero;
    private Integer celular;
    private String correo;
    private String observaciones;
    private List<String> sintomas;

    public Paciente(String documento, String tipoDocumento, String nombre, Integer edad, String genero, Integer celular, String correo, String observaciones, List<String> sintomas) {
        this.documento = documento;
        this.tipoDocumento = tipoDocumento;
        this.nombre = nombre;
        this.edad = edad;
        this.genero = genero;
        this.celular = celular;
        this.correo = correo;
        this.observaciones = observaciones;
        this.sintomas = sintomas;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getCelular() {
        return celular;
    }

    public void setCelular(Integer celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }
}


