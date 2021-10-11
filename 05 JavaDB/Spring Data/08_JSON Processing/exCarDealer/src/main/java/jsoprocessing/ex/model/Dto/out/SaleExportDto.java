package jsoprocessing.ex.model.Dto.out;

import com.google.gson.annotations.Expose;

public class SaleExportDto {
    @Expose
    private int discount;



    public SaleExportDto() {
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }
}
