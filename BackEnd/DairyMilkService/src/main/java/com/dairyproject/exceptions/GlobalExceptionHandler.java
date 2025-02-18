package com.dairyproject.exceptions;

import ch.qos.logback.core.spi.ErrorCodes;
import com.dairyproject.dto.ErrorDto;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(ConstraintViolationException constraintViolationException){
        ErrorDto errorDto = ErrorDto.builder()
                .errorMsg(constraintViolationException.getConstraintViolations().toString())
                .statusCOde("400")
                .build();
        return new ResponseEntity<ErrorDto>(errorDto, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> handleConstraintViolationException(Exception exception){
        ErrorDto errorDto = ErrorDto.builder()
                .errorMsg(exception.getMessage())
                .statusCOde("500")
                .build();
        return new ResponseEntity<ErrorDto>(errorDto, HttpStatusCode.valueOf(500));
    }
}
