package vehiclesExtend.Vehicles;

public class Truck extends VehiclesImpl {
    protected Truck(double quantity, double consumption, double capacity) {
        super(quantity, consumption + 1.6, capacity);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters*0.95);
    }
}
