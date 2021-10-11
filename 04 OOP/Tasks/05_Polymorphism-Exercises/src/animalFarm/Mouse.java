package animalFarm;

public class Mouse extends Mammal {
    protected Mouse(String animalName, String animalType, Double animalWeight,  String livingRegion) {
        super(animalName, animalType, animalWeight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("SQUEEEAAAK!");

    }

    @Override
    protected void eat(Food food) {
        if (!(food instanceof Vegetable)){
            System.out.println("Mice are not eating that type of food!");
            return;
        }

        super.eat(food);
    }
}
