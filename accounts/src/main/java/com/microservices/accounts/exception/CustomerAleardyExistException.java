package com.microservices.accounts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerAleardyExistException extends RuntimeException{

    public CustomerAleardyExistException(String msg) {
       super(msg);
    }
}
