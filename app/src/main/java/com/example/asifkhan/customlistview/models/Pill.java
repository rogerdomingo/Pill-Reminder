package com.example.asifkhan.customlistview.models;

public class Pill {
    private String name;
    private String date;

    public Pill() {
    }

    public Pill(String name) {
        this.name = name;
    }

    public Pill(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
