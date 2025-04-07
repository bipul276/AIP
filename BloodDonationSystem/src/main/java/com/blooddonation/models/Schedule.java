package com.blooddonation.models;

public class Schedule {
    private String email;
    private String datetime;
    private String status;

    public Schedule(String email, String datetime, String status) {
        this.email = email;
        this.datetime = datetime;
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getStatus() {
        return status;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // 👇 This was missing:
    public String getContact() {
        return email;
    }
}
