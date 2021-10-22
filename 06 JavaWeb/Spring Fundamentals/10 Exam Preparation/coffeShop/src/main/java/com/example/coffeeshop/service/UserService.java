package com.example.coffeeshop.service;


import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.UserViewModel;

import java.util.List;

public interface UserService {
    void registerUser(UserServiceModel userServiceModel);

    UserServiceModel findByUsernameAndPassword(String username, String password);

    void loginUser(Long id, String username);

    void logout();

    UserEntity findById(Long id);

    List<UserViewModel> findAllUsersByCountOrdersDesc();

    boolean isUserNameFree(String username);
}
