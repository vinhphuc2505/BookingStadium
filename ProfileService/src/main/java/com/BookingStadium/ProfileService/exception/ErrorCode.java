package com.BookingStadium.ProfileService.exception;


import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {


    ;
    private final int errorCode;
    private final String message;
    private final HttpStatusCode httpStatusCode;

    ErrorCode(int errorCode, String message, HttpStatusCode httpStatusCode) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatusCode = httpStatusCode;
    }

}
