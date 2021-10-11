package ex.gameStore.model.dto;

import java.math.BigDecimal;

public class GameTitlePriceDto extends GameTitleDto{

    private BigDecimal price;

    public GameTitlePriceDto() {

    }

    public GameTitlePriceDto(String title, BigDecimal price) {
        super(title);
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return super.toString()+" "+this.getPrice();
    }
}
