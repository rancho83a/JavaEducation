package carShopExtended;

public class Seat extends CarImpl implements Sellable{
    private Double price;

    public Seat(String model, String color, Integer power, String country, Double price){
        super(model, color,  power, country);
        this.price=price;
    }


    @Override
    public Double getPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return super.toString()+System.lineSeparator()+String.format("%s sells for %f",this.getModel(), this.getPrice());
    }
}
