package com.example.examOct.model.binding;

import com.example.examOct.model.entity.CategoryNameEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class ShipAddBindingModel {
    private String name;
    private Long power;
    private Long health;
       private LocalDate created;
    private CategoryNameEnum category;


    public ShipAddBindingModel() {
    }

    @NotBlank
    @Size(min = 2, max = 10, message = "Username length must be between 2 and 10 characters.")
    public String getName() {
        return name;
    }

    public ShipAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Positive(message = "The power must be positive.")
    public Long getPower() {
        return power;
    }

    public ShipAddBindingModel setPower(Long power) {
        this.power = power;
        return this;
    }
    @NotNull
    @Positive(message = "The health must be positive.")
    public Long getHealth() {
        return health;
    }

    public ShipAddBindingModel setHealth(Long health) {
        this.health = health;
        return this;
    }
    @NotNull
    @PastOrPresent(message = "Created date can not be in the future.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getCreated() {
        return created;
    }

    public ShipAddBindingModel setCreated(LocalDate created) {
        this.created = created;
        return this;
    }
    @NotNull(message = "You must select the category.")
    public CategoryNameEnum getCategory() {
        return category;
    }

    public ShipAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }
}
