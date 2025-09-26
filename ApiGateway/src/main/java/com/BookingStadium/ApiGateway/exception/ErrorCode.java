package com.BookingStadium.ApiGateway.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    IS_NOT_PERMISSION(1011, "You do not have access", HttpStatus.UNAUTHORIZED),
    NO_CONNECTION(1015, "Unable to connect", HttpStatus.SERVICE_UNAVAILABLE),
    ;

    private final int errorCode;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int errorCode, String message, HttpStatusCode httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatusCode = httpStatus;
    }
}
