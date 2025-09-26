package com.BookingStadium.ProfileService.repository;


import com.BookingStadium.ProfileService.entity.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends MongoRepository<Profile, String> {
    boolean existsByUserId(String id);

    Optional<Profile> findByUserId(String id);

    void deleteByUserId(String id);
}
