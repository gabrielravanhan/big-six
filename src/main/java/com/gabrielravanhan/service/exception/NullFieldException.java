package com.gabrielravanhan.service.exception;

public class NullFieldException extends BusinessException {

    public NullFieldException(String campo) {
        super("É necessário informar " + campo + ".");
    }
}
