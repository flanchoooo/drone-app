package com.musala.droneapp.exceptions;

public class InvalidTypeException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public InvalidTypeException(final String message) {
        super(message, "Invalid type.", true);
    }
}
