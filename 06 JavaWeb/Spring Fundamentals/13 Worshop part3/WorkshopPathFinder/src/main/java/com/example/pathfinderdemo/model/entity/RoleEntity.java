package com.example.pathfinderdemo.model.entity;

import com.example.pathfinderdemo.model.entity.enums.RoleEnum;

import javax.persistence.*;

@Entity
@Table(name="roles")
public class RoleEntity extends BaseEntity{

    private RoleEnum role;


    public RoleEntity() {
    }

    @Enumerated(EnumType.STRING)
    public RoleEnum getRole() {
        return role;
    }

    public RoleEntity setRole(RoleEnum name) {
        this.role = name;
        return this;
    }


}
