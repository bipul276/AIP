package com.blooddonation.models;

public class Donor {
    private String name;
    private String bloodGroup;
    private String contact;
    private String email;
    private String city;

    public Donor(String name, String bloodGroup, String contact, String email, String city) {
        this.name = name;
        this.bloodGroup = bloodGroup;
        this.contact = contact;
        this.email = email;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }

    public String getCity() {
        return city;
    }
}
