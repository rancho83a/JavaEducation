package vehiclesExtend.Vehicles;

public class Bus extends VehiclesImpl {
    protected Bus(double quantity, double consumption, double capacity) {
        super(quantity, consumption, capacity);
    }

    @Override
    public String drive(double km) {

        super.incrementConsumtion(1.4);
        String result = super.drive(km);
        super.incrementConsumtion(-1.4);
        return result;
    }

    public String driveEmpty(double km) {
        return super.drive(km);
    }

}



