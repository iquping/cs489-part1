package edu.miu.cs489.quping.hello.exception;

public class AstronautNotFoundException extends RuntimeException {
    public AstronautNotFoundException(String message) {
        super(message);
    }
}

/*

// Custom Exceptions
package com.space.mission.exception;

public class AstronautNotFoundException extends RuntimeException {
    public AstronautNotFoundException(String message) {
        super(message);
    }
}

package com.space.mission.exception;

public class SatelliteNotFoundException extends RuntimeException {
    public SatelliteNotFoundException(String message) {
        super(message);
    }
}

package com.space.mission.exception;

public class InvalidSatelliteReferenceException extends RuntimeException {
    public InvalidSatelliteReferenceException(String message) {
        super(message);
    }
}

package com.space.mission.exception;

public class SatelliteDecommissionedException extends RuntimeException {
    public SatelliteDecommissionedException(String message) {
        super(message);
    }
}

// API Error Response Model
package com.space.mission.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;

public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public ApiError() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiError(int status, String error, String message, String path) {
        this();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

// Global Exception Handler
package com.space.mission.exception;

import com.space.mission.model.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(InvalidSatelliteReferenceException.class)
    public ResponseEntity<ApiError> handleInvalidSatelliteReferenceException(InvalidSatelliteReferenceException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SatelliteDecommissionedException.class)
    public ResponseEntity<ApiError> handleSatelliteDecommissionedException(SatelliteDecommissionedException ex, HttpServletRequest request) {
        ApiError apiError = new ApiError(
                HttpStatus.FORBIDDEN.value(),
                "Forbidden",
                ex.getMessage(),
                request.getRequestURI()
        );
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
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
 */
