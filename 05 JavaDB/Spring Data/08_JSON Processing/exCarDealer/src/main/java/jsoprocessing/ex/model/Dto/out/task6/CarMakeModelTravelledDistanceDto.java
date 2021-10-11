package jsoprocessing.ex.model.Dto.out.task6;

import com.google.gson.annotations.Expose;

public class CarMakeModelTravelledDistanceDto {
    @Expose
    private String Make;
    @Expose
    private String Model;
    @Expose
    private Long TravelledDistance;

    public CarMakeModelTravelledDistanceDto() {
    }

    public String getMake() {
        return Make;
    }

    public void setMake(String make) {
        this.Make = make;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String model) {
        this.Model = model;
    }

    public Long getTravelledDistance() {
        return TravelledDistance;
    }

    public void setTravelledDistance(Long travelledDistance) {
        this.TravelledDistance = travelledDistance;
    }
}
