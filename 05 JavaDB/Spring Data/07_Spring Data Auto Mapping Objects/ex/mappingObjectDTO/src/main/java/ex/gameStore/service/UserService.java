package ex.gameStore.service;

import ex.gameStore.model.dto.UserLoginDto;
import ex.gameStore.model.dto.UserRegisterDto;
import ex.gameStore.model.entity.User;

import java.math.BigDecimal;
import java.util.List;

public interface UserService {
    void registerUser(UserRegisterDto userRegisterDto);

    void loginUser(UserLoginDto userLoginDto);

    void logout();

    Boolean isLoggedInUserAdmin();

    User getLoginUser();

    void purchaseGame(String gameName);

    void    printGamesForCurrentLoggedUser();

    List<User> getAllByGameId(Long id);
}
