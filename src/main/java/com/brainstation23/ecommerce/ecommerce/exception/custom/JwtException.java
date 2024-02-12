package com.brainstation23.ecommerce.ecommerce.exception.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class JwtException extends RuntimeException{
    private static final String BASE_MESSAGE = "Jwt Exception : ";
    public JwtException(String message) {
        super(BASE_MESSAGE + message);
    }
}
