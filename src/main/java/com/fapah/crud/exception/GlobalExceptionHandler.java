package com.fapah.crud.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleEmptyResultException(EmptyResultException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NullParameterException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleNullParameterException(NullParameterException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), Instant.now(), ex.getMessage()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchDataException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleNoSuchDataException(NoSuchDataException ex) {
        log.warn(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), Instant.now(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ErrorResponse> handleUncaughtException(WebRequest request, RuntimeException ex)
    {
        log.warn("Handling uncaught controller exception for {}", request, ex);
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), Instant.now(), ex.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

