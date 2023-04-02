package com.musala.droneapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ExceptionResponseModel {
    private String title;
    private HttpStatus status;
    private String message;
    private String timestamp;
}