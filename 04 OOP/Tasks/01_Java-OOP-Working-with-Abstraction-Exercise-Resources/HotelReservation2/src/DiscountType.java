public enum DiscountType {

    VIP(20),
    SECONDVISIT(10),
    NONE(0);

    private int discount;
    private DiscountType(int discount){
        this.discount=discount;
    }

    public int getDiscount() {
        return discount;
    }
}
