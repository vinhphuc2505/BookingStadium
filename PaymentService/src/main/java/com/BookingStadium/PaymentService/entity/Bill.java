package com.BookingStadium.PaymentService.entity;


import com.BookingStadium.PaymentService.enums.BillStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bill")
public class Bill {
    @Id
    @Column(name = "bill_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID billId;

    @Column(name = "stadium_booking_id")
    private UUID bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payment_method_id", nullable = true)
    @JsonBackReference
    private PaymentMethod paymentMethod;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "final_price")
    private BigDecimal finalPrice;

    @Column(name = "status")
    private BillStatus status;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDateTime dateCreated = LocalDateTime.now();

    @Column(name = "date_paid")
    private LocalDateTime datePaid;

}




