package com.BookingStadium.BookingService.entity;


import com.BookingStadium.BookingService.enums.BookingStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stadium_booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stadium_booking_id")
    private UUID bookingId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "location_id")
    private UUID locationId;

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @Column(name = "status")
    private BookingStatus bookingStatus;

    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "booking", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<BookingDetails> bookingDetails = new ArrayList<>();
}













