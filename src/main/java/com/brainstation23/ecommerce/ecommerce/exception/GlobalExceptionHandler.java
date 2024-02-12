package com.brainstation23.ecommerce.ecommerce.exception;

import com.brainstation23.ecommerce.ecommerce.exception.custom.AlreadyExistException;
import com.brainstation23.ecommerce.ecommerce.exception.custom.JwtException;
import com.brainstation23.ecommerce.ecommerce.exception.custom.NotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler implements AuthenticationEntryPoint {
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

    @ExceptionHandler({JwtException.class})
    public ResponseEntity<Object> handleJwtException(JwtException exception) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    public void commence(HttpServletRequest request
            , HttpServletResponse response
            , AuthenticationException authException) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        final Map<String, Object> body = new HashMap<>();
        body.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        body.put("error", "Unauthorized");
        body.put("message", authException.getMessage());
        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}
