package com.BookingStadium.IdentityService.exception;


import com.BookingStadium.IdentityService.dto.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class GlobalRuntimeException {
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handlerAppException(AppException exception){
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse= new ApiResponse();

        apiResponse.setCode(errorCode.getErrorCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    ResponseEntity<ApiResponse> handlerSQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException exception){
        ApiResponse apiResponse= new ApiResponse();

        apiResponse.setCode(ErrorCode.DATA_INTEGRITY_VIOLATION.getErrorCode());
        apiResponse.setMessage(ErrorCode.DATA_INTEGRITY_VIOLATION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handlerUncategorizedException(RuntimeException runtimeException){
        ApiResponse apiResponse= new ApiResponse();

        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getErrorCode());
        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

}
















