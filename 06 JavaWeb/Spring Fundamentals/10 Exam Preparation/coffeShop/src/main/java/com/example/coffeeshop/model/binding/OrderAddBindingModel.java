package com.example.coffeeshop.model.binding;

import com.example.coffeeshop.model.entity.CategoryEntity;
import com.example.coffeeshop.model.entity.CategoryNameEnum;
import com.example.coffeeshop.model.entity.UserEntity;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderAddBindingModel {
    private String name;
    private BigDecimal price;
    private LocalDateTime orderTime;
    private CategoryNameEnum category;
    private String description;

    public OrderAddBindingModel() {
    }

    @NotNull
    @Size(min = 3, max = 20)
    public String getName() {
        return name;
    }

    public OrderAddBindingModel setName(String name) {
        this.name = name;
        return this;
    }

    @NotNull
    @Positive
    public BigDecimal getPrice() {
        return price;
    }

    public OrderAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    @NotNull
    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderAddBindingModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    @NotNull
    public CategoryNameEnum getCategory() {
        return category;
    }

    public OrderAddBindingModel setCategory(CategoryNameEnum category) {
        this.category = category;
        return this;
    }

    @NotNull
    @Size(min = 5)
    public String getDescription() {
        return description;
    }

    public OrderAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }
}
