package com.supercode.exception;

import javax.servlet.http.HttpServletRequest;

import com.supercode.controller.AppController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice(basePackageClasses = AppController.class)
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    @ResponseBody
    public ResponseEntity<String> genericHandler(HttpServletRequest request, ApiException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}