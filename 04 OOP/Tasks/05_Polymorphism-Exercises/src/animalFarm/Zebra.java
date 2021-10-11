package animalFarm;

public class Zebra extends Mammal {
    protected Zebra(String animalName, String animalType, Double animalWeight,  String livingRegion) {
        super(animalName, animalType, animalWeight,  livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("Zs");

    }

    @Override
    protected void eat(Food food) {
        if (!(food instanceof Vegetable)){
            System.out.println("Zebras are not eating that type of food!");
            return;
        }
        super.eat(food);

    }
}
