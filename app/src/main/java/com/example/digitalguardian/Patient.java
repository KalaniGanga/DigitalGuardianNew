package com.example.digitalguardian;

public class Patient {
    private String name;
    private int roomNumber;
    private int temperature;
    private String desc;
    private String gender;
    private String index;
    private int src;
    private int bpm;

    public String getName() {
        return name;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getDesc() {
        return desc;
    }

    public String getGender() {
        return gender;
    }

    public String getIndex() {
        return index;
    }

    public int getSrc() {
        return src;
    }

    public int getBpm() {
        return bpm;
    }

    public Patient(String index, String name, int roomNumber, int temperature, String desc, String gender, int src, int bpm) {
        this.name = name;
        this.roomNumber = roomNumber;
        this.temperature = temperature;
        this.desc = desc;
        this.gender = gender;
        this.index = index;
        this.src = src;
        this.bpm = bpm;

    }

}

