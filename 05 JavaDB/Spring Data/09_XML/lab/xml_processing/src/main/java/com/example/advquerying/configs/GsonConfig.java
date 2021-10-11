package com.example.advquerying.configs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GsonConfig {

    @Bean
    public GsonBuilder gsonBuilder(){
        return new GsonBuilder()
                .excludeFieldsWithModifiers();
    }
}
