package com.example.books.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ApplicationConfig {


    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
