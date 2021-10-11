package task02VehiclesExtension.task01Vehicles;

public class Truck extends VehicleImpl {
    protected Truck(double quantity, double consumption, double capacity) {
        super(quantity, consumption+1.6, capacity);
    }

    @Override
    public void refuel(double liter) {
        super.refuel(liter*0.95);
    }
}
