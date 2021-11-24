package com.urgencias.paciente.repositories;

import com.urgencias.paciente.models.Enfermedad;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EnfermedadRepository extends MongoRepository<Enfermedad,String> {
}
