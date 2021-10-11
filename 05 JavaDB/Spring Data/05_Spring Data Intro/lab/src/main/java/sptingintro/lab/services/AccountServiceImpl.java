package sptingintro.lab.services;

import org.springframework.stereotype.Service;
import sptingintro.lab.exeption.InsufficientFundException;
import sptingintro.lab.models.Account;
import sptingintro.lab.repositories.AccountRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void withdrawMoney(BigDecimal money, Long id) throws InsufficientFundException {
        Account account = getAccount(id);
        if (account.getBalance().compareTo(money) < 0) {
            throw new InsufficientFundException();
        }

        account.setBalance(account.getBalance().subtract(money));
        this.accountRepository.save(account);
    }

    private Account getAccount(Long id) {
        return this.accountRepository.findById(id).orElseThrow();
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {

        Account account = getAccount(id);

        account.setBalance(account.getBalance().add(money));
        this.accountRepository.save(account);
    }

    @Transactional
    @Override

    public void transferBetweenAccounts(Long id1, Long id2, BigDecimal amount) throws InsufficientFundException {
        this.transferMoney(amount, id2);
        this.withdrawMoney(amount, id1);

    }
}
