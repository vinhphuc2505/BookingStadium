package com.BookingStadium.StadiumService.repository;

import com.BookingStadium.StadiumService.entity.TypeOfStadium;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<TypeOfStadium, Integer> {



}
