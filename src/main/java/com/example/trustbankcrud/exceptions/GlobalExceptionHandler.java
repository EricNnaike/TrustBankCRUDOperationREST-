package com.example.trustbankcrud.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ErrorDetails> handleInvalidRequestException(InvalidRequestException e, WebRequest request) {
        ErrorDetails errorDetails = buildCustomExceptionResponse(e, request, HttpStatus.UNPROCESSABLE_ENTITY);
        return new ResponseEntity<>(errorDetails, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException e, WebRequest request) {
        ErrorDetails errorDetails = buildCustomExceptionResponse(e, request, HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    private ErrorDetails buildCustomExceptionResponse(Exception e, WebRequest request, HttpStatus status) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .details(request.getDescription(false))
                .build();
        return errorDetails;
    }
}
