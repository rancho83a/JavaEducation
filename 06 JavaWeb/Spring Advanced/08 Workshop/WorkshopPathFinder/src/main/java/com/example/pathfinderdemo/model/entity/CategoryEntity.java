package com.example.pathfinderdemo.model.entity;

import com.example.pathfinderdemo.model.entity.enums.CategoryEnum;

import javax.persistence.*;

@Entity
@Table(name = "categories")
public class CategoryEntity extends BaseEntity {
    private CategoryEnum name;
    private String description;


    public CategoryEntity() {
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    public CategoryEnum getName() {
        return name;
    }

    public CategoryEntity setName(CategoryEnum name) {
        this.name = name;
        return this;
    }
@Column(columnDefinition = "TEXT")
    public String getDescription() {
        return description;
    }

    public CategoryEntity setDescription(String description) {
        this.description = description;
        return this;
    }
}
