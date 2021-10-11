package animalFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal {
    private String livingRegion;
    protected Mammal(String animalName, String animalType, Double animalWeight, String livingRegion) {
        super(animalName, animalType, animalWeight);
        this.livingRegion = livingRegion;
    }

    protected String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {
        String pattern="##.##";
        DecimalFormat myFormat = new DecimalFormat(pattern);
        return String.format("%s[%s, %s, %s, %d]",
                this.getAnimalType(),this.getAnimalName(), myFormat.format(this.getAnimalWeight()), this.livingRegion, getFoodEaten());
    }

}
