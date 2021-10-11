package wildFarm;

public class Tiger extends Felime {
    public Tiger(String animalName, String animalType, Double weight, String livingRegion) {
        super(animalName, animalType, weight, livingRegion);
    }

    @Override
    protected void makeSound() {
        System.out.println("ROAAR!!!");
    }

    @Override
    protected void eatFood(Food food) {
        if (food instanceof Meat) {
            super.eatFood(food);
        } else {
            System.out.println("Tigers are not eating that type of food!");
        }
    }
}
