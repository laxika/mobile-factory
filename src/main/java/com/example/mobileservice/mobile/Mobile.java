package com.example.mobileservice.mobile;

import java.util.HashMap;

public class Mobile {
    private Manufacturer manufacturer;

    private HashMap<PartType, Part> partMap = new HashMap<>();

    private String model;
    private String otherInfo;

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Part getDisplay() {
        return partMap.get(PartType.DISPLAY);
    }

    public void setDisplay(Part display) {
        partMap.put(PartType.DISPLAY, display);
    }

    public Part getMotherBoard() {
        return partMap.get(PartType.MOTHERBOARD);
    }

    public void setMotherBoard(Part motherBoard) {
        partMap.put(PartType.MOTHERBOARD, motherBoard);
    }

    public Part getKeyboard() {
        return partMap.get(PartType.KEYBOARD);
    }

    public void setKeyboard(Part keyboard) {
        partMap.put(PartType.KEYBOARD, keyboard);
    }

    public Part getMicrophone() {
        return partMap.get(PartType.MICROPHONE);
    }

    public void setMicrophone(Part microphone) {
        partMap.put(PartType.MICROPHONE, microphone);
    }

    public Part getSpeaker() {
        return partMap.get(PartType.SPEAKER);
    }

    public void setSpeaker(Part speaker) {
        partMap.put(PartType.SPEAKER, speaker);
    }

    public Part getVolumeButtons() {
        return partMap.get(PartType.VOLUME_BUTTONS);
    }

    public void setVolumeButtons(Part volumeButtons) {
        partMap.put(PartType.VOLUME_BUTTONS, volumeButtons);
    }

    public Part getPowerSwitch() {
        return partMap.get(PartType.POWER_SWITCH);
    }

    public void setPowerSwitch(Part powerSwitch) {
        partMap.put(PartType.POWER_SWITCH, powerSwitch);
    }

    public String getOtherInfo() {
        return otherInfo;
    }

    public void setOtherInfo(String otherInfo) {
        this.otherInfo = otherInfo;
    }

    @Override
    public String toString() {
        return getManufacturer() + " " + getModel() + ", other Info: " + getOtherInfo();
    }
}
