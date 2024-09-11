package br.com.localweb.app.controllers.exceptions;

import br.com.localweb.app.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest http) {
        StandardError err = new StandardError();
        String message = e.getMessage();
        String error = "Resource not found";
        HttpStatus statusCode = HttpStatus.NOT_FOUND;

        err.setError(error);
        err.setMessage(message);
        err.setPath(http.getRequestURI());
        err.setStatus(statusCode.value());

        return ResponseEntity.status(statusCode).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StandardError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest http) {
         Map<String, String> errors = new HashMap<>();

         e.getBindingResult().getAllErrors().forEach(objectError -> {
            String fieldName = ((FieldError) objectError).getField();
            String messageError = objectError.getDefaultMessage();
            errors.put(fieldName, messageError);
        });

         StandardError err = new StandardError();
         err.setStatus(HttpStatus.BAD_REQUEST.value());
         err.setTimestamp(LocalDateTime.now());
         err.setPath(http.getRequestURI());
         err.setErrors(errors);
         err.setError("Validation error");

         return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(err);
    }
}
