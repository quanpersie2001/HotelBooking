package com.example.hotelbooking.model;

public class HotelModel {
    int image;
    String name, price, address;

    public HotelModel(int image, String name, String price, String address) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
