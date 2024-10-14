package com.microservices.accounts.exception;

import com.microservices.accounts.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

@ExceptionHandler(CustomerAleardyExistException.class)
 public ResponseEntity<ErrorResponseDto> handleCustomerAlreadyExistsException(
         CustomerAleardyExistException customerAlreadyExistsException, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
        webRequest.getDescription(false),
        HttpStatus.BAD_REQUEST,
        customerAlreadyExistsException.getMessage(),
        LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto , HttpStatus.BAD_REQUEST);
 }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(
            ResourceNotFoundException resourceNotFoundException, WebRequest webRequest) {

        ErrorResponseDto errorResponseDto = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.NOT_FOUND,
                resourceNotFoundException.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDto , HttpStatus.NOT_FOUND);
    }


}
