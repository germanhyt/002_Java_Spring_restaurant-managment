package com.ironman.restaurantmanagment.expose.controller.exception;



import com.ironman.restaurantmanagment.shared.exception.model.ArgumentNotValidError;
import com.ironman.restaurantmanagment.shared.exception.model.GeneralError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

// Spring stereotype annotation that is used to define a global exception handler for the application
@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ArgumentNotValidError> handleInvalidArgument(MethodArgumentNotValidException exception) {
        // Variables
        ArgumentNotValidError response = new ArgumentNotValidError();
        Map<String, String> error = new HashMap<>();

        // Process
        exception.getBindingResult()
                .getFieldErrors()
                .forEach(fieldError -> error.put(fieldError.getField(), fieldError.getDefaultMessage()));

        response.setMessage("Invalid Arguments");
        response.setError(error);

        // Result
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(response);
    }



}
