package jsoprocessing.ex.model.Dto.out.task4;

import com.google.gson.annotations.Expose;

import java.util.List;
import java.util.Set;


public class CarExportDto {
    @Expose
    private CarMakeModelDistanceDto car;
    @Expose
    private List<PartNamePriceDto> parts;

    public CarExportDto() {
    }

    public CarMakeModelDistanceDto getCar() {
        return car;
    }

    public void setCar(CarMakeModelDistanceDto car) {
        this.car = car;
    }

    public List<PartNamePriceDto> getParts() {
        return parts;
    }

    public void setParts(List<PartNamePriceDto> parts) {
        this.parts = parts;
    }
}
