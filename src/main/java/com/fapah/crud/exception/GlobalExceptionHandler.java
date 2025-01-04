package com.fapah.crud.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyResultException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleEmptyResultException(EmptyResultException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(NullParameterException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleNullParameterException(NullParameterException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_ACCEPTABLE.value(), Instant.now(), ex.getMessage()),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchDataException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleNoSuchDataException(NoSuchDataException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND.value(), Instant.now(), ex.getMessage()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        log.error(ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(HttpStatus.NO_CONTENT.value(), Instant.now(), ex.getMessage()),
                HttpStatus.I_AM_A_TEAPOT);
    }
}
