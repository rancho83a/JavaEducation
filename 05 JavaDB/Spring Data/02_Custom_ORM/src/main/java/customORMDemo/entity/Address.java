package customORMDemo.entity;

import OrmFramework.annotation.Column;
import OrmFramework.annotation.Entity;
import OrmFramework.annotation.Id;

@Entity(tableName = "Addresses")
public class Address {
    @Id
    private  int id;

    @Column(name="street",columnDefinition = "VARCHAR(255)")
    private String street;

    @Column(name="street_number",columnDefinition = "VARCHAR(255)")
    private String streetNumber;

    @Column(name="people_count",columnDefinition = "INT")
    private int people;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }
}
