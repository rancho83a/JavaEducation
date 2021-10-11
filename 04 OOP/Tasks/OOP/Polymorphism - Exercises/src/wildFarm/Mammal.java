package wildFarm;

import java.text.DecimalFormat;

public abstract class Mammal extends Animal{
    private String livingRegion;

    public Mammal(String animalName, String animalType, Double weight,String livingRegion) {
        super(animalName, animalType, weight);
        this.livingRegion=livingRegion;
    }

    public String getLivingRegion() {
        return livingRegion;
    }

    @Override
    public String toString() {

        DecimalFormat myFormat = new DecimalFormat("##.##");
        return String.format("%s[%s, %s, %s, %d]",
              this.getAnimalType(),this.getAnimalName(),myFormat.format(this.getWeight()),
                this.getLivingRegion(),this.getFoodEaten());
    }
}
