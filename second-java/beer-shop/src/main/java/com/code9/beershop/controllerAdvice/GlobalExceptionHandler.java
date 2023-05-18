package com.code9.beershop.controllerAdvice;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller advice to handle global exceptions.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the exception thrown when method arguments fail validation.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return A ResponseEntity containing the error messages.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<String> errorMessages = new ArrayList<>();
        for (FieldError error : bindingResult.getFieldErrors()) {
            errorMessages.add(error.getField() + ": " + error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(errorMessages);
    }
}
