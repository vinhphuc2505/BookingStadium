package com.BookingStadium.IdentityService.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@RedisHash("redisHas")
public class RedisToken {
    @Id
    private String jwtId;

    @TimeToLive(unit = TimeUnit.HOURS)
    private Long expiredTime;
}
