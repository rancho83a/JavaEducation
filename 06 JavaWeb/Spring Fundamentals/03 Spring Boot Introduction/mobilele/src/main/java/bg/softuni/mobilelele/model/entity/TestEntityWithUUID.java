package bg.softuni.mobilelele.model.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

import org.hibernate.annotations.Type;
import org.hibernate.id.UUIDGenerator;

@Entity
@Table(name ="test_entity")
public class TestEntityWithUUID {
    @Id
    @GeneratedValue
    @Type(type="uuid-char")
    @GenericGenerator(
            name ="UUIDGenerator",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;

    public UUID getId() {
        return id;
    }

    public TestEntityWithUUID setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public TestEntityWithUUID setName(String name) {
        this.name = name;
        return this;
    }
}
