package com.wilson.insurance.configuration;

import com.wilson.insurance.exceptions.ParameterException;
import com.wilson.insurance.exceptions.TargetNotFoundException;
import com.wilson.insurance.exceptions.UnknownPremiumException;
import com.wilson.insurance.models.CommonResponse;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log
public class GlobalExceptionHandler {
    @ExceptionHandler(TargetNotFoundException.class)
    public ResponseEntity<CommonResponse> handleTargetNotFoundException() {
        CommonResponse response = CommonResponse.builder().message("target not existed.").build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ParameterException.class)
    public ResponseEntity<CommonResponse> handleParameterException() {
        CommonResponse response = CommonResponse.builder().message("invalid client parameter").build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnknownPremiumException.class)
    public ResponseEntity<CommonResponse> handleUnknownPremiumException() {
        CommonResponse response = CommonResponse.builder().message("unknown premium").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse> handleException(Exception e) {
        log.severe(e.getMessage());
        e.printStackTrace();
        CommonResponse response = CommonResponse.builder().message("error occurred.").build();
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
