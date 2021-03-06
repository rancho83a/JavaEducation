package com.example.coffeeshop.service.impl;

import com.example.coffeeshop.model.entity.UserEntity;
import com.example.coffeeshop.model.service.UserServiceModel;
import com.example.coffeeshop.model.view.UserViewModel;
import com.example.coffeeshop.repository.UserRepository;
import com.example.coffeeshop.security.CurrentUser;
import com.example.coffeeshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserViewModel> findAllUsersByCountOrdersDesc() {


        return userRepository.findAllByOrdersByDesc()
                .stream()
                .map(user-> {
                    UserViewModel userViewModel=new UserViewModel();
                    userViewModel.setUsername(user.getUsername()).setOrdersCount(user.getOrders().size());
                    return userViewModel;
                })
                .collect(Collectors.toList());
    }

    @Override
    public boolean isUserNameFree(String username) {
        return this.userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }
}
