import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private long id;
    private String type;
    private String ownerName;


    public Vehicle() {
    }

    public  Vehicle(String type, String ownerName) {
        this.type = type;
        this.ownerName = ownerName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
