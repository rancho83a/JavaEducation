package com.example.football.repository;


import com.example.football.models.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//ToDo:
public interface TownRepository  extends JpaRepository<Town, Long> {
    boolean existsByName(String name);

    Optional<Town> findFirstByName(String name);

}
