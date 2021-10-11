package jsoprocessing.ex.model.Dto.viewDto;

import com.google.gson.annotations.Expose;

import java.math.BigDecimal;

public class CategoryStatisticDto {
       @Expose
        private String category;
       @Expose
       private int productsCount;
       @Expose
       private BigDecimal averagePrice;
       @Expose
       private BigDecimal totalRevenue;

       public CategoryStatisticDto() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getProductsCount() {
        return productsCount;
    }

    public void setProductsCount(int productsCount) {
        this.productsCount = productsCount;
    }
    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(BigDecimal totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }
}
