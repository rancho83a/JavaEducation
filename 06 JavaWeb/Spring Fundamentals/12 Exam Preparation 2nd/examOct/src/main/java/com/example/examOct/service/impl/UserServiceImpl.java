package com.example.examOct.service.impl;


import com.example.examOct.model.entity.UserEntity;
import com.example.examOct.model.service.UserServiceModel;
import com.example.examOct.repository.UserRepository;
import com.example.examOct.security.CurrentUser;
import com.example.examOct.service.UserService;
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

        userRepository.save(newUser);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password) {

        return userRepository.findByUsernameAndPassword(username, password)
                .map(user-> modelMapper.map(user, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public void loginUser(Long id, String username) {
        currentUser.setUsername(username).setId(id);

    }

    @Override
    public void logout() {
        currentUser.setUsername(null).setId(null);
    }

    @Override
    public UserEntity findById(Long id) {
        return this.userRepository.findById(id).orElse(null);
    }


    @Override
    public boolean isUserNameFree(String username) {
        return this.userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }
}
