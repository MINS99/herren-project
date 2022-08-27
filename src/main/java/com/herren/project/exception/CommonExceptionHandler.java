package com.herren.project.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(CommonException.class)
    protected ResponseEntity<ErrorResponse> handleCustomException(CommonException e) {
        logger.error("{} : {}", e.getErrorCode(), e.getErrorCode().getDetail());
        return ErrorResponse.toResponseEntity(e.getErrorCode());
    }
}
