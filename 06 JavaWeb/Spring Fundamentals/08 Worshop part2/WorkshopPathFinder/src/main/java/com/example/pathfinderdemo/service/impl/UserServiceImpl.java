package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.UserRepository;
import com.example.pathfinderdemo.model.entity.Enum.LevelEnum;
import com.example.pathfinderdemo.model.entity.UserEntity;
import com.example.pathfinderdemo.model.service.UserServiceModel;
import com.example.pathfinderdemo.service.UserService;
import com.example.pathfinderdemo.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);
        newUser.setLevel(LevelEnum.BEGINNER);

        userRepository.save(newUser);

    }

    @Override
    public UserServiceModel findUserByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(username,password)
                .map(user-> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setUsername(username);
        currentUser.setId(id);
    }

    @Override
    public void logoutUser() {
        currentUser.setUsername(null).setId(null);
    }
}
