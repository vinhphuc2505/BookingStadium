package com.BookingStadium.BookingService.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stadium_booking_details")
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stadium_booking_details_id")
    private UUID bookingDetailsId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stadium_booking_id", nullable = false)
    @JsonBackReference
    private Booking booking;

    @Column(name = "stadium_id")
    private UUID stadiumId;

    @Column(name = "date_of_booking")
    private LocalDate dateOfBooking;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Column(name = "total_hours")
    private BigDecimal totalHours;

    @Column(name = "price")
    private BigDecimal price;
}




