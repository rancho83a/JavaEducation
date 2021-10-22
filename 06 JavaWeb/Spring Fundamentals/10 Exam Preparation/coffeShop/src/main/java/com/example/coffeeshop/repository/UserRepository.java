package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM UserEntity u ORDER BY size(u.orders)  DESC")
    List<UserEntity> findAllByOrdersByDesc();
    Optional<UserEntity> findByUsernameIgnoreCase(String username);

}
