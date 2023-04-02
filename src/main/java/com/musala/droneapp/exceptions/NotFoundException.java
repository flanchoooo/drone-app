package com.musala.droneapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(final String reason) {
        super(HttpStatus.NOT_FOUND, reason);//message will come from the ExceptionTranslator
    }

    public NotFoundException() {
        super(HttpStatus.NOT_FOUND, "");//message will come from the ExceptionTranslator
    }
}
