package wildFarm;

public class Mouse extends Mammal{
    public Mouse(String animalName, String animalType, Double weight,  String livingRegion) {
        super(animalName, animalType, weight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("SQUEEEAAAK!");
    }

    @Override
    protected void eatFood(Food food) {
        if (food instanceof Vegetable) {
            super.eatFood(food);
        } else {
            System.out.println("Mice are not eating that type of food!");
        }
    }
}
