package com.example.examOct.repository;

import com.example.examOct.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsernameAndPassword(String username, String password);


    Optional<UserEntity> findByUsernameIgnoreCase(String username);

}
