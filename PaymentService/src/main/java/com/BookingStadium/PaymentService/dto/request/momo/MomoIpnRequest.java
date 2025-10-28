package com.BookingStadium.PaymentService.dto.request.momo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MomoIpnRequest {
    // Thông tin định danh đối tác
    private String partnerCode;
    private String accessKey;
    private String requestId;

    // Thông tin giao dịch
    private String orderId; // Mã đơn hàng của đối tác (Bill ID)
    private String orderInfo;
    private String orderType;

    // Số tiền (MoMo gửi về kiểu số Long)
    private Long amount;

    // Kết quả giao dịch
    private String message;
    private Integer resultCode; // 0 = Thành công, khác 0 = Thất bại/Hủy

    // Chi tiết thanh toán
    private String transId; // Mã giao dịch duy nhất của MoMo
    private Long responseTime;
    private String payType; // Loại thanh toán (web, app, token)
    private String extraData;

    // Chữ ký bảo mật
    private String signature;
}
