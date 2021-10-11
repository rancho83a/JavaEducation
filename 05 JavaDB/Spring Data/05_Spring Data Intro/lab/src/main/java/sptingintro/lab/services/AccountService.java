package sptingintro.lab.services;

import sptingintro.lab.exeption.InsufficientFundException;

import java.math.BigDecimal;

public interface AccountService {
    void withdrawMoney(BigDecimal money, Long id) throws InsufficientFundException;
    void transferMoney(BigDecimal money, Long id);
    void transferBetweenAccounts(Long id1,Long id2, BigDecimal amount) throws InsufficientFundException;
}
