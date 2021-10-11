package softuni.exam.instagraphlite.models.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.*;

public class PictureSeedDto {
    @Expose
    private String path;
    @Expose
    private double size;


    public PictureSeedDto() {
    }

    @NotBlank
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @DecimalMin(value = "500.00")
    @DecimalMax(value = "60000.00")
    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }
}
