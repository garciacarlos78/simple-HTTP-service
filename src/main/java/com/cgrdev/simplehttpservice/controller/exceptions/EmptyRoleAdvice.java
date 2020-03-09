package com.cgrdev.simplehttpservice.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class EmptyRoleAdvice {

    // Advice rendered straight into the response body
    @ResponseBody
    // Configures the advice to only respond if an EmployeeNotFoundException is thrown
    @ExceptionHandler(EmptyRoleException.class)
    // Says to issue an HTTP 404
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String emptyRoleHandler(EmptyRoleException ex) {
        // The message of the exception will be the content of the response
        return ex.getMessage();
    }
}