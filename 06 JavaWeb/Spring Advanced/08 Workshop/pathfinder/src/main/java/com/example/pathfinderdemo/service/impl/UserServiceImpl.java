package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.UserRepository;
import com.example.pathfinderdemo.model.entity.enums.LevelEnum;
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


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel) {

        UserEntity newUser = modelMapper.map(userServiceModel, UserEntity.class);
        newUser.setLevel(LevelEnum.BEGINNER);

        userRepository.save(newUser);

    }



    @Override
    public UserServiceModel findById(Long id) {
        return userRepository.findById(id)
                .map(user-> modelMapper.map(user, UserServiceModel.class ))
                .orElse(null);
    }

}
