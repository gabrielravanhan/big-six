package com.gabrielravanhan.service.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(String messagem) {
        super(messagem);
    }
}
