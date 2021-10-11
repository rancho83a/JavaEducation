package task4_Pizza;

public class Topping {
    private String toppingType;
    private double weight;

    public Topping(String toppingType, double weight) {
        this.setToppingType(toppingType);
        this.setWeight(weight);
    }

    private void setToppingType(String toppingType) {
       Validator.toppingTypeValidate(toppingType);
        this.toppingType =toppingType;
    }

    private void setWeight(double weight) {
        Validator.weightValidate(weight, 50,this.toppingType);
        this.weight = weight;
    }

    public double calculateCalories (){
        double modifier = ToppingEnum.valueOf(toppingType.toUpperCase()).getModifier();
        return 2*this.weight*modifier;
    }


    @Override
    public String toString() {
        return "Topping{" +
                "toppingType='" + toppingType + '\'' +
                ", weight=" + weight +
                '}' + calculateCalories();
    }
}
