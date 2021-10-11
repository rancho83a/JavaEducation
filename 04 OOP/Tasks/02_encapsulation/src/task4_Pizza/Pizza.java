package task4_Pizza;

import java.util.ArrayList;
import java.util.List;

public class Pizza {
    private String name;
    private Dough dough;
    private List<Topping> toppings;

    public Pizza(String name, int toppingsCount) {
        this.setName(name);
        this.setToppings(toppingsCount);

    }

    private void setName(String name) {
        Validator.nameValidate(name);
        this.name = name;
    }

    public void setDough(Dough dough) {
        this.dough = dough;
    }

    private void setToppings(int toppingsCount) {
        Validator.toppingCountValidate(toppingsCount);
        this.toppings = new ArrayList<>(toppingsCount);
    }

    public String getName() {
        return name;
    }

    public void addTopping(Topping topping){
        this.toppings.add(topping);
    }

    public  double getOverallCalories(){
     return this.dough.calculateCalories()+
             this.toppings.stream()
                     .mapToDouble(Topping::calculateCalories)
                     .sum();
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", this.getName(), this.getOverallCalories());
    }
}
