package com.example.examOct.model.view;

public class AttackerViewModel {
    private Long id;
    private String name;

    public AttackerViewModel() {
    }

    public Long getId() {
        return id;
    }

    public AttackerViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public AttackerViewModel setName(String name) {
        this.name = name;
        return this;
    }
}
