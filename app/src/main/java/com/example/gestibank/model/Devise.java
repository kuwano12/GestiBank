package com.example.gestibank.model;

import com.example.gestibank.R;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class Devise {
    @SerializedName("rates")
    private SortedMap<String, Double> rates;
    @SerializedName("base")
    private String base;
    @SerializedName("date")
    private String date;
    public Devise(){}

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SortedMap<String, Double> getRates() {
        return rates;
    }

    public void setRates(SortedMap<String, Double> rates) {
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "Devise{" +
                "rates=" + rates +
                ", base='" + base + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
