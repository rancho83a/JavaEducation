package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


//ToDo:
public interface PlayerRepository extends JpaRepository<Player, Long> {

    boolean existsByEmail(String email);



    @Query("select p from Player p where p.birthDate > :afterDate AND p.birthDate < :beforeDate " +
            "ORDER BY p.stat.shooting DESC, p.stat.passing DESC, p.stat.endurance DESC, p.lastName" )
    List<Player> findAllByBirthDateAndOrderThem(
            @Param("afterDate") LocalDate afterDate,
            @Param("beforeDate") LocalDate beforeDate
            );
    //List<Player> findAllByBirthDateAndOrderThem(LocalDate d1, LocalDate d2);
}
