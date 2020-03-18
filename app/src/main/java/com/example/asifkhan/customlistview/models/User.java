package com.example.asifkhan.customlistview.models;

import java.util.List;
import java.util.UUID;

public class User {
    private String id, name, email, password;
    private List<Pill> pillList;

    public User() {
    }

    public User(String name, String email, String password, List<Pill> pillList) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.password = password;
        this.pillList = pillList;
    }

    public User(String id, String name, String email, String password, List<Pill> pillList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.pillList = pillList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Pill> getPillList() {
        return pillList;
    }

    public void setPillList(List<Pill> pillList) {
        this.pillList = pillList;
    }


}
