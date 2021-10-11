package carShopExtended;

public abstract class CarImpl implements Car {
    private String model;
    private String color;
    private Integer power;
    private String country;


    protected CarImpl(String model, String color, Integer power, String country) {
        this.model = model;
        this.color = color;
        this.power = power;
        this.country = country;
    }
    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public Integer getHorsePower() {
        return this.power;
    }

    @Override
    public String countryProduced() {
        return this.country;
    }

    @Override
    public String toString() {
        return String.format("This is %s produced in %s and have %d tires", this.getModel(),this.countryProduced(), TIRES);
    }
}
