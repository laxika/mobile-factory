package com.example.mobileservice.mobile;

public enum Manufacturer {
    
    SAMSUNG(
            "Samsung",
            new String[] {"Galaxy S3", "Galaxy S4", "Galaxy Note"}
    ),
    APPLE (
            "Apple",
            new String[] {"iPhone 4s", "iPhone 5s", "iPhone5c"}
    ),
    HTC(
            "HTC",
            new String[] {"One", "Desire 610", "One Mini"}
    ),
    HUAWEI(
            "Huawei",
            new String[] {"Ascend P7", "Ascend P2", "Ascend G630"}
    );

    private final String name;
    private final String[] modelList;

    Manufacturer(String name, String[] modelList) {
        this.name = name;
        this.modelList = modelList;
    }

    public String[] getModelList() {
        return modelList;
    }

    public String getName() {
        return name;
    }
}
