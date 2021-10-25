package com.example.pathfinderdemo.model.binding;

import com.example.pathfinderdemo.model.entity.Enum.CategoryEnum;
import com.example.pathfinderdemo.model.entity.Enum.LevelEnum;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class RoutAddBindingModel {

    private String name;
    private String description;
    private MultipartFile gpxCoordinates;
    private LevelEnum level;
    private String videoUrl;
    private Set<CategoryEnum> categories;

    public RoutAddBindingModel() {
    }

    public String getName() {
        return name;
    }

    public RoutAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public RoutAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public MultipartFile getGpxCoordinates() {
        return gpxCoordinates;
    }

    public RoutAddBindingModel setGpxCoordinates(MultipartFile gpxCoordinates) {
        this.gpxCoordinates = gpxCoordinates;
        return this;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public RoutAddBindingModel setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    public Set<CategoryEnum> getCategories() {
        return categories;
    }

    public RoutAddBindingModel setCategories(Set<CategoryEnum> categories) {
        this.categories = categories;
        return this;
    }
}
