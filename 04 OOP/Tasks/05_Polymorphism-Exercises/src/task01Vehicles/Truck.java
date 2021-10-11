package task01Vehicles;

public class Truck extends VehicleImpl{
    protected Truck(double quantity, double consumption) {
        super(quantity, consumption+1.6);
    }

    @Override
    public void refuel(double liter) {
        super.refuel(liter*0.95);
    }
}
