package com.example.demogateway.model;

public class GatewayUser {
    private Integer userId;
    private String fullName;
    private String contact;

    public GatewayUser() {
    }

    public GatewayUser(Integer userId, String fullName, String contact) {
        this.userId = userId;
        this.fullName = fullName;
        this.contact = contact;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
