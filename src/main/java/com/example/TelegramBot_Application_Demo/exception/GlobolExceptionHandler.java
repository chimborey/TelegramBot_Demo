package com.example.TelegramBot_Application_Demo.exception;

import org.apache.http.MethodNotSupportedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobolExceptionHandler {

//    check 404
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Map<String, Object>>resourceNotFound(ResourceNotFound resourceNotFound){
        Map<String, Object>errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Not found 404!!!");
        errorResponse.put("message", resourceNotFound.getMessage().toLowerCase());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

//    check 500
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>>runtimeException(RuntimeException runtimeException){
        Map<String, Object>errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.INTERNAL_SERVER_ERROR);
        errorResponse.put("error", "Internal Server Error 500!!!");
        errorResponse.put("message", runtimeException.getMessage().toLowerCase());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

//    check 405
    @ExceptionHandler(MethodNotSupportedException.class)
    public ResponseEntity<Map<String, Object>>methodNotSupportedException(MethodNotSupportedException methodNotSupportedException){
        Map<String, Object>errorResponse = new HashMap<>();
        errorResponse.put("timestamp", LocalDateTime.now());
        errorResponse.put("status", HttpStatus.CONFLICT.value());
        errorResponse.put("error", "Method Not Found!!!");
        errorResponse.put("message", methodNotSupportedException.getMessage().toLowerCase());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

//    check 403
}
