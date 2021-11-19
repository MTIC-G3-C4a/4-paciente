package com.urgencias.paciente.repositories;

import com.urgencias.paciente.models.Paciente;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PacienteRepository extends MongoRepository<Paciente,String>{

}