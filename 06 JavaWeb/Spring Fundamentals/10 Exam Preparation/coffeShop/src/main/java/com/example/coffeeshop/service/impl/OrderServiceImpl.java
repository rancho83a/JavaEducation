package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.OrderEntity;
import com.example.coffeeshop.model.service.OrderServiceModel;
import com.example.coffeeshop.repository.OrderRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.CategoryService;
import com.example.coffeeshop.service.OrderService;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final UserService userService;
    private final CategoryService categoryService;
    private final CurrentUser currentUser;

    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper, UserService userService, CategoryService categoryService, CurrentUser currentUser) {
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.categoryService = categoryService;
        this.currentUser = currentUser;
    }

    @Override
    public void addOrder(OrderServiceModel orderServiceModel) {
        OrderEntity order = modelMapper.map(orderServiceModel, OrderEntity.class);

        order.setEmployee(userService.findById(currentUser.getId()));
        order.setCategory(categoryService.findCategoryByCategoryNameEnum(orderServiceModel.getCategory()));

        this.orderRepository.save(order);
    }
}
