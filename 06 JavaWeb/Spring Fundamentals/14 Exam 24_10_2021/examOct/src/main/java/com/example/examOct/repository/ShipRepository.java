package com.example.examOct.repository;

import com.example.examOct.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    List<ShipEntity> findAllByOrderById();

    List<ShipEntity> findByUserId(Long user_id);
    List<ShipEntity> findByUserIdNot(Long user_id);


    Optional<ShipEntity> findById(Long id);
}
