package entities;

import javax.persistence.*;

@Entity
@Table(name="billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class BillingDetail extends BaseEntity{
    private String number;
    private User owner;

    public BillingDetail() {
    }

    @Column(unique = true, nullable = false)
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @ManyToOne
    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
