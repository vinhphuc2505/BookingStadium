package com.BookingStadium.PaymentService.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id")
    private int paymentMethodId;

    @Column(name = "payment_method_name")
    private String paymentMethodName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "paymentMethod", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Bill> bills = new ArrayList<>();
}
