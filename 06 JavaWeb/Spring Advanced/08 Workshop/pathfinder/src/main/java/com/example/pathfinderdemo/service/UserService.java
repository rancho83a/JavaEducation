package com.example.pathfinderdemo.service;

import com.example.pathfinderdemo.model.entity.UserEntity;
import com.example.pathfinderdemo.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);



    UserServiceModel findById(Long id);


}
