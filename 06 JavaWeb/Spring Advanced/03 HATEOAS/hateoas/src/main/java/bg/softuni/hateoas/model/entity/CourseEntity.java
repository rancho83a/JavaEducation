package bg.softuni.hateoas.model.entity;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name="courses")
public class CourseEntity extends BaseEntity{

    private String name;
    private BigDecimal price;
    private boolean enabled;

    public String getName() {
        return name;
    }

    public CourseEntity setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public CourseEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public CourseEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }
}
