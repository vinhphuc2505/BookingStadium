package com.BookingStadium.PaymentService.controller;


import com.BookingStadium.PaymentService.dto.request.momo.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.momo.MomoIpnRequest;
import com.BookingStadium.PaymentService.dto.response.MoMoResponse;
import com.BookingStadium.PaymentService.dto.response.ApiResponse;
import com.BookingStadium.PaymentService.service.MoMoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/momo")
public class MoMoController {
    @Autowired
    private MoMoService momoService;


    @PostMapping("/create-payment")
    public ApiResponse<MoMoResponse> createPayment(@RequestBody CreatePaymentRequest request) {
        return ApiResponse.<MoMoResponse>builder()
                .code(1000)
                .result(momoService.createPayment(request))
                .build();
    }

    @PostMapping("/ipn")
    public ResponseEntity<Void> handleMomoIPN(@RequestBody MomoIpnRequest request) {
        try {
            // Gọi service (đã bao gồm validate signature)
            momoService.handleMomoIPN(request);

            // Nếu thành công, trả về 204 No Content
            // Đây là tín hiệu cho MoMo biết "Đã nhận OK, đừng gửi lại"
            return ResponseEntity.noContent().build();

        } catch (SecurityException e) {
            // Lỗi chữ ký không hợp lệ
            System.err.println("Lỗi Controller: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400

        } catch (RuntimeException e) {
            // Lỗi nghiệp vụ (ví dụ: Bill not found)
            System.err.println("Lỗi Controller: " + e.getMessage());
            // Trả về 500, MoMo có thể sẽ thử gửi lại IPN
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500
        }
    }



}
