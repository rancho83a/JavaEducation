package carShopping;

public abstract class CarImpl implements Car{
    private String model;
    private String color;
    private String countryProduced;
    private Integer horsePower;

    protected CarImpl(String model, String color,  Integer horsePower, String countryProduced) {
        this.model = model;
        this.color = color;
        this.countryProduced = countryProduced;
        this.horsePower = horsePower;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public Integer getHorsePower() {
        return horsePower;
    }

    @Override
    public String countryProduced() {
        return countryProduced;
    }
    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires", this.getModel(),this.countryProduced(), TIRES);
    }


}
