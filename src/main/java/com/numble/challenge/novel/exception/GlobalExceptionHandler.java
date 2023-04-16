package com.numble.challenge.novel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleShortOfPoint(Exception ex, HttpServletRequest request) {
        ex.printStackTrace();
        System.out.println("hello");
        return new ResponseEntity(new ErrorDTO(400, ex.getMessage(), request.getRequestURI()) , HttpStatus.BAD_REQUEST);
    }

}
