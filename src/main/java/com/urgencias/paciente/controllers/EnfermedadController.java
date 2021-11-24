package com.urgencias.paciente.controllers;

import com.urgencias.paciente.models.Enfermedad;
import com.urgencias.paciente.repositories.EnfermedadRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EnfermedadController {

    private final EnfermedadRepository enfermedadRepository;

    public EnfermedadController(EnfermedadRepository enfermedadRepository) {
        this.enfermedadRepository = enfermedadRepository;
    }

    @PostMapping("/enfermedad")
    Enfermedad newEnfermedad(@RequestBody Enfermedad enfermedad) {
        return enfermedadRepository.save(enfermedad);
    }

    @GetMapping("/enfermedades")
    List<Enfermedad> getEnfermedades(){
        return enfermedadRepository.findAll();
    }


}
