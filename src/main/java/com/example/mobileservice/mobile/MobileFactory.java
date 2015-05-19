package com.example.mobileservice.mobile;

import java.util.Random;

public class MobileFactory {

    private static final Random random = new Random();

    public Mobile newRandomMobile() {
        Manufacturer manufacturer = Manufacturer.values()[random.nextInt(Manufacturer.values().length)];

        Mobile mobile = new Mobile();

        mobile.manufacturer = manufacturer;
        mobile.model = manufacturer.getModelList()[random.nextInt(manufacturer.getModelList().length)];
        mobile.display = new Part(PartType.DISPLAY, random.nextBoolean(), manufacturer.getName() + " Display");
        mobile.microphone = new Part(PartType.MICROPHONE, random.nextBoolean(), manufacturer.getName() + " Microphone");
        mobile.motherBoard = new Part(PartType.MOTHERBOARD, random.nextBoolean(), manufacturer.getName() + " " + mobile.model + " Motherboard");
        mobile.powerSwitch = new Part(PartType.POWER_SWITCH, random.nextBoolean(), manufacturer.getName() + " Power Switch");
        mobile.speaker = new Part(PartType.SPEAKER, random.nextBoolean(), manufacturer.getName() + " Speaker");
        mobile.volumeButtons = new Part(PartType.VOLUME_BUTTONS, random.nextBoolean(), manufacturer.getName() + " Volume Buttons");

        return mobile;
    }
}
