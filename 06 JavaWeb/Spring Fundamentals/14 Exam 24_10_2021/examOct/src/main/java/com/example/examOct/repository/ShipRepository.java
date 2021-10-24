package com.example.examOct.repository;

import com.example.examOct.model.entity.ShipEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShipRepository extends JpaRepository<ShipEntity, Long> {
    List<ShipEntity> findAll();

    List<ShipEntity> findByUserId(Long user_id);
    List<ShipEntity> findByUserIdNot(Long user_id);
}
