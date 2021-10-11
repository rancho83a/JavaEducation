package project.nextleveltechnology.service;

import project.nextleveltechnology.model.dto.UserLoginDto;
import project.nextleveltechnology.model.dto.UserRegisterDto;

public interface UserService {


    boolean register(UserRegisterDto  user);

    Long validateUserLoginAndDetails(UserLoginDto user);
}
