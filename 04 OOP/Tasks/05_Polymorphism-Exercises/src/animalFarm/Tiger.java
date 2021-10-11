package animalFarm;

public class Tiger extends Felime {
    protected Tiger(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight,  livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    protected void eat(Food food) {
        if (!(food instanceof Meat)){
            System.out.println("Tigers are not eating that type of food!");
            return;
        }
        super.eat(food);
    }
}
