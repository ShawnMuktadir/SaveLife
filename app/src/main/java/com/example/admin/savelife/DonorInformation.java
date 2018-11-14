package com.example.admin.savelife;

public class DonorInformation {

    private String donorId;
    private String donorName;
    private String donorPhoneNumber;
    private String donorBloodGroup;
    private String donorLocation;

    public DonorInformation() {

    }

    public DonorInformation(String donorId, String donorName, String donorPhoneNumber, String donorBloodGroup, String donorLocation) {
        this.donorId = donorId;
        this.donorName = donorName;
        this.donorPhoneNumber = donorPhoneNumber;
        this.donorBloodGroup = donorBloodGroup;
        this.donorLocation = donorLocation;
    }

    public String getDonorId() {
        return donorId;
    }

    public String getDonorName() {
        return donorName;
    }

    public String getDonorPhoneNumber() {
        return donorPhoneNumber;
    }

    public String getDonorBloodGroup() {
        return donorBloodGroup;
    }

    public String getDonorLocation() {
        return donorLocation;
    }


}
