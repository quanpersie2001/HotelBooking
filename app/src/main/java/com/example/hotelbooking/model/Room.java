package com.example.hotelbooking.model;

public class Room {

    private String id, name, type, purl, price;
    private String status;
    private String square;

    Room() {};

    public Room(String id, String name, String type, String purl, String price, String status, String square) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.purl = purl;
        this.price = price;
        this.status = status;
        this.square = square;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
