package com.example.webfluxsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorisedException extends ApiException {
    public UnauthorisedException(String message) {
        super(message, "ADMIN_UNAUTHORISED");
    }
}
