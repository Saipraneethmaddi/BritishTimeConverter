package com.smartbear.assessment.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Handler to handle instances of CustomException.class and provide appropriate API responses
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<?> handleCustomException(CustomException ex) {
        return new ResponseEntity<>(ex.getErrorCode().getMessage(), ex.getErrorCode().getHttpStatus());
    }

}
