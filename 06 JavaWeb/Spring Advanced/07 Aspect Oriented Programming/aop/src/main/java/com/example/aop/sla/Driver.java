package com.example.aop.sla;

public class Driver {
    private String name;
    private String licenseCategory;



    public String getName() {
        return name;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }

    public Driver setName(String name) {
        this.name = name;
        return this;
    }

    public Driver setLicenseCategory(String licenseCategory) {
        this.licenseCategory = licenseCategory;
        return this;
    }
}
