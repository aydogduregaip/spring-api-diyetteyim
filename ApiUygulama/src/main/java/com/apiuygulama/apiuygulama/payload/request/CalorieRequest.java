package com.apiuygulama.apiuygulama.payload.request;

public class CalorieRequest {
    private String cinsiyet;
    private int kilo;
    private int boy;
    private int yas;
    private double aktivite;
    private double kilotype;

    public String getCinsiyet() {
        return cinsiyet;
    }

    public void setCinsiyet(String cinsiyet) {
        this.cinsiyet = cinsiyet;
    }

    public int getKilo() {
        return kilo;
    }

    public void setKilo(int kilo) {
        this.kilo = kilo;
    }

    public int getBoy() {
        return boy;
    }

    public void setBoy(int boy) {
        this.boy = boy;
    }

    public int getYas() {
        return yas;
    }

    public void setYas(int yas) {
        this.yas = yas;
    }

    public double getAktivite() {
        return aktivite;
    }

    public void setAktivite(double aktivite) {
        this.aktivite = aktivite;
    }

    public double getKilotype() {
        return kilotype;
    }

    public void setKilotype(double kilotype) {
        this.kilotype = kilotype;
    }
}
