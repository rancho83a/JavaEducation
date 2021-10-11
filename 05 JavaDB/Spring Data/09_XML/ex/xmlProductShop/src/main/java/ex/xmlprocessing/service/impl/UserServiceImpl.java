package ex.xmlprocessing.service.impl;

import ex.xmlprocessing.Dto.UserSeedDto;
import ex.xmlprocessing.Dto.query2.ProductNamePriceBuyerDto;
import ex.xmlprocessing.Dto.query2.UserFirstLastNameProductsDto;
import ex.xmlprocessing.Dto.query2.UserViewRootDto;
import ex.xmlprocessing.Dto.query4.ProductDto;
import ex.xmlprocessing.Dto.query4.SoldProductsDto;
import ex.xmlprocessing.Dto.query4.UserRootOutDto;
import ex.xmlprocessing.Dto.query4.UserFirstLastNameAgeDto;
import ex.xmlprocessing.Repository.UserRepository;
import ex.xmlprocessing.model.entity.Category;
import ex.xmlprocessing.model.entity.User;
import ex.xmlprocessing.service.UserService;
import ex.xmlprocessing.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public void seedUsers(List<UserSeedDto> users) {
        users.stream()
                .filter(validationUtil::isValid)
                .map(userSeedDto -> modelMapper.map(userSeedDto, User.class))
                .forEach(userRepository::save);
    }

    @Override
    public long getUserCount() {
        return this.userRepository.count();
    }

    @Override
    public User getRandomUser() {
        Long id = ThreadLocalRandom
                .current().nextLong(1, this.userRepository.count() + 1);
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public UserViewRootDto getALlUsersWith1SoldProductAtLeast() {
        // List<User> allWith1SoldProductAtLeast = this.userRepository.findAllWith1SoldProductAtLeast();
        UserViewRootDto userViewRootDto = new UserViewRootDto();

        userViewRootDto.setUsers(
                this.userRepository.findAllWith1SoldProductAtLeast()
                        .stream()
                        .map(user -> modelMapper.map(user, UserFirstLastNameProductsDto.class)).collect(Collectors.toList())
        );
        return userViewRootDto;
    }

    @Override
    public UserRootOutDto findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName() {
        UserRootOutDto userRootOutDto = new UserRootOutDto();

        List<UserFirstLastNameAgeDto> users = this.userRepository.findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName()
                .stream()
                .map(user -> {
                    UserFirstLastNameAgeDto userInfo = modelMapper.map(user, UserFirstLastNameAgeDto.class);


                    SoldProductsDto soldProductsDto = new SoldProductsDto();

                    soldProductsDto.setProducts(
                    user.getSoldProducts().stream()
                            .map(product -> modelMapper.map(product, ProductDto.class))
                            .collect(Collectors.toList())
                    );

                    soldProductsDto.setCount(user.getSoldProducts().size() + "");


                    userInfo.setSoldProducts(soldProductsDto);
                    return userInfo;
                }).collect(Collectors.toList());

        userRootOutDto.setCount(users.size() + "");
        userRootOutDto.setUsers(users);
        return userRootOutDto;
    }
}
