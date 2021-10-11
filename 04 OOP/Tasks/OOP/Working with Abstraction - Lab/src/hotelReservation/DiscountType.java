package hotelReservation;

public enum DiscountType {
    VIP(0.2),
    SECONDVISIT(0.1),
    NONE(0);

    private double discount;

    DiscountType(double discount) {
        this.discount = discount;
    }

    public double discountFor (double amount){
        return (1-this.discount)*amount;
    }

//    public static void main(String[] args) {
//        System.out.println(DiscountType.VIP.discountFor(10));
//        System.out.println(DiscountType.NONE.discountFor(10));
//        System.out.println(DiscountType.SECOND_VISIT.discountFor(10));
//    }
}
