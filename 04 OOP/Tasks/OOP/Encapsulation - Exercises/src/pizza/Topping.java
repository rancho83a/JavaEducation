package pizza;

import pizza.utils.TypeUtils;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
        if (!TypeUtils.TOPPING_TYPE.containsKey(toppingType)) {
            throw new IllegalArgumentException(String.format("Cannot place %s on top of your pizza.", toppingType));
        }
        this.toppingType = toppingType;
    }

    private void setWeight(double weight) {
        if (weight < 1 || weight > 50) {
            throw new IllegalArgumentException(String
                    .format("%s weight should be in the range [1..50].", this.toppingType));
        }
        this.weight = weight;
    }

    public double calculateCalories() {
        return 2 * this.weight * TypeUtils.TOPPING_TYPE.get(this.toppingType);
    }
}
