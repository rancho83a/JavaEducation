package ex.gameStore.service.impl;

import ex.gameStore.model.dto.UserLoginDto;
import ex.gameStore.model.dto.UserRegisterDto;
import ex.gameStore.model.entity.Game;
import ex.gameStore.model.entity.User;
import ex.gameStore.repository.GameRepository;
import ex.gameStore.repository.UserRepository;
import ex.gameStore.service.UserService;
import ex.gameStore.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;
    final private GameRepository gameRepository;
    private User loginUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, ValidationUtil validationUtil, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
        this.gameRepository = gameRepository;
    }

    @Override
    public void registerUser(UserRegisterDto userRegisterDto) {
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            System.out.println("Wrong confirm password");
            return;
        }
        Set<ConstraintViolation<UserRegisterDto>> violations = validationUtil.violation(userRegisterDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }
        User user = modelMapper.map(userRegisterDto, User.class);


        if(this.userRepository.count()==0){
            user.setIsAdmin(true);
        }

        userRepository.save(user);
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        Set<ConstraintViolation<UserLoginDto>> violations = validationUtil.violation(userLoginDto);

        if (!violations.isEmpty()) {
            violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
            return;
        }

        User user = this.userRepository.findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword())
                .orElse(null);
        if (user == null) {
            System.out.println("Incorrect username / password");
            return;
        }
        loginUser = user;
        System.out.println("Successfully logged in "+user.getFullName());

    }

    @Override
    public void logout() {
        if (loginUser == null) {
            System.out.println("Cannot log out. No user was logged in.");
        } else {
            System.out.println("User "+ loginUser.getFullName() +" successfully logged out");
            loginUser = null;
        }

    }

    @Override
    public Boolean isLoggedInUserAdmin() {
        return this.loginUser.getIsAdmin();
    }
    public User getLoginUser() {
        return loginUser;
    }

    @Override
    public void purchaseGame(String gameName) {
        Game game = this.gameRepository.findByTitle(gameName);
        if(game==null){
            System.out.println("There is no game with provided name");
            return;
        }
        this.loginUser.getGames().add(game);
        this.userRepository.save(loginUser);
        System.out.println(loginUser.getFullName()+" purchased game: "+ gameName);

    }

    @Override
    public void printGamesForCurrentLoggedUser() {
        this.loginUser.getGames().forEach(g-> System.out.println(g.getTitle()));
    }

    @Override
    public List<User> getAllByGameId(Long id) {
        return this.userRepository.findAllByGamesId(id);
    }

}
