package hotelReservation;

public enum Season {
    AUTUMN (1),
    WINTER(3),
    SPRING(2),
    SUMMER(4);

    private int priceMultiplier;

    Season(int priceMultiplier){
        this.priceMultiplier=priceMultiplier;
    }

    public double multipliedPrice (double price){
        return price*this.priceMultiplier;
    }
}
