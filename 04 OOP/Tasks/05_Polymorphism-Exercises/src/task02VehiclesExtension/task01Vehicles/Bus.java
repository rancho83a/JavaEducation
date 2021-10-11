package task02VehiclesExtension.task01Vehicles;

public class Bus extends VehicleImpl {
    private static final double INCREASE_CONSUMPTION=1.4;
    protected Bus(double quantity, double consumption, double capacity) {
        super(quantity, consumption, capacity);
    }
    @Override
    public String drive(double distance){
        this.setConsumption(this.getConsumption()+INCREASE_CONSUMPTION);
        String res = super.drive(distance);
        this.setConsumption(this.getConsumption()-INCREASE_CONSUMPTION);

        return res;
    }
    public String  driveEmpty(double distance){
        return super.drive(distance);
    }



}
