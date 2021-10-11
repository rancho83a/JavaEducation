package jsoprocessing.ex.model.Dto.viewDto.task4;

import com.google.gson.annotations.Expose;

import java.util.List;

public class SoldProductCountDto {
    @Expose
    private int count;
    @Expose
    List<ProductNamePriceDto> products;

    public SoldProductCountDto() {
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ProductNamePriceDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductNamePriceDto> products) {
        this.products = products;
    }
}
