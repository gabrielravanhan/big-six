package com.gabrielravanhan.service.exception;

public class RequiredFieldException extends BusinessException {

    public RequiredFieldException(String campo) {
        super("Campo obrigatório: %s.", campo);
    }
}
