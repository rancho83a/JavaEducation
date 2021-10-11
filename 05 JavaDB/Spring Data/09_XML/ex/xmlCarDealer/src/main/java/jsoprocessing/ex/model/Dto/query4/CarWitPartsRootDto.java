package jsoprocessing.ex.model.Dto.query4;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWitPartsRootDto {

    @XmlElement(name="car")
    private List<CarInfoWithPartsDto> cars;

    public CarWitPartsRootDto() {
    }
    public List<CarInfoWithPartsDto> getCars() {
        return cars;
    }

    public void setCars(List<CarInfoWithPartsDto> cars) {
        this.cars = cars;
    }
}

