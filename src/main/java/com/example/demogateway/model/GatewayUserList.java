package com.example.demogateway.model;

import java.util.List;

public class GatewayUserList {
    private List<GatewayUser> users;

    public GatewayUserList() {
    }

    public GatewayUserList(List<GatewayUser> users) {
        this.users = users;
    }

    public List<GatewayUser> getUsers() {
        return users;
    }

    public void setUsers(List<GatewayUser> users) {
        this.users = users;
    }
}
