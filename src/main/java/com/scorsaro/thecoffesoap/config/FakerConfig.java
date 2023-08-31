package com.scorsaro.thecoffesoap.config;

import net.datafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfig {

    @Bean
    Faker faker() {
        return new Faker();
    }
}
