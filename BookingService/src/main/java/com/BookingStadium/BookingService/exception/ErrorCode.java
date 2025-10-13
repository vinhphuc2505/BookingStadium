package com.BookingStadium.BookingService.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    BOOKING_NOT_FOUND(1001, "Booking not found", HttpStatus.NOT_FOUND),
    BOOKING_DETAILS_NOT_FOUND(1002, "Booking details not found", HttpStatus.NOT_FOUND),
    TIME_INVALID(1003, "Time invalid", HttpStatus.BAD_REQUEST),
    KAFKA_SEND_ERROR(1004, "Kafka send message error", HttpStatus.INTERNAL_SERVER_ERROR),
    KAFKA_RECEIVE_ERROR(1005, "Kafka receive message error", HttpStatus.INTERNAL_SERVER_ERROR),
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
