package com.musala.droneapp.exceptions;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@ControllerAdvice
@Slf4j
@AllArgsConstructor
public class ExceptionTranslator {

    private final SimpleDateFormat errorDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponseModel> handleException(final BusinessException exception,
                                                                  final NativeWebRequest request) {

        final HttpStatus status = exception.isUserError() ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR;
        final ExceptionResponseModel errorDetail = new ExceptionResponseModel();
        errorDetail.setTimestamp(errorDateFormat.format(new Date()));
        errorDetail.setStatus(status);
        errorDetail.setMessage(exception.getMessage());
        errorDetail.setTitle(exception.getTitle());
        log.error(exception.getMessage());
        return new ResponseEntity<>(errorDetail, null, errorDetail.getStatus());
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponseModel> handleException(final ResponseStatusException exception, final NativeWebRequest request) {
        final ExceptionResponseModel errorDetail = new ExceptionResponseModel();
        errorDetail.setMessage(exception.getReason() == null ? "The requested record could not be found." : "The requested data for " + exception.getReason() + " could not be found.");
        errorDetail.setStatus(HttpStatus.NOT_FOUND);
        errorDetail.setTimestamp(errorDateFormat.format(new Date()));
        errorDetail.setTitle(exception.getReason() == null ? "Record not found" :  exception.getReason() + " not found");
        log.error(exception.getMessage());
        return new ResponseEntity<>(errorDetail, null, errorDetail.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ExceptionResponseModel> onConstraintValidationException(ConstraintViolationException e) {
        final ExceptionResponseModel errorDetail = new ExceptionResponseModel();
        e.getConstraintViolations().forEach(violation -> {
            errorDetail.setMessage(violation.getPropertyPath().toString().toUpperCase(Locale.ROOT) + " - " + violation.getMessage());
        });

        errorDetail.setStatus(HttpStatus.BAD_REQUEST);
        errorDetail.setTimestamp(errorDateFormat.format(new Date()));
        errorDetail.setTitle("Validation Error");
        return new ResponseEntity<>(errorDetail, null, errorDetail.getStatus());

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseEntity<ExceptionResponseModel> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        final ExceptionResponseModel errorDetail = new ExceptionResponseModel();
        errorDetail.setMessage(e.getLocalizedMessage());
        errorDetail.setStatus(HttpStatus.BAD_REQUEST);
        errorDetail.setTimestamp(errorDateFormat.format(new Date()));
        errorDetail.setTitle("Validation Error");
        return new ResponseEntity<>(errorDetail, null, errorDetail.getStatus());

    }

}
