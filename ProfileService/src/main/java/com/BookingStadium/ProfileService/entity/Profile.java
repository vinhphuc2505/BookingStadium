package com.BookingStadium.ProfileService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;


@Document(collection = "profiles")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Profile {

    @MongoId(FieldType.STRING)
    private String profileId;

    private String userId;

    private String firstname;

    private String lastname;

    private String phone;

    private String date_of_birth;

    private LocalDateTime dateCreated = LocalDateTime.now();
}
