package com.BookingStadium.StadiumService.entity;


import com.BookingStadium.StadiumService.enums.StadiumStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "stadium")
public class Stadium {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "stadium_id")
    private UUID stadiumId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    @JsonBackReference
    private StadiumLocation location;

    @ManyToOne
    @JoinColumn(name = "type_id", nullable = false)
    @JsonBackReference
    private TypeOfStadium type;

    @Column(name = "stadium_name")
    private String stadiumName;

    @Column(name = "price")
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StadiumStatus status = StadiumStatus.AVAILABLE;

    @Column(name = "date_created")
    @CreationTimestamp
    private LocalDate dateCreated = LocalDate.now();

    @Column(name = "description")
    private String description;

}





