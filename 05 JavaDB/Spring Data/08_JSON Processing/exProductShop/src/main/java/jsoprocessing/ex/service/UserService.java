package jsoprocessing.ex.service;

import jsoprocessing.ex.model.Dto.viewDto.UserSoldProductsDto;
import jsoprocessing.ex.model.Dto.viewDto.task4.UsersOutputDto;
import jsoprocessing.ex.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserService {
    void seedUsers() throws IOException;

    User getRandomUser();

    List<UserSoldProductsDto> getAllBySoldProductsWithOneBuyer();
    UsersOutputDto getAllBySoldProductsWithOneBuyerOrderSoldProductCount();

}
