package com.BookingStadium.PaymentService.service.serviceImpl;


import com.BookingStadium.PaymentService.config.MoMoConfig;
import com.BookingStadium.PaymentService.dto.request.momo.CreatePaymentRequest;
import com.BookingStadium.PaymentService.dto.request.momo.MoMoRequest;
import com.BookingStadium.PaymentService.dto.request.momo.MomoIpnRequest;
import com.BookingStadium.PaymentService.dto.response.MoMoResponse;
import com.BookingStadium.PaymentService.entity.Bill;
import com.BookingStadium.PaymentService.enums.BillStatus;
import com.BookingStadium.PaymentService.repository.BillRepository;
import com.BookingStadium.PaymentService.service.MoMoService;
import com.BookingStadium.PaymentService.util.HmacUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
public class MoMoServiceImpl implements MoMoService {
    @Autowired
    private BillRepository billRepository;
    @Autowired
    private MoMoConfig momoConfig;
    @Autowired
    private RestTemplate restTemplate;



    @Override
    public MoMoResponse createPayment(CreatePaymentRequest request) {
        // Tìm hóa đơn theo billId
        System.out.println("Creating payment for Bill ID: " + request.getBillId());
        Bill bill = billRepository.findById(request.getBillId())
                .orElseThrow(() -> new RuntimeException("Bill not existed"));
        // Kiểm tra trạng thái của hóa đơn
        if(bill.getStatus() == BillStatus.PAID){
            throw new RuntimeException("Bill is paid");
        }

        // Tạo yêu cầu thanh toán đến MoMo
        String requestId = UUID.randomUUID().toString();
        String orderId = bill.getBillId().toString();
        long amount = bill.getFinalPrice()
                .setScale(0, RoundingMode.UNNECESSARY)
                .longValueExact();
        String orderInfo = request.getOrderInfo() + bill.getBillId();
        String requestType = "captureWallet";
        String extraData = "";
        String lang = "vi";

        // Xác định URL IPN để sử dụng
        String ipnUrlToUse;
        if (StringUtils.hasText(momoConfig.getIpnUrlNgrok())) {
            // Nếu có cấu hình Ngrok (môi trường dev), ưu tiên dùng nó
            ipnUrlToUse = momoConfig.getIpnUrlNgrok();
        } else {
            // Ngược lại, dùng URL mặc định (localhost hoặc production)
            ipnUrlToUse = momoConfig.getIpnUrl();
        }
        log.info(ipnUrlToUse);

        // Tạo chữ ký
        String rawSignature = "accessKey=" + momoConfig.getAccessKey() +
                "&amount=" + amount +
                "&extraData=" + extraData +
                "&ipnUrl=" + ipnUrlToUse +
                "&orderId=" + orderId +
                "&orderInfo=" + orderInfo +
                "&partnerCode=" + momoConfig.getPartnerCode() +
                "&redirectUrl=" + momoConfig.getRedirectUrl() +
                "&requestId=" + requestId +
                "&requestType=" + requestType;

        // Mã hóa chữ ký sử dụng HMAC SHA256 gửi đến MoMo
        String signature = HmacUtil.hmacSHA256(rawSignature, momoConfig.getSecretKey());


        // Tạo đối tượng MoMoRequest
        MoMoRequest momoRequest = MoMoRequest.builder()
                .partnerCode(momoConfig.getPartnerCode())
                .accessKey(momoConfig.getAccessKey())
                .requestId(requestId)
                .amount(amount)
                .orderId(orderId)
                .orderInfo(orderInfo)
                .redirectUrl(momoConfig.getRedirectUrl())
                .ipnUrl(ipnUrlToUse)
                .requestType(requestType)
                .extraData(extraData)
                .lang(lang)
                .signature(signature)
                .build();

        // Gửi yêu cầu đến MoMo và nhận phản hồi
        MoMoResponse momoResponse = restTemplate.postForObject(
                momoConfig.getEndpoint(),
                momoRequest,
                MoMoResponse.class
        );

        return momoResponse;
    }

    @Override
    public void handleMomoIPN(MomoIpnRequest request) {

        if (!validateIpnSignature(request)) {
            // Nếu chữ ký không hợp lệ -> ném lỗi bảo mật
            System.err.println("CẢNH BÁO BẢO MẬT: Chữ ký IPN không hợp lệ!");
            throw new SecurityException("Invalid IPN Signature");
        }

        String billId = request.getOrderId();
        String transId = request.getTransId();


        if (request.getResultCode() != 0) {
            System.out.println("Giao dịch MoMo thất bại/hủy. orderId: " + billId + ", ResultCode: " + request.getResultCode());
            return;
        }

        Bill bill = billRepository.findById(UUID.fromString(billId))
                .orElseThrow(() -> new RuntimeException("Bill not found"));

        if (bill.getStatus() == BillStatus.PAID) {
            System.out.println("IPN trùng lặp. Hóa đơn đã được thanh toán: " + billId);
            return;
        }

        bill.setStatus(BillStatus.PAID);
        bill.setDatePaid(LocalDateTime.now());

        billRepository.save(bill);

        System.out.println("Hóa đơn MoMo PAID thành công: " + billId);
    }

    public boolean validateIpnSignature(MomoIpnRequest request) {
        // Định dạng Raw Data IPN (sắp xếp theo A-Z)
        // accessKey=$accessKey&amount=$amount&extraData=$extraData&message=$message&orderId=$orderId&orderInfo=$orderInfo&orderType=$orderType&partnerCode=$partnerCode&payType=$payType&requestId=$requestId&responseTime=$responseTime&resultCode=$resultCode&transId=$transId
        String rawSignature = "accessKey=" + momoConfig.getAccessKey() +
                "&amount=" + request.getAmount() +
                "&extraData=" + request.getExtraData() +
                "&message=" + request.getMessage() +
                "&orderId=" + request.getOrderId() +
                "&orderInfo=" + request.getOrderInfo() +
                "&orderType=" + request.getOrderType() +
                "&partnerCode=" + request.getPartnerCode() +
                "&payType=" + request.getPayType() +
                "&requestId=" + request.getRequestId() +
                "&responseTime=" + request.getResponseTime() +
                "&resultCode=" + request.getResultCode() +
                "&transId=" + request.getTransId();

        String signature = HmacUtil.hmacSHA256(rawSignature, momoConfig.getSecretKey());

        // So sánh chữ ký
        return signature.equals(request.getSignature());
    }


}














