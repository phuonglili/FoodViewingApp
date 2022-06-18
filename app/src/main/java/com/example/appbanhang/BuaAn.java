package com.example.appbanhang;

public class BuaAn {
    public String name;
    private String overview;
    private int image;
    private int giatien;

    public BuaAn(String name, String overview, int image,int giatien) {
        this.name = name;
        this.overview = overview;
        this.image = image;
        this.giatien= giatien;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }
}
