public class PriceCalculator {

    private double price;
    private int days;
    private DiscountType discountType;
    private Season season;

    public PriceCalculator(double price, int days,  Season season, DiscountType discountType) {
        this.price = price;
        this.days = days;
        this.discountType = discountType;
        this.season = season;
    }

    public double calculate(){
        return price*days*season.getKoef()*(1-discountType.getDiscount()/100.00);
    }
}
