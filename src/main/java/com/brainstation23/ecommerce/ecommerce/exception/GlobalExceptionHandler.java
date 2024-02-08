package com.brainstation23.ecommerce.ecommerce.exception;

import com.brainstation23.ecommerce.ecommerce.exception.custom.AlreadyExistException;
import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Object> handleStudentNotFoundException(NotFoundException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }
    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<Object> handleStudentAlreadyExistsException(AlreadyExistException exception) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(exception.getMessage());
    }


    /*

    **********  PLEASE DO NOT Handle RUNTIME EXCEPTION in Development phase    *************

     */
//    @ExceptionHandler({RuntimeException.class})
//    public ResponseEntity<Object> handleRuntimeException(RuntimeException exception) {
//        return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(exception.getMessage());
//    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleEntityValidationException(ConstraintViolationException exception) {
        List<String> errors = exception
                .getConstraintViolations()
                .stream()
                .map(ConstraintViolation::getMessageTemplate).toList();
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}
