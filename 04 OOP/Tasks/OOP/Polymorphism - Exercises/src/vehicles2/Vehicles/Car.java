package vehicles2.Vehicles;

public class Car extends VehiclesImpl {
    protected Car(double quantity, double consumption) {
        super(quantity, consumption+0.9);
    }

}
