package vehicles2.Vehicles;

public class Truck extends VehiclesImpl {
    protected Truck(double quantity, double consumption) {
        super(quantity, consumption + 1.6);
    }

    @Override
    public void refuel(double liters) {
        super.refuel(liters*0.95);
    }
}
