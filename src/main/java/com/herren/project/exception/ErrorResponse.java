package com.herren.project.exception;

import java.time.LocalDateTime;
import org.springframework.http.ResponseEntity;

public class ErrorResponse {
    private final LocalDateTime timestamp = LocalDateTime.now();
    private final int status;
    private final String error;
    private final String code;
    private final String message;

    public ErrorResponse(int status, String error, String code, String message) {
        this.status = status;
        this.error = error;
        this.code = code;
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ResponseEntity<ErrorResponse> toResponseEntity(ErrorCode errorCode) {
        ErrorResponse errorResponse = new ErrorResponse(errorCode.getHttpStatus().value(),
                errorCode.getHttpStatus().name(), errorCode.name(), errorCode.getDetail());

        return ResponseEntity.status(errorCode.getHttpStatus()).body(errorResponse);
    }
}
