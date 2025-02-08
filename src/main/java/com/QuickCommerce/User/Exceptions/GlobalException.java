package com.QuickCommerce.User.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class GlobalException {
    @ExceptionHandler
    public ResponseEntity<String> notFoundException(NotFoundException notFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(notFoundException.getMessage());
    }
    @ExceptionHandler
    public ResponseEntity<String> invalidArgException(InvalidArgException invalidArgException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                             .body(invalidArgException.getMessage());
    }
}
