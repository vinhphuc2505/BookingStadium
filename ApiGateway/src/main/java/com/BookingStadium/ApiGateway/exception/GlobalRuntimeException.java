package com.BookingStadium.ApiGateway.exception;


import com.BookingStadium.ApiGateway.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;

@ControllerAdvice
public class GlobalRuntimeException{

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlerAccessDeniedException(AccessDeniedException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.IS_NOT_PERMISSION.getErrorCode());
        apiResponse.setMessage(ErrorCode.IS_NOT_PERMISSION.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
    }

    // Xử lý lỗi khi không kết nối được đến service khác
    @ExceptionHandler(value = ConnectException.class)
    ResponseEntity<ApiResponse> handlerConnectException(ConnectException exception){
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.NO_CONNECTION.getErrorCode());
        apiResponse.setMessage(ErrorCode.NO_CONNECTION.getMessage());

        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(apiResponse);
    }

}
