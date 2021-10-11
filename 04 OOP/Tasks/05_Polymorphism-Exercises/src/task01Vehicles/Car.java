package task01Vehicles;

public class Car extends VehicleImpl {
    private static final double INCREASE_CONSUMPTION=0.9;
    protected Car(double quantity, double consumption) {
        super(quantity, consumption+INCREASE_CONSUMPTION);
    }


}
