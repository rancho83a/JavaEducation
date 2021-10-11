package greedyTimesMy;

public class Item {
    private String name;
    private long amount;

    public Item(String name, long amount) {
        this.name = name;
        this.amount = amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
