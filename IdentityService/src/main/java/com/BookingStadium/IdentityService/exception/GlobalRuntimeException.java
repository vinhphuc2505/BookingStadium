package com.BookingStadium.IdentityService.exception;


import com.BookingStadium.IdentityService.dto.response.ApiResponse;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.net.ConnectException;
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

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handlerValidation(MethodArgumentNotValidException exception){

        String enumKey = exception.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.KEY_INVALID;
        //Bắt lỗi khi nhập sai key
        try{
            errorCode = errorCode.valueOf(enumKey);
        }catch (IllegalArgumentException e){}

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getErrorCode());
        apiResponse.setMessage((errorCode.getMessage()));

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handlerAccessDeniedException(AccessDeniedException exception) {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(ErrorCode.IS_NOT_PERMISSION.getErrorCode());
        apiResponse.setMessage(ErrorCode.IS_NOT_PERMISSION.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(apiResponse);
    }

    // Xử lý lỗi khi không kết nối được đến service khác
//    @ExceptionHandler(value = ConnectException.class)
//    ResponseEntity<ApiResponse> handlerConnectException(ConnectException exception){
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(ErrorCode.NO_CONNECTION.getErrorCode());
//        apiResponse.setMessage(ErrorCode.NO_CONNECTION.getMessage());
//
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(apiResponse);
//    }
//    // Xử lý lỗi từ Feign Client
//    @ExceptionHandler(value = FeignException.class)
//    ResponseEntity<ApiResponse> handlerFeignException(FeignException exception){
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(ErrorCode.NO_CONNECTION.getErrorCode());
//        apiResponse.setMessage(ErrorCode.NO_CONNECTION.getMessage());
//
//        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(apiResponse);
//    }


//    @ExceptionHandler(value = RuntimeException.class)
//    ResponseEntity<ApiResponse> handlerUncategorizedException(RuntimeException exception){
//        ApiResponse apiResponse= new ApiResponse();
//
//        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getErrorCode());
//        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

}
















