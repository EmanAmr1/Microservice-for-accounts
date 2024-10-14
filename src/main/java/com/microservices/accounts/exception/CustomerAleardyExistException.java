package com.microservices.accounts.exception;

public class CustomerAleardyExistException extends RuntimeException{

    public CustomerAleardyExistException(String msg) {
       super(msg);
    }
}
