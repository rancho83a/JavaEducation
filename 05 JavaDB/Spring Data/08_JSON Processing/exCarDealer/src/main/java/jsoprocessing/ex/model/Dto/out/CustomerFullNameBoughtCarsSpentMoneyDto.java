package jsoprocessing.ex.model.Dto.out;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;
import java.util.Objects;

public class CustomerFullNameBoughtCarsSpentMoneyDto {
    @Expose
    private String fullName;
    @Expose
    private int boughtCar;
    @Expose
    private BigDecimal spentMoney;

    public CustomerFullNameBoughtCarsSpentMoneyDto() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getBoughtCar() {
        return boughtCar;
    }

    public void setBoughtCar(int boughtCar) {
        this.boughtCar = boughtCar;
    }

    public BigDecimal getSpentMoney() {
        return spentMoney;
    }

    public void setSpentMoney(BigDecimal spentMoney) {
        this.spentMoney = spentMoney;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerFullNameBoughtCarsSpentMoneyDto that = (CustomerFullNameBoughtCarsSpentMoneyDto) o;
        return boughtCar == that.boughtCar && Objects.equals(fullName, that.fullName) && Objects.equals(spentMoney, that.spentMoney);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullName, boughtCar, spentMoney);
    }
}
