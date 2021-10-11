package ex.xmlprocessing.Dto.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryRootDto {
    @XmlElement(name="category")
    List<CategoryStatisticDto> categories;

    public CategoryRootDto() {
    }

    public List<CategoryStatisticDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryStatisticDto> categories) {
        this.categories = categories;
    }
}
