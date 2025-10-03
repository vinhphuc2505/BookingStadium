package com.BookingStadium.IdentityService.repository;

import com.BookingStadium.IdentityService.entity.RedisToken;
import org.springframework.data.repository.CrudRepository;

public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
