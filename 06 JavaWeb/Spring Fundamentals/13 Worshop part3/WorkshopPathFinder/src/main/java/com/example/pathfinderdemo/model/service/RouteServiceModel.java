package com.example.pathfinderdemo.model.service;

import com.example.pathfinderdemo.model.entity.CategoryEntity;
import com.example.pathfinderdemo.model.entity.Enum.LevelEnum;
import com.example.pathfinderdemo.model.entity.PictureEntity;
import com.example.pathfinderdemo.model.entity.UserEntity;

import java.util.Set;

public class RouteServiceModel {
    private Long id;
    private String description;
    private String gpxCoordinate;
    private LevelEnum level;
    private String name;
    private String videoUrl;
    private UserEntity author;

    private Set<CategoryEntity> categories;

    private Set<PictureEntity> pictures;

    public RouteServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public RouteServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinate() {
        return gpxCoordinate;
    }

    public RouteServiceModel setGpxCoordinate(String gpxCoordinate) {
        this.gpxCoordinate = gpxCoordinate;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteServiceModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteServiceModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public RouteServiceModel setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public RouteServiceModel setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public RouteServiceModel setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
