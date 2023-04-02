package com.musala.droneapp.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public abstract class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -4986825699512723803L;
    private final boolean userError;
    private final String title;

    protected BusinessException(final String message, final String title, final boolean userError) {
        super(message);
        this.userError = userError;
        this.title = title;
    }

    protected BusinessException(final String message, final String title, final Throwable cause, final boolean userError) {
        super(message, cause);
        this.userError = userError;
        this.title = title;
    }
}
