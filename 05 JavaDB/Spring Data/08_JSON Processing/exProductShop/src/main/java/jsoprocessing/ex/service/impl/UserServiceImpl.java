package jsoprocessing.ex.service.impl;

import com.google.gson.Gson;
import jsoprocessing.ex.constant.GlobalConstant;
import jsoprocessing.ex.model.Dto.UserSeedDto;
import jsoprocessing.ex.model.Dto.viewDto.UserSoldProductsDto;
import jsoprocessing.ex.model.Dto.viewDto.task4.ProductNamePriceDto;
import jsoprocessing.ex.model.Dto.viewDto.task4.SoldProductCountDto;
import jsoprocessing.ex.model.Dto.viewDto.task4.UserFirstLastNameAgeSoldProductsDto;
import jsoprocessing.ex.model.Dto.viewDto.task4.UsersOutputDto;
import jsoprocessing.ex.model.entity.User;
import jsoprocessing.ex.repository.UserRepository;
import jsoprocessing.ex.service.UserService;
import jsoprocessing.ex.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private static final String USER_FILE_NAME = "users.json";
    private final UserRepository userRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;

    public UserServiceImpl(UserRepository userRepository, Gson gson, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.userRepository = userRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }


    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() > 0) {
            return;
        }

        Arrays.stream(gson.fromJson(
                Files.readString(Path.of(GlobalConstant.RESOURCE_FILE_PATH + USER_FILE_NAME)),
                UserSeedDto[].class))
                .filter(validationUtil::isValid)
                .map(d -> modelMapper.map(d, User.class))
                .forEach(userRepository::save);


    }

    @Override
    public User getRandomUser() {
        Long id = ThreadLocalRandom
                .current().nextLong(1, this.userRepository.count() + 1);
        return this.userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserSoldProductsDto> getAllBySoldProductsWithOneBuyer() {

        return this.userRepository.findAllBySoldProductsWithOneBuyerOrderByLastNameThenFirstName()
                .stream()
                .map(user -> modelMapper.map(user, UserSoldProductsDto.class))
                .collect(Collectors.toList());

    }

    @Override
    public UsersOutputDto getAllBySoldProductsWithOneBuyerOrderSoldProductCount() {
        //Връщаме само ЕДО ДТО , а не лист
        UsersOutputDto usersOutputDto =new UsersOutputDto();

        List<User> users = this.userRepository.findAllBySoldProductsWithOneBuyerOrderByCountSoldProductDescThenLastName();

        List<UserFirstLastNameAgeSoldProductsDto> listUsers = users.stream()
                .map(user -> {
                    UserFirstLastNameAgeSoldProductsDto innerUser = modelMapper.map(user, UserFirstLastNameAgeSoldProductsDto.class);

                    SoldProductCountDto soldProductCountDto =new SoldProductCountDto();
                    List<ProductNamePriceDto> listProducts = user.getSoldProducts().stream()
                            .map(product -> modelMapper.map(product, ProductNamePriceDto.class))
                            .collect(Collectors.toList());

                    soldProductCountDto.setCount(user.getSoldProducts().size());
                    soldProductCountDto.setProducts(listProducts);
                    innerUser.setSoldProducts(soldProductCountDto);
                    return innerUser;
                })
                .collect(Collectors.toList());

        usersOutputDto.setUsersCount(users.size());
        usersOutputDto.setUsers(listUsers);
        return usersOutputDto;
    }
}
