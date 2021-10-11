package hotelReservation;

public class Reservation {
    private double pricePerDay;
    private int numberOfDays;
    private Season season;
    private DiscountType discount;

    public Reservation(double pricePerDay, int numberOfDays, Season season, DiscountType discount) {
        this.pricePerDay = pricePerDay;
        this.numberOfDays = numberOfDays;
        this.season = season;
        this.discount = discount;
    }

    public double calculatedPrice(){
        double price = this.pricePerDay*this.numberOfDays;
        price = season.multipliedPrice(price);
        price = discount.discountFor(price);

        return price;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "pricePerDay=" + pricePerDay +
                ", numberOfDays=" + numberOfDays +
                ", season=" + season +
                ", discount=" + discount +
                '}';
    }
}
