package com.example.gestibank.model;

public class Rate {
    public String deviseName;
    public Double devisePrice;

    public Rate(){}

    public Rate(String deviseName, Double devisePrice) {
        this.deviseName = deviseName;
        this.devisePrice = devisePrice;
    }

    public String getDeviseName() {
        return deviseName;
    }

    public void setDeviseName(String deviseName) {
        this.deviseName = deviseName;
    }

    public Double getDevisePrice() {
        return devisePrice;
    }

    public void setDevisePrice(Double devisePrice) {
        this.devisePrice = devisePrice;
    }

    @Override
    public String toString() {
        return "Rate{" +
                "deviseName='" + deviseName + '\'' +
                ", devisePrice=" + devisePrice +
                '}';
    }
}
