package com.example.admin.savelife;

public class SingleRowHospital {

    private String name;
    private String phoneNumber;

    public SingleRowHospital(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }


}
