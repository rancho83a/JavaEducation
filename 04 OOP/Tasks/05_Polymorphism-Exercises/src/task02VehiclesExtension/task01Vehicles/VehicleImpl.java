package task02VehiclesExtension.task01Vehicles;

import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;

public abstract class VehicleImpl implements Vehicle {
    private double quantity;
    private double consumption;
    private double capacity;

    protected VehicleImpl(double quantity, double consumption, double capacity) {

        this.quantity = quantity;
        this.consumption = consumption;
        this.capacity =capacity;
    }



    public double getQuantity() {
        return quantity;
    }

    protected void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    protected double getConsumption() {
        return consumption;
    }

    protected void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    @Override
    public String drive(double distance) {
        if(distance*this.consumption>this.quantity){
            return String.format("%s needs refueling", this.getClass().getSimpleName());
        }

        String pattern="0.##";
        DecimalFormat myFormat = new DecimalFormat(pattern);


        this.quantity-=distance*this.consumption;
        return String.format("%s travelled %s km", this.getClass().getSimpleName(), myFormat.format(distance));
    }

    @Override
    public void refuel(double liter) {
        if(liter<=0){
            System.out.println("Fuel must be a positive number");
            return;
        }
        if(this.quantity+liter>capacity){
            System.out.println("Cannot fit fuel in tank");
            return;
        }
        this.quantity+=liter;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(), this.quantity);
    }
}
