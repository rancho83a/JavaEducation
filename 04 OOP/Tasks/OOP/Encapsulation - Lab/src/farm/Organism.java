package farm;

public class Organism {
    private double weight;

    public Organism(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    protected void setWeight(double weight) {
        if (weight <=0 ) {
            throw  new IllegalStateException("Error");
        }
        this.weight=weight;
    }
}
