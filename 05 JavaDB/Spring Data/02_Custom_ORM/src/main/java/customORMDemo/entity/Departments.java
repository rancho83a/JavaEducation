package customORMDemo.entity;

import OrmFramework.annotation.Column;
import OrmFramework.annotation.Entity;
import OrmFramework.annotation.Id;

@Entity(tableName = "Departments")
public class Departments {
    @Id
    private int id;

    @Column(name="name", columnDefinition = "VARCHAR(211)")
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
