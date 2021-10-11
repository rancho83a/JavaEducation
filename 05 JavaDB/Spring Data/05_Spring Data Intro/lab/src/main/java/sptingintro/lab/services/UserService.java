package sptingintro.lab.services;

import sptingintro.lab.exeption.UserNameAlreadyExistExeption;
import sptingintro.lab.exeption.UserNotFoundException;

import java.math.BigDecimal;

public interface UserService {
    public void registerUser(
            String username,
            int age,
            BigDecimal initialCapital
    ) throws UserNameAlreadyExistExeption;

    void addAccount(BigDecimal amount, long id) throws UserNotFoundException;
}
