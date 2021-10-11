package vehiclesExtend.Vehicles;

public class Car extends VehiclesImpl {
    protected Car(double quantity, double consumption, double capacity) {
        super(quantity, consumption+0.9, capacity);
    }

}
