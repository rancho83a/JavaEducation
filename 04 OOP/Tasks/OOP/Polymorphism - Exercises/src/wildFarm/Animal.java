package wildFarm;

public abstract class Animal {
    private String animalName;
    private String animalType;
    private Double Weight;
    private Integer foodEaten;

    public Animal(String animalName, String animalType, Double weight) {
        this.animalName = animalName;
        this.animalType = animalType;
        this.Weight = weight;
        this.foodEaten=0;
    }

    protected String getAnimalName() {
        return animalName;
    }

    protected String getAnimalType() {
        return animalType;
    }

    protected Double getWeight() {
        return Weight;
    }

    protected Integer getFoodEaten() {
        return foodEaten;
    }

    protected abstract void makeSound();

    protected  void eatFood(Food food){
        this.foodEaten+= food.getQuantity();
    }


}
