package jsoprocessing.ex.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name="customers")
public class Customer extends BaseEntity{
    private String name;
    private LocalDateTime birthDate;
    private Boolean isYoungDriver;
    private Set<Sale> sales;

    public Customer() {
    }
@OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    @Column(name="birth_date")
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


@Column(name="is_young_driver")
    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }
}
