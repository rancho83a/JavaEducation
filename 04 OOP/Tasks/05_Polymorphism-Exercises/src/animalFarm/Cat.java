package animalFarm;

import java.text.DecimalFormat;

public class Cat extends Felime {
    private String breed;
    protected Cat(String animalName, String animalType, Double animalWeight, String livingRegion, String breed) {
        super(animalName, animalType, animalWeight,  livingRegion);
        this.breed=breed;
    }

    @Override
    protected void makeSound() {

        System.out.println("Meowwww");
    }

    @Override
    public String toString() {
        String pattern="##.##";
        DecimalFormat myFormat = new DecimalFormat(pattern);
        return String.format("%s[%s, %s, %s, %s, %d]",
                this.getAnimalType(),this.getAnimalName(), this.breed,myFormat.format(this.getAnimalWeight()), this.getLivingRegion(), getFoodEaten());
    }
}
