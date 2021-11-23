package com.example.pathfinderdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

//@Component
public class Temp implements CommandLineRunner {
    private final PasswordEncoder encoder;

    public Temp(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println(encoder.encode("test"));
        System.out.println(encoder.encode("test"));
    }
}
