package com.project.Desafio_itau.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataNoFuturoException extends RuntimeException {
    public DataNoFuturoException(String mensagem) {
        super(mensagem);
    }
}
