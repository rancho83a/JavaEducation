package animalFarm;

public abstract class Food {
    private Integer quantity;

    protected Food(Integer quantity) {
        this.quantity = quantity;
    }

    protected Integer getQuantity() {
        return quantity;
    }
}
