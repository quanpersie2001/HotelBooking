package com.example.hotelbooking.model;

public class Hotel {
    String  purl, name, price, address, description;

    Hotel()
    {

    }

    public Hotel(String purl, String name, String price, String address, String description) {
        this.description = description;
        this.purl = purl;
        this.name = name;
        this.price = price;
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
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
