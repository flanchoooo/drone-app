package com.musala.droneapp.exceptions;

public class BadRequestException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public BadRequestException(final String message) {
        super(message, "", true);
    }
}
