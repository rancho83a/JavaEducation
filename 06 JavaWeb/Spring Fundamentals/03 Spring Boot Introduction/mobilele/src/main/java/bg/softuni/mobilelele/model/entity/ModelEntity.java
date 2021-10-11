package bg.softuni.mobilelele.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private CategoryEnum category;

    @Column(length = 512)
    private String imageUrl;
    private Integer startYear;
    private Integer endYear;

    @ManyToOne
    private BrandEntity brand;

    public ModelEntity() {
    }

    public String getName() {
        return name;
    }

    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(CategoryEnum category) {
        this.category = category;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ModelEntity setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public Integer getStartYear() {
        return startYear;
    }

    public ModelEntity setStartYear(Integer startYear) {
        this.startYear = startYear;
        return this;
    }

    public Integer getEndYear() {
        return endYear;
    }

    public ModelEntity setEndYear(Integer endYear) {
        this.endYear = endYear;
        return this;
    }

    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
    /*
name – a model name.
 category – an enumeration (Car, Buss, Truck, Motorcycle)
 imageUrl – the url of image with size between 8 and 512 characters.
 startYear – a number.
 endYear – a number.
 created – a date and time.
 modified – a date and time.
 brand – a model brand.
 */
}
