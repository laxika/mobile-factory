package com.example.mobileservice.mobile;

import com.example.mobileservice.mobile.Mobile.MobileBuilder;

import java.util.Random;

public class MobileFactory {

    private static final Random random = new Random();

    public Mobile newRandomMobile() {
        Manufacturer manufacturer = Manufacturer.values()[random.nextInt(Manufacturer.values().length)];

        MobileBuilder mobileBuilder = new MobileBuilder();

        String model = manufacturer.getModelList()[random.nextInt(manufacturer.getModelList().length)];

        mobileBuilder.setManufacturer(manufacturer);
        mobileBuilder.setModel(model);

        for (PartType type : PartType.values()) {
            //TODO: remove this if
            if (type == PartType.MOTHERBOARD) {
                mobileBuilder.setPart(type, new Part(PartType.MOTHERBOARD, random.nextBoolean(), manufacturer.getName() + " " + model + " " + type.getName()));
            } else {
                mobileBuilder.setPart(type, new Part(type, random.nextBoolean(), manufacturer.getName() + " " + type.getName()));
            }
        }

        return mobileBuilder.createMobile();
    }
}
