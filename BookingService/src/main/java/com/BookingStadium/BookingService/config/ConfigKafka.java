package com.BookingStadium.BookingService.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;

@Configuration
@Slf4j
public class ConfigKafka {
    @Bean
    public ApplicationRunner kafkaListenerVerifier(KafkaListenerEndpointRegistry registry) {
        return args -> {
            registry.getListenerContainers().forEach(c ->
                    log.info("🛰️ Kafka listener active: {}", c.getListenerId())
            );
        };
    }
}
