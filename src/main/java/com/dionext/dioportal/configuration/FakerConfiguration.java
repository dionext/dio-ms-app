package com.dionext.dioportal.configuration;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakerConfiguration {

    @Bean
    public Faker faker() {
        return new Faker();
    }
}
