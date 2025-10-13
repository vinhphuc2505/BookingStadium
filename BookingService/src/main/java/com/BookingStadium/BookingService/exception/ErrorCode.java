package com.BookingStadium.StadiumService.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    TYPE_NOT_EXISTED(1001, "Type not existed", HttpStatus.NOT_FOUND),
    TYPE_EXISTED(1002, "Type existed", HttpStatus.BAD_REQUEST),
    LOCATION_NOT_EXISTED(1004, "Location not existed", HttpStatus.NOT_FOUND),
    STADIUM_NOT_EXISTED(1003, "Stadium not existed", HttpStatus.NOT_FOUND),
    WORK_SCHEDULE_NOT_EXISTED(1005, "Work schedule not existed", HttpStatus.NOT_FOUND),
    KAFKA_SEND_ERROR(1006, "Failed to send", HttpStatus.INTERNAL_SERVER_ERROR),
    KAFKA_RECEIVE_ERROR(1007, "Failed to receive", HttpStatus.INTERNAL_SERVER_ERROR),
    USER_NOT_EXISTED(1008, "User not existed", HttpStatus.NOT_FOUND),
    KEY_INVALID(1010, "Key invalid", HttpStatus.NOT_FOUND),
    IS_NOT_PERMISSION(1011, "You do not have access", HttpStatus.UNAUTHORIZED),

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
