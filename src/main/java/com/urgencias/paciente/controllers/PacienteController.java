package com.urgencias.paciente.controllers;

import com.urgencias.paciente.exceptions.PacienteNotFoundException;
import com.urgencias.paciente.models.Paciente;
import com.urgencias.paciente.repositories.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.DELETE, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT})
@RequestMapping("/pacientes")

public class PacienteController {

    @Autowired
    private PacienteRepository repository;

    @PostMapping("/paciente")
    public Paciente createPaciente(@Validated @RequestBody Paciente p){
        return repository.insert(p);
    }

    @GetMapping("/")
    public List<Paciente> getAllPacientes(){
        return repository.findAll();
    }

    @GetMapping("/{documento}")
    public Paciente getPaciente(@PathVariable String documento) {
        return repository.findById(documento).orElseThrow(() -> new PacienteNotFoundException
                ("No se encontro un paciente con el documento: " + documento));
    }

    @PutMapping("/updatePaciente/{documento}")
    public Paciente updatePaciente(@PathVariable String documento, @Validated @RequestBody Paciente p){
        p.setDocumento(documento);
        return repository.save(p);
    }

    @DeleteMapping("/deletePaciente/{documento}")
    public void deletePaciente(@PathVariable String documento){
        repository.deleteById(documento);
    }
}