package com.example.aop.sla;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverController {

    @GetMapping("/drivers")
    public ResponseEntity<List<Driver>> allDrivers() {

        return ResponseEntity.ok(
                List.of(
                        new Driver("Pesho", "C"),
                        new Driver("Anna", "A")
                )
        );
    }
}
