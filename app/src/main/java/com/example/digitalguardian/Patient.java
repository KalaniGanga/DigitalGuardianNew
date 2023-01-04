package com.example.digitalguardian;

public class Patient {
    private String name;
    private int roomNumber;
    private int temperature;
    private int age;
    private String desc;
    private String gender;
    private String index;
    private int src;
    private int bpm;
    private String userName;
    private String password;

    public Patient(){};

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

    public void setName(String name) {
        this.name = name;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setSrc(int src) {
        this.src = src;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

