package com.gabrielravanhan.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException {

    HttpStatus httpStatus;

    public BusinessException(String messagem) {
        super(messagem);
        this.httpStatus = HttpStatus.BAD_REQUEST;
    }

    public BusinessException(String messagem, HttpStatus httpStatus) {
        super(messagem);
        this.httpStatus = httpStatus;
    }

    public BusinessException(String mensagem, Object... params) {
        super(String.format(mensagem, params));
    }
}
