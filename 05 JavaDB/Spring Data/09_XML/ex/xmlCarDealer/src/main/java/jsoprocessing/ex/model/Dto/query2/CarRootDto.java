package jsoprocessing.ex.model.Dto.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarRootDto {

    @XmlElement(name="car")
    private List<CarInfoDto> cars;

    public CarRootDto() {
    }

    public List<CarInfoDto> getCars() {
        return cars;
    }

    public void setCars(List<CarInfoDto> cars) {
        this.cars = cars;
    }
}

