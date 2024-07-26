package com.gabrielravanhan.controller.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
public class ResponseError {

    private LocalDateTime timestamp;

    private String status;

    private int statusCode;

    private String message;
}
