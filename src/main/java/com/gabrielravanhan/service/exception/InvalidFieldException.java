package com.gabrielravanhan.service.exception;

public class InvalidFieldException extends BusinessException {

    public InvalidFieldException(String campo) {
        super("Campo inválido: %s.", campo);
    }
}
