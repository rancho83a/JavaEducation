package com.example.examOct.service;


import com.example.examOct.model.entity.UserEntity;
import com.example.examOct.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserEntity findById(Long id);


    boolean isUserNameFree(String username);
}
