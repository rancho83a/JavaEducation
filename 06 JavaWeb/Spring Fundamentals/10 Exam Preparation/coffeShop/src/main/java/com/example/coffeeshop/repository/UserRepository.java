package com.example.coffeeshop.repository;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);

}
