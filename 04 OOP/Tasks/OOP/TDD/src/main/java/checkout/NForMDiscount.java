package checkout;

public class NForMDiscount {
    private final int howManyItems;
    private final long priceForThatManyItems;

    public NForMDiscount(int howManyItems, long priceForThatManyItems) {
        this.howManyItems = howManyItems;
        this.priceForThatManyItems = priceForThatManyItems;
    }

    public long calculate(int itemCount, long singleItemPrice) {
        int discountedSetsOfItems = itemCount / howManyItems;
        int remainingItems = itemCount % howManyItems;

        return discountedSetsOfItems * priceForThatManyItems + remainingItems * singleItemPrice;
    }
}
