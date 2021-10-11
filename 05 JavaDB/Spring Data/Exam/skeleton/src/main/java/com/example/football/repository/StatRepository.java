package com.example.football.repository;

import com.example.football.models.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//ToDo:
public interface StatRepository extends JpaRepository<Stat,Long> {

    boolean existsByPassingAndShootingAndEndurance(float passing, float shooting, float endurance);
    Optional<Stat> findFirstById(Long id);
}
