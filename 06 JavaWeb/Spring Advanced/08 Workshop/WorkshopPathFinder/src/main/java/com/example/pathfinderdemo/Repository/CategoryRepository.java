package com.example.pathfinderdemo.Repository;

import com.example.pathfinderdemo.model.entity.CategoryEntity;
import com.example.pathfinderdemo.model.entity.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(CategoryEnum categoryEnum);
}
