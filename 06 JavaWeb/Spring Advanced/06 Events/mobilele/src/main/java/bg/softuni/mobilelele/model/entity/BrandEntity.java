package bg.softuni.mobilelele.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="brands")
public class BrandEntity extends BaseEntity {

    @Column( unique = true, nullable = false)
    private String name;



    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ModelEntity> models;

    public BrandEntity() {
    }


    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }
}
