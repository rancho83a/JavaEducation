package jsoprocessing.ex.model.Dto.viewDto.task4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class UserFirstLastNameAgeSoldProductsDto {
    @Expose
    private String firstName;
    @Expose
    private String lastName;
    @Expose
    private int age;
    @Expose
    private SoldProductCountDto soldProducts;

    public UserFirstLastNameAgeSoldProductsDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SoldProductCountDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(SoldProductCountDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
