package jsoprocessing.ex.model.Dto.out;

import com.google.gson.annotations.Expose;

public class SupplierIdNamePartCount {
    @Expose
    private Long id;
    @Expose
    private String name;
    @Expose
    private int partsCount;

    public SupplierIdNamePartCount() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPartsCount() {
        return partsCount;
    }

    public void setPartsCount(int partsCount) {
        this.partsCount = partsCount;
    }
}
