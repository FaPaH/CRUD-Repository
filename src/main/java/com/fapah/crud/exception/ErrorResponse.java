package com.fapah.crud.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private int code;
    private Instant timestamp;
    private String message;

    public ErrorResponse(String message)
    {
        super();
        this.message = message;
    }
}
