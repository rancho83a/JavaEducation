package vehiclesExtend.Vehicles;

import java.text.DecimalFormat;

public abstract class VehiclesImpl implements Vehicles {
    private double quantity;
    private double consumption;
    private double capacity;

    protected VehiclesImpl(double quantity, double consumption, double capacity) {
        this.quantity = quantity;
        this.consumption = consumption;
        this.capacity=capacity;
    }

    protected void incrementConsumtion(double increment){
        this.consumption+=increment;
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
        if(liters>0) {
            if(this.quantity+liters>this.capacity){
                System.out.println("Cannot fit fuel in tank");
                return;
            }
            this.quantity += liters;
        } else {
            System.out.println("Fuel must be a positive number");
        }
    }

    @Override
    public String toString() {
        return String.format("%s: %.2f",this.getClass().getSimpleName(),this.quantity);
    }
}
