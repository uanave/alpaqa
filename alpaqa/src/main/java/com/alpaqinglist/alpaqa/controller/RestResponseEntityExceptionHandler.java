package com.alpaqinglist.alpaqa.controller;


import com.alpaqinglist.alpaqa.data.error.ApiError;
import com.alpaqinglist.alpaqa.data.error.ApiSubError;
import com.alpaqinglist.alpaqa.data.error.ApiValidationError;
import com.alpaqinglist.alpaqa.exception.EntityNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    @NonNull
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        List<ApiSubError> errorList = e.getBindingResult().getFieldErrors().stream()
                .map(this::createApiSubError)
                .collect(Collectors.toList());

        String error = "There was an error while trying to validate the JSON-Data coming in from an API Request";

        return buildResponseEntity(new ApiError(HttpStatus.CONFLICT, error, e, errorList));
    }

    @Override
    @NonNull
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            @NonNull HttpMessageNotReadableException ex,
            @NonNull HttpHeaders headers,
            @NonNull HttpStatus status,
            @NonNull WebRequest request) {

        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, "Http input not readable", ex), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<Object> entityNotFoundHandler(EntityNotFoundException e) {
        String error = "Entity Error";
        return new ResponseEntity<>(new ApiError(HttpStatus.BAD_REQUEST, error, e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<String> invalidFormatExceptionHandler(InvalidFormatException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private ApiSubError createApiSubError(FieldError f) {
        return new ApiValidationError(f.getObjectName(), f.getField(), f.getRejectedValue(), f.getDefaultMessage());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
