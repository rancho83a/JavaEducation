package sptingintro.lab.models;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="accounts")
public class Account extends BaseEntity{

    @Column
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;

    public Account() {
    }
}
