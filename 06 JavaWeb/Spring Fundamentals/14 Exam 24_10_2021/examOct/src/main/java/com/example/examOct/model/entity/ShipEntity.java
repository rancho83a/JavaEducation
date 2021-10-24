package com.example.examOct.model.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ships")
public class ShipEntity extends BaseEntity {
    private String name;
    private Long health;
    private Long power;
    private LocalDate created;
    private CategoryEntity category;
    private UserEntity user;

    public ShipEntity() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public ShipEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public Long getHealth() {
        return health;
    }

    public ShipEntity setHealth(Long health) {
        this.health = health;
        return this;
    }

    @Column(nullable = false)
    public Long getPower() {
        return power;
    }

    public ShipEntity setPower(Long power) {
        this.power = power;
        return this;
    }

    @Column(nullable = false)
    public LocalDate getCreated() {
        return created;
    }

    public ShipEntity setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    @ManyToOne
    public CategoryEntity getCategory() {
        return category;
    }

    public ShipEntity setCategory(CategoryEntity category) {
        this.category = category;
        return this;
    }
@ManyToOne
    public UserEntity getUser() {
        return user;
    }

    public ShipEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }
}
