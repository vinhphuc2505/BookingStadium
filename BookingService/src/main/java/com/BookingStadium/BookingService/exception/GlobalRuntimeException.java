package com.BookingStadium.BookingService.exception;



import com.BookingStadium.BookingService.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


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

}
