package wildFarm;

public class Zebra extends Mammal {
    public Zebra(String animalName, String animalType, Double weight,  String livingRegion) {
        super(animalName, animalType, weight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("Zs");

    }
    @Override
    protected void eatFood(Food food) {
        if (food instanceof Vegetable) {
            super.eatFood(food);
        } else {
            System.out.println("Zebras are not eating that type of food!");
        }
    }
}
