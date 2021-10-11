package jsoprocessing.ex.model.Dto.out.task6;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class SalesWithAppliedDiscountDto {
    @Expose
    private CarMakeModelTravelledDistanceDto car;
    @Expose
    private String customerName;
    @Expose
    private Double Discount;
    @Expose
    private BigDecimal price;
    @Expose
    private BigDecimal priceWithDiscount;

    public SalesWithAppliedDiscountDto() {
    }

    public CarMakeModelTravelledDistanceDto getCar() {
        return car;
    }

    public void setCar(CarMakeModelTravelledDistanceDto car) {
        this.car = car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Double getDiscount() {
        return Discount;
    }

    public void setDiscount(Double discount) {
        Discount = discount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public void setPriceWithDiscount(BigDecimal priceWithDiscount) {
        this.priceWithDiscount = priceWithDiscount;
    }
}
