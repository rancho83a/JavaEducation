package com.example.pathfinderdemo.model.entity;

import com.example.pathfinderdemo.model.entity.enums.LevelEnum;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "users")
public class UserEntity  extends BaseEntity{
    private String fullName;
    private String password;
    private String username;
    private LevelEnum level;
    private Integer age;
    private String email;
    private Set<RoleEntity> roles;

    public UserEntity() {
    }

    @Column()
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public UserEntity setAge(Integer age) {
        this.age = age;
        return this;
    }

    @Column(nullable = false)
    public String getFullName() {
        return fullName;
    }

    public UserEntity setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    @Column(unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String email) {
        this.username = email;
        return this;
    }
@Enumerated(EnumType.STRING)
    public LevelEnum getLevel() {
        return level;
    }

    public UserEntity setLevel(LevelEnum level) {
        this.level = level;
        return this;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public UserEntity setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
        return this;
    }
}
