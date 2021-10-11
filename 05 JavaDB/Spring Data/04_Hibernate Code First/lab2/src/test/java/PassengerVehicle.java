import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class PassengerVehicle extends Vehicle{

    private int numOfPassengers;

    public PassengerVehicle() {
    }

    public PassengerVehicle(String type, int numOfPassengers, String ownerName) {
        super(type, ownerName);
        this.numOfPassengers =numOfPassengers;
    }

    public int getNumOfPassengers() {
        return numOfPassengers;
    }

    public void setNumOfPassengers(int numOfPassengers) {
        this.numOfPassengers = numOfPassengers;
    }
}
