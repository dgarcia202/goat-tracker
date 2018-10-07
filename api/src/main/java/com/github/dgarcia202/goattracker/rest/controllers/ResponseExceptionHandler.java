package com.github.dgarcia202.goattracker.rest.controllers;

import com.github.dgarcia202.goattracker.exceptions.MissingFoundationDataException;
import com.github.dgarcia202.goattracker.exceptions.NotFoundException;
import com.github.dgarcia202.goattracker.rest.errors.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError e: ex.getBindingResult().getFieldErrors()) {
            error.getDetails().add(new ValidationErrorDetail(e.getField(), e.getRejectedValue(), e.getDefaultMessage()));
        }

        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        return new ResponseEntity(
                new ApiErrorResponse(400, "Request data is either is malformed or does not have the expected structure."), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> notFoundErrorhandler() {
        return new ResponseEntity(
                new ApiErrorResponse(404, "Not found."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MissingFoundationDataException.class)
    public ResponseEntity<Object> missingFoundationDataErrorHandler() {
        return new ResponseEntity(
                new ApiErrorResponse(505, "Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<Object> internalServerErrorHandler() {
        return new ResponseEntity(
                new ApiErrorResponse(500, "Internal server error."), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
