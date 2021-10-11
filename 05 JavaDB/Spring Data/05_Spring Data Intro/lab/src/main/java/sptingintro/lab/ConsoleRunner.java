package sptingintro.lab;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import sptingintro.lab.exeption.InsufficientFundException;
import sptingintro.lab.exeption.UserNameAlreadyExistExeption;
import sptingintro.lab.services.AccountService;
import sptingintro.lab.services.UserService;

import java.math.BigDecimal;

@Service
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final AccountService accountService;

    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @Override
    public void run(String... args) throws Exception {
//        try {
//            this.userService.registerUser("Pesho", 33, BigDecimal.valueOf(11111));
//        }catch (UserNameAlreadyExistExeption ex){
//            System.out.println(ex.getClass().getSimpleName());
//        }
//
//        this.userService.addAccount(BigDecimal.valueOf(33),1);
//
//
//        //
//
//        try {
//            this.accountService.withdrawMoney(BigDecimal.valueOf(200),1L);
//        } catch (InsufficientFundException insufficientFundExeption) {
//            insufficientFundExeption.printStackTrace();
//        }
//
//        this.accountService.transferMoney(BigDecimal.valueOf(200),2L);

        try {
            this.accountService.transferBetweenAccounts(2L,4L, BigDecimal.valueOf(1000));
        } catch (InsufficientFundException e) {
            e.printStackTrace();
        }
    }
}
