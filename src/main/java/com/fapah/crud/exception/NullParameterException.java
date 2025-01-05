package com.fapah.crud.exception;

import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class NullParameterException extends DataAccessException {
    public NullParameterException(String msg) {
        super(msg);
    }
}