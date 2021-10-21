package com.example.coffeeshop.model.view;

public class UserViewModel {
    private Long id;
    private String username;
    private Integer ordersCount;

    public UserViewModel() {
    }

    public Integer getOrdersCount() {
        return ordersCount;
    }

    public UserViewModel setOrdersCount(Integer ordersCount) {
        this.ordersCount = ordersCount;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserViewModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
