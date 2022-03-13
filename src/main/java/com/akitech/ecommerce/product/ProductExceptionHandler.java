package com.akitech.ecommerce.product;

import com.akitech.ecommerce.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(NoProductFound.class)
    public ResponseEntity<ErrorResponse> handleNoProductFound(NoProductFound ex) {
        ErrorResponse apiError = new ErrorResponse(HttpStatus.NO_CONTENT, ex.getMessage());
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
