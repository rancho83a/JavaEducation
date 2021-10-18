package com.example.pathfinderdemo.Repository;

import com.example.pathfinderdemo.model.entity.PictureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PictureRepository extends JpaRepository<PictureEntity, Long> {

    @Query("SELECT p.url from  PictureEntity p")
    List<String> findAllByUrl();
}
