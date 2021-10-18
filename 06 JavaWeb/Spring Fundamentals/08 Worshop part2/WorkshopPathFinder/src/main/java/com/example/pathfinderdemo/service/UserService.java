package com.example.pathfinderdemo.service;

import com.example.pathfinderdemo.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findUserByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);
    void logoutUser();

    UserServiceModel findById(Long id);

}
