package com.gabrielravanhan.service.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException() {
        super("Recurso não encontrado.");
    }
}
