package ex.xmlprocessing.service;

import ex.xmlprocessing.Dto.UserSeedDto;
import ex.xmlprocessing.Dto.query2.UserViewRootDto;
import ex.xmlprocessing.Dto.query4.UserRootOutDto;

import ex.xmlprocessing.model.entity.User;

import java.util.List;

public interface UserService {
    void seedUsers(List<UserSeedDto> users);

    long getUserCount();

    User getRandomUser();

    UserViewRootDto getALlUsersWith1SoldProductAtLeast();

    UserRootOutDto findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName();

}
