package com.example.pathfinderdemo.model.view;

import com.example.pathfinderdemo.model.entity.PictureEntity;
import com.example.pathfinderdemo.model.entity.enums.LevelEnum;

import java.util.Set;

public class RouteDetailsViewModel {
    private Long id;

    private String description;
    private String gpxCoordinate;
    private LevelEnum level;
    private String name;
    private String videoUrl;
    private String author;
    private Set<PictureEntity> pictures;

    public RouteDetailsViewModel() {
    }

    public Long getId() {
        return id;
    }

    public RouteDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public RouteDetailsViewModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RouteDetailsViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGpxCoordinate() {
        return gpxCoordinate;
    }

    public RouteDetailsViewModel setGpxCoordinate(String gpxCoordinate) {
        this.gpxCoordinate = gpxCoordinate;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RouteDetailsViewModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public String getName() {
        return name;
    }

    public RouteDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteDetailsViewModel setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public RouteDetailsViewModel setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }
}
