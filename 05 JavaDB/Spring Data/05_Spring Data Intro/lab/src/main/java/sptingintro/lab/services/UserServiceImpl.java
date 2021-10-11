package sptingintro.lab.services;

import org.springframework.stereotype.Service;
import sptingintro.lab.exeption.UserNameAlreadyExistExeption;
import sptingintro.lab.exeption.UserNotFoundException;
import sptingintro.lab.models.Account;
import sptingintro.lab.models.User;
import sptingintro.lab.repositories.AccountRepository;
import sptingintro.lab.repositories.UserRepository;

import java.math.BigDecimal;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AccountRepository accountRepository;


    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void registerUser(String username, int age, BigDecimal initialCapital) throws UserNameAlreadyExistExeption {

        if(userRepository.existsByUsername(username)){
            throw new UserNameAlreadyExistExeption();
        }

        var user = new User();
        user.setUsername(username);
        user.setAge(age);
        this.userRepository.save(user);

        saveAccount(initialCapital, user);

    }

    private void saveAccount(BigDecimal initialCapital, User user) {
        var firstAccount = new Account();
        firstAccount.setBalance(initialCapital);
        firstAccount.setUser(user);

        this.accountRepository.save(firstAccount);
    }


    @Override
    public void addAccount(BigDecimal amount, long id) throws UserNotFoundException {
        User user  = this.userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        saveAccount(amount, user);


    }
}
