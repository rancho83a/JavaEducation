package sptingintro.lab.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;
@Column
    private int age;
    @OneToMany(mappedBy = "user")
    private Set<Account> accounts;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }
}
