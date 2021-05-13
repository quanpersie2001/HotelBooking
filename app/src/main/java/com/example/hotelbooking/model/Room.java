package com.example.hotelbooking.model;

import java.io.Serializable;

public class Room implements Serializable {

    private String hotelID, name, type, purl, price;
    private String status;
    private String square;

    public Room() {

    }

    public Room(String hotelID, String name, String type, String purl, String price, String status, String square) {
        this.hotelID = hotelID;
        this.name = name;
        this.type = type;
        this.purl = purl;
        this.price = price;
        this.status = status;
        this.square = square;
    }

    public String getHotelID() {
        return hotelID;
    }

    public void setHotelID(String hotelID) {
        this.hotelID = hotelID;
    }

    public String getPurl() {
        return purl;
    }

    public void setPurl(String purl) {
        this.purl = purl;
    }

    public String getSquare() {
        return square;
    }

    public void setSquare(String square) {
        this.square = square;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
