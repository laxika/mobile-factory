package com.example.mobileservice.mobile;

import java.util.Random;

public class MobileFactory {

    private static final Random random = new Random();

    public Mobile newRandomMobile() {
        Manufacturer manufacturer = Manufacturer.values()[random.nextInt(Manufacturer.values().length)];

        Mobile mobile = new Mobile();

        mobile.setManufacturer(manufacturer);
        mobile.setModel(manufacturer.getModelList()[random.nextInt(manufacturer.getModelList().length)]);

        for (PartType type : PartType.values()) {
            mobile.setDisplay(new Part(PartType.DISPLAY, random.nextBoolean(), manufacturer.getName() + " Display"));
        }
        mobile.setDisplay(new Part(PartType.DISPLAY, random.nextBoolean(), manufacturer.getName() + " Display"));
        mobile.setMicrophone(new Part(PartType.MICROPHONE, random.nextBoolean(), manufacturer.getName() + " Microphone"));
        mobile.setMotherBoard(new Part(PartType.MOTHERBOARD, random.nextBoolean(), manufacturer.getName() + " " + mobile.getModel() + " Motherboard"));
        mobile.setPowerSwitch(new Part(PartType.POWER_SWITCH, random.nextBoolean(), manufacturer.getName() + " Power Switch"));
        mobile.setSpeaker(new Part(PartType.SPEAKER, random.nextBoolean(), manufacturer.getName() + " Speaker"));
        mobile.setVolumeButtons(new Part(PartType.VOLUME_BUTTONS, random.nextBoolean(), manufacturer.getName() + " Volume Buttons"));

        return mobile;
    }
}
