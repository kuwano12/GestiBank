package com.example.gestibank.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviseList {
    private List<Devise> devises;
    public DeviseList(){}

    public DeviseList(List<Devise> devises) {
        this.devises = devises;
    }

    public List<Devise> getDevises() {
        return devises;
    }

    public void setDevises(List<Devise> devises) {
        this.devises = devises;
    }

    @Override
    public String toString() {
        return "DeviseList{" +
                "devises=" + devises +
                '}';
    }
}
