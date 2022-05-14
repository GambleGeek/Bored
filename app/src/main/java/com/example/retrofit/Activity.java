package com.example.retrofit;

import com.google.gson.annotations.SerializedName;

public class Activity {

    private String activity;

    private String type;

    private int participants;

    private int price;

    private String link;

    private double key;

    private int accessibility;

    public String getActivity() {
        return activity;
    }

    public String getType() {
        return type;
    }

    public int getParticipants() {
        return participants;
    }

    public int getPrice() {
        return price;
    }

    public String getLink() {
        return link;
    }

    public double getKey() {
        return key;
    }

    public int getAccessibility() {
        return accessibility;
    }
}
