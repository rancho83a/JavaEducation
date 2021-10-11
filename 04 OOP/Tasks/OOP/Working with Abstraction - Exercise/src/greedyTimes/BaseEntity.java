package greedyTimes;

public abstract class  BaseEntity {
    protected long amount;

    public BaseEntity(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
