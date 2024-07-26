package com.gabrielravanhan.service.exception;

public class NotFoundException extends BusinessException {

    public NotFoundException(String recurso) {
        super("Recurso não encontrado: %s.", recurso);
    }
}
