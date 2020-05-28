package com.alpaqinglist.alpaqa.controller;

import com.alpaqinglist.alpaqa.exception.BackpackNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends
        ResponseEntityExceptionHandler {
/*
*** ~~~~~~~~~~~~~~~ ***
    ExceptionHandler for ALL endpoints!
*** ~~~~~~~~~~~~~~~ ***
 */

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException e,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        List<String> errorList = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::toString)
                .collect(Collectors.toList());
        return new ResponseEntity<>(errorList, HttpStatus.CONFLICT);

    }
/*
Template for ExceptionHandling
applies to all endpoints
    @ExceptionHandler({ExceptionName.class})
    public ResponseEntity<String> methodName(ExceptionName ex) {

        return new ResponseEntity<>(body, HttpStatus);
    }

 */


}
