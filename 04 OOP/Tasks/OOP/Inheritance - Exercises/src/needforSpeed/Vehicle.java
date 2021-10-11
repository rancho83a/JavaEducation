package needforSpeed;

public class Vehicle {
    private  final static double DEFAULT_FUEL_CONSUMPTION= 1.25;
    private double fuelConsumption;
    private double fuel;
    private int horsePower;

    public Vehicle(double fuel, int horsePower) {
        this.setFuelConsumption(DEFAULT_FUEL_CONSUMPTION);
        this.fuel = fuel;
        this.horsePower = horsePower;
    }
    public void drive(double kilometers){
        double needFuelForDrive = kilometers*this.getFuelConsumption();
        if(this.getFuel()>=needFuelForDrive){
            this.setFuel(this.getFuel()-needFuelForDrive);
        }

    }

    public double getFuelConsumption() {
        return fuelConsumption;
    }

    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
