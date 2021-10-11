package com.example.pathfinderdemo.model.entity;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "comments")
public class CommentEntity extends BaseEntity {

    private boolean approved;
    private Instant created;
    private String textContent;
    private UserEntity author;
    private RouteEntity route;

    public CommentEntity() {
    }

    @Column(nullable = false)
    public boolean isApproved() {
        return approved;
    }

    public CommentEntity setApproved(boolean approved) {
        this.approved = approved;
        return this;
    }

    @Column(nullable = false)

    public Instant getCreated() {
        return created;
    }

    public CommentEntity setCreated(Instant created) {
        this.created = created;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getTextContent() {
        return textContent;
    }

    public CommentEntity setTextContent(String textContent) {
        this.textContent = textContent;
        return this;
    }

    @ManyToOne
    public UserEntity getAuthor() {
        return author;
    }

    public CommentEntity setAuthor(UserEntity author) {
        this.author = author;
        return this;
    }

    @ManyToOne
    public RouteEntity getRoute() {
        return route;
    }

    public CommentEntity setRoute(RouteEntity route) {
        this.route = route;
        return this;
    }
}
