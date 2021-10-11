package project.nextleveltechnology.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import project.nextleveltechnology.model.Entity.User;
import project.nextleveltechnology.model.dto.UserLoginDto;
import project.nextleveltechnology.model.dto.UserRegisterDto;
import project.nextleveltechnology.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean register(UserRegisterDto userRequest) {
        if (
                this.userRepository.existsByUsernameOrEmail(userRequest.getUsername(), userRequest.getEmail())
                        || !userRequest.getPassword().equals(userRequest.getConfirmPassword())
        ) {
            return false;
        }

        User user = modelMapper.map(userRequest, User.class);
        this.userRepository.save(user);
        return true;

    }

    @Override
    public Long validateUserLoginAndDetails(UserLoginDto userRequest) {
        User user = this.userRepository.findFirstByUsername(userRequest.getUsername());

        if (user == null || !user.getPassword().equals(userRequest.getPassword())) {
            return null;
        }
        return user.getId();
    }
}
