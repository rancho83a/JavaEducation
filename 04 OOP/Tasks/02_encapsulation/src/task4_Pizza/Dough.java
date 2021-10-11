package task4_Pizza;

public class Dough {
    private String flourType;
    private String bakingTechnique;
    private double weight;

    public Dough(String flourType, String bakingTechnique, double weight) {
        this.setFlourType(flourType);
        this.setBakingTechnique(bakingTechnique);
        this.setWeight(weight);
    }

    private void setFlourType(String flourType) {
        Validator.flourTypeValidate(flourType);
        this.flourType = flourType;
    }

    private void setWeight(double weight) {
        Validator.weightValidate(weight, 200, "Dough");
        this.weight = weight;
    }

    private void setBakingTechnique(String bakingTechnique) {
        Validator.flourTypeValidate(bakingTechnique);
        this.bakingTechnique = bakingTechnique;
    }

    public double calculateCalories (){
        double modifier1 = DoughEnum.valueOf(flourType.toUpperCase()).getModifier();
        double modifier2 = DoughEnum.valueOf(bakingTechnique.toUpperCase()).getModifier();
        return 2*this.weight*modifier1*modifier2;
    }

    @Override
    public String toString() {
        return "Dough{" +
                "flourType='" + flourType + '\'' +
                ", bakingTechnique='" + bakingTechnique + '\'' +
                ", weight=" + weight +
                '}'+ this.calculateCalories();
    }
}
