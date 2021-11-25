package com.urgencias.paciente.controllers;

import com.urgencias.paciente.exceptions.EnfermedadNotCoincidenceException;
import com.urgencias.paciente.exceptions.PacienteNotFoundException;
import com.urgencias.paciente.models.Enfermedad;
import com.urgencias.paciente.models.Paciente;
import com.urgencias.paciente.repositories.PacienteRepository;
import com.urgencias.paciente.repositories.EnfermedadRepository;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class EnfermedadController {

    private final EnfermedadRepository enfermedadRepository;
    private final PacienteRepository pacienteRepository;


    public EnfermedadController(EnfermedadRepository enfermedadRepository, PacienteRepository pacienteRepository) {
        this.enfermedadRepository = enfermedadRepository;
        this.pacienteRepository = pacienteRepository;
    }
    //Crear enfermedad
    @PostMapping("/enfermedad")
    public Enfermedad newEnfermedad(@RequestBody Enfermedad enfermedad) {
        // Condición para emitir excepción en caso de que exista la enfermedad a crear
        if(!enfermedadRepository.existsById(enfermedad.getNombre())) {
            return enfermedadRepository.save(enfermedad);
        }
        else{
            return enfermedadRepository.findById("").orElseThrow(() -> new EnfermedadNotCoincidenceException
                    ("Ya existe la enfermedad con nombre: " + enfermedad.getNombre()));
        }
    }
    // Ver todas las enfermedades
    @GetMapping("/enfermedades")
    public List<Enfermedad> getEnfermedades(){
        return enfermedadRepository.findAll();
    }

    // Ver una enfermedad en específico
    @GetMapping("/enfermedad/{nombre}")
    public Enfermedad getEnfermedad(@PathVariable String nombre) {
        return enfermedadRepository.findById(nombre).orElseThrow(() -> new EnfermedadNotCoincidenceException
                ("No se encontró la enfermedad: " + nombre));
    }

    // Filtrar enfermedades según síntomas de pacientes
    @GetMapping("/enfermedades/{documento}")
    public List<Enfermedad> enfermedadesPaciente (@PathVariable String documento){
        //Excepción si no está el documento
        Paciente paciente =pacienteRepository.findById(documento).orElseThrow(() -> new PacienteNotFoundException
                ("No se encontró un paciente con el documento: " + documento));
        List <Enfermedad> enfermedades = enfermedadRepository.findAll();
        List<String> sintomas= paciente.getSintomas();

        // Creación de variables vacías para comparar sintomas, organizar las enfermedades de mayor coincidencia
        //en síntomas a menor.
        List<Enfermedad> enfermedades_filt=new ArrayList<>();
        List<Integer> coincidencias =new ArrayList<>();
        List<Integer> posiciones = new ArrayList<>();

        for (Enfermedad i : enfermedades){
            int n=0;
            List<String> sinto_enfer= i.getSintomas();
            for(String s : sintomas){
                if(sinto_enfer.contains(s)) {
                    n++;
                }
            }
            if(n>0) {
                coincidencias.add(n);
                posiciones.add(enfermedades.indexOf(i));
            }
            //aux2.retainAll(sintomas);
            //enfermedades_filt.add(i);
            //continue;
        }
        //System.out.println(coincidencias);
        //System.out.println(posiciones);

        if (coincidencias.size()==1){
            enfermedades_filt.add(enfermedades.get(posiciones.get(0)));
        }
        if (coincidencias.size()>1) {
            for (int i = Collections.max(coincidencias); i > 0; i--) {
                for (int p = 0; p < coincidencias.size(); p++) {
                    if (coincidencias.get(p) == i) {
                        enfermedades_filt.add(enfermedades.get(posiciones.get(p)));
                    }
                }
            }
        }
        // Excepción si no hay ninguna coincidencia
        if (enfermedades_filt.size()==0){
            Enfermedad enfermedadn = enfermedadRepository.findById(" ").orElseThrow(() -> new EnfermedadNotCoincidenceException
                    ("No existe una enfermedad registrada que coincida con alguno de los síntomas del paciente con documento: " + documento));
            enfermedades_filt.add(enfermedadn);
        }

        return enfermedades_filt;
    }
    // Actualizar enfermedad
    @PutMapping("/actualizarEnfermedad/{nombre}")
    public Enfermedad updateEnfermedad(@PathVariable String nombre, @Validated @RequestBody Enfermedad enfermedad) {
        enfermedadRepository.findById(nombre).orElseThrow( ()-> new EnfermedadNotCoincidenceException
                ("No se encontró la enfermedad: " + nombre + ". Por favor ingrese los datos como una enfermedad nueva."));
        if (enfermedad.getNombre()!=nombre) {
            // Si se cambia el nombre de la enfermedad se elimina la enfermedad con el nombre anterior y se agrega la nueva
            enfermedadRepository.deleteById(nombre);
        }
        return enfermedadRepository.save(enfermedad);
    }
    // Eliminar enfermedad
    @DeleteMapping("eliminarEnfermedad/{nombre}")
    public String delEnfermedad(@PathVariable String nombre){
        enfermedadRepository.findById(nombre).orElseThrow( ()-> new EnfermedadNotCoincidenceException
                ("No se encontró la enfermedad: " + nombre));
        enfermedadRepository.deleteById(nombre);
        return "Enfermedad eliminada correctamente";
    }


}
