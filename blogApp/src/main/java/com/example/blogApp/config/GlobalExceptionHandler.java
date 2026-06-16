package com.example.blogApp.config;

import com.example.blogApp.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException e) {
        if (e.getMessage() != null && e.getMessage().contains("Unauthorized")) {
            return ResponseEntity.status(403)
                    .body(new ErrorResponse(403, e.getMessage(), LocalDateTime.now()));
        }
        return ResponseEntity.status(404)
                .body(new ErrorResponse(404, e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity.status(500)
                .body(new ErrorResponse(500, "Internal server error", LocalDateTime.now()));
    }
}