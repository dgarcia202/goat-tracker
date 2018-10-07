package com.github.dgarcia202.goattracker.rest.controllers;

import com.github.dgarcia202.goattracker.rest.errors.ValidationError;
import com.github.dgarcia202.goattracker.rest.errors.ValidationErrorDetail;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationError error = new ValidationError();
        for (FieldError e: ex.getBindingResult().getFieldErrors()) {
            error.getDetails().add(new ValidationErrorDetail(e.getField(), e.getRejectedValue(), e.getDefaultMessage()));
        }

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }
}
