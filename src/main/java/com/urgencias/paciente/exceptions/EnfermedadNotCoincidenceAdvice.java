package com.urgencias.paciente.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@ResponseBody

public class EnfermedadNotCoincidenceAdvice {
    @ResponseBody
    @ExceptionHandler(EnfermedadNotCoincidenceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EntityNotCoincidenceAdvice(EnfermedadNotCoincidenceException exception){
        return exception.getMessage();
    }
}
