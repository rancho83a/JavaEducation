package jsoprocessing.ex.model.Dto.out;

import com.google.gson.annotations.Expose;
import jsoprocessing.ex.model.entity.Sale;

import java.time.LocalDateTime;
import java.util.Set;

public class CustomerIdNameBirthDateIsYoungerDriverDto {
    @Expose
    private Long Id;
    @Expose
    private String name;
    @Expose
    private String birthDate;
    @Expose
    private Boolean isYoungDriver;
    @Expose
    private Set<SaleExportDto> sales;

    public CustomerIdNameBirthDateIsYoungerDriverDto() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(Boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public Set<SaleExportDto> getSales() {
        return sales;
    }

    public void setSales(Set<SaleExportDto> sales) {
        this.sales = sales;
    }
}
