package com.fapah.crud.exception;

import java.util.NoSuchElementException;

public class NoSuchDataException extends NoSuchElementException {
    public NoSuchDataException(String msg) {
        super(msg);
    }
}
