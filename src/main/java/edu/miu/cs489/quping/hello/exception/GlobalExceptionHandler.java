package edu.miu.cs489.quping.hello.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ping Qu
 * @date 4/21/25
 * @description
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AstronautNotFoundException.class)
    public ResponseEntity<ApiError> handleAstronautNotFoundException(AstronautNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SatelliteDecommissionedException.class)
    public ResponseEntity<ApiError> handleSatelliteDecommissionedException(SatelliteDecommissionedException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Satellite Decommissioned",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SatelliteNotFoundException.class)
    public ResponseEntity<ApiError> handleSatelliteNotFoundException(SatelliteNotFoundException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Error",
                "Validation failed for one or more fields: " + errors,
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleAllUncaughtExceptions(Exception ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
