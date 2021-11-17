package com.example.aop.sla;

public class Driver {
    private String name;
    private String licenseCategory;

    public Driver(String name, String licenseCategory) {
        this.name = name;
        this.licenseCategory = licenseCategory;
    }

    public String getName() {
        return name;
    }

    public String getLicenseCategory() {
        return licenseCategory;
    }
}
