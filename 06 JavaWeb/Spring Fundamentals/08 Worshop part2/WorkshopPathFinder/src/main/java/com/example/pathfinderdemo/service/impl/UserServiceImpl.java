package com.example.pathfinderdemo.service.impl;

import com.example.pathfinderdemo.Repository.UserRepository;
import com.example.pathfinderdemo.model.entity.Enum.LevelEnum;
import com.example.pathfinderdemo.model.entity.UserEntity;
import com.example.pathfinderdemo.model.service.UserServiceModel;
import com.example.pathfinderdemo.service.UserService;
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
}
