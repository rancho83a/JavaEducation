package wildFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;

    public Cat(String animalName, String animalType, Double weight,  String livingRegion, String breed) {
        super(animalName, animalType, weight, livingRegion);
        this.breed=breed;
    }

    @Override
    protected void makeSound() {
        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        return String.format("Cat[%s, %s, %s, %s, %d]",
                this.getAnimalName(),this.breed,new DecimalFormat("##.##").format(this.getWeight()),this.getLivingRegion(),this.getFoodEaten());
    }
}
