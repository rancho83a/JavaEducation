package jsoprocessing.ex.model.Dto.query4;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarInfoWithPartsDto {

    @XmlAttribute
    private String make;
    @XmlAttribute
    private String model;
    @XmlAttribute(name = "travelled-distance")
    private String travelledDistance;
    @XmlElementWrapper(name ="parts")
    private List<PartNamePriceDto> parts;

    public List<PartNamePriceDto> getParts() {
        return parts;
    }

    public void setParts(List<PartNamePriceDto> parts) {
        this.parts = parts;
    }



    public CarInfoWithPartsDto() {
    }


    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTravelledDistance() {
        return travelledDistance;
    }

    public void setTravelledDistance(String travelledDistance) {
        this.travelledDistance = travelledDistance;
    }
}
