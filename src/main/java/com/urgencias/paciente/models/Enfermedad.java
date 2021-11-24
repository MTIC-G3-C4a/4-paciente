package com.urgencias.paciente.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Enfermedad {
    @Id
    private String nombre;
    private List<String> sintomas;
    private List<String> medicina;

    public Enfermedad(String nombre, List<String> sintomas, List<String> medicina) {
        this.nombre = nombre;
        this.sintomas = sintomas;
        this.medicina = medicina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getSintomas() {
        return sintomas;
    }

    public void setSintomas(List<String> sintomas) {
        this.sintomas = sintomas;
    }

    public List<String> getMedicina() {
        return medicina;
    }

    public void setMedicina(List<String> medicina) {
        this.medicina = medicina;
    }
}
