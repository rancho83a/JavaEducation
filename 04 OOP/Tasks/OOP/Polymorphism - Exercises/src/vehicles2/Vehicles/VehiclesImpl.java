package vehicles2.Vehicles;

import java.text.DecimalFormat;

public abstract class VehiclesImpl implements Vehicles {
    private double quantity;
    private double consumption;

    protected VehiclesImpl(double quantity, double consumption) {
        this.quantity = quantity;
        this.consumption = consumption;
    }


    @Override
    public String drive(double km) {
        double neededFuel = km * this.consumption;
        String vehicleType = getClass().getSimpleName();

        String pattern="0.##";
        DecimalFormat myFormat = new DecimalFormat(pattern);

        if (quantity >= neededFuel) {
            this.quantity-=neededFuel;
            return String.format("%s travelled %s km", vehicleType,myFormat.format(km));
        }
        return vehicleType + " needs refueling";
    }

    @Override
    public void refuel(double liters) {
        this.quantity+=liters;
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(),this.quantity);
    }

}
