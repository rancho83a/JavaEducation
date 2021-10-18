package com.example.pathfinderdemo.model.entity;

import com.example.pathfinderdemo.model.entity.Enum.LevelEnum;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "routes")
public class RouteEntity extends BaseEntity {

    private String description;
    private String gpxCoordinate;
    private LevelEnum level;
    private String name;
    private String videoUrl;
    private UserEntity author;

    private Set<CategoryEntity> categories;

    private Set<PictureEntity> pictures;

    public RouteEntity() {
    }

    @OneToMany(mappedBy = "route")
    public Set<PictureEntity> getPictures() {
        return pictures;
    }

    public RouteEntity setPictures(Set<PictureEntity> pictures) {
        this.pictures = pictures;
        return this;
    }

    @ManyToMany
    public Set<CategoryEntity> getCategories() {
        return categories;
    }

    public RouteEntity setCategories(Set<CategoryEntity> categories) {
        this.categories = categories;
        return this;
    }

    @Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public RouteEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    @Column(columnDefinition = "LONGTEXT", name = "gpx_coordinates")
    public String getGpxCoordinate() {
        return gpxCoordinate;
    }

    public RouteEntity setGpxCoordinate(String gpxCoordinate) {
        this.gpxCoordinate = gpxCoordinate;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public RouteEntity setLevel(LevelEnum levelEnum) {
        this.level = levelEnum;
        return this;
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public RouteEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public RouteEntity setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public RouteEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }
}
