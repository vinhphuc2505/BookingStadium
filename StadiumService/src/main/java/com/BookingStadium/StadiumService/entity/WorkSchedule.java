package com.BookingStadium.StadiumService.entity;


import com.BookingStadium.StadiumService.enums.DayOfWeek;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalTime;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_schedule")
public class WorkSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "work_schedule_id")
    private UUID workSchedule_id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false, unique = true)
    private StadiumLocation location;

    @Column(name = "day_of_the_week", columnDefinition = "varchar(50)[]")
    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.ARRAY)
    private DayOfWeek[] dayOfWeeks;

    @Column(name = "opening_time")
    private LocalTime openTime;

    @Column(name = "closing_time")
    private LocalTime closeTime;

    @Column(name = "note")
    private String note;

}






