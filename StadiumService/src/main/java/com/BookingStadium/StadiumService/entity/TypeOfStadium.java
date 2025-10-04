package com.BookingStadium.StadiumService.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "type_of_stadium")
public class TypeOfStadium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type_name")
    private String typeName;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Stadium> stadiumList = new ArrayList<>();

}
