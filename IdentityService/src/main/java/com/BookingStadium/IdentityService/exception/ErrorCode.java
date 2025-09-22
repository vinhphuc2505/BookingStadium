package com.BookingStadium.IdentityService.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;



@Getter
public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error", HttpStatus.BAD_REQUEST),
    DATA_INTEGRITY_VIOLATION(1001, "Data invalid or existed", HttpStatus.CONFLICT),
    EMAIL_INVALID(1002, "Email invalid", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED(1003, "Email existed", HttpStatus.CONFLICT),
    USER_EXISTED(1004, "User existed", HttpStatus.CONFLICT),
    USER_OR_EMAIL_EXISTED(1005, "User or email existed", HttpStatus.CONFLICT),
    ROLE_NOT_EXISTED(1006, "Role not existed", HttpStatus.NOT_FOUND),
    USER_NOT_EXISTED(1007, "User not existed", HttpStatus.NOT_FOUND),
    USER_OR_EMAIL_NOT_EXISTED(1008, "User or email not existed", HttpStatus.NOT_FOUND),
    PASSWORDS_DO_NOT_MATCH(1009, "Password do not match", HttpStatus.BAD_REQUEST),
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
