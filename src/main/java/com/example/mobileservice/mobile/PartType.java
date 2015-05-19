package com.example.mobileservice.mobile;

public enum PartType {
    DISPLAY("Display"),
    KEYBOARD("Keyboard"),
    MOTHERBOARD("Motherboard"),
    MICROPHONE("Microphone"),
    SPEAKER("Speaker"),
    VOLUME_BUTTONS("Volume Buttons"),
    POWER_SWITCH("Power Switch");

    private String name;

    PartType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
