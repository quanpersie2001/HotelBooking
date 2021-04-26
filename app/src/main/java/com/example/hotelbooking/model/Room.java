package com.example.hotelbooking.model;

public class Room {

    private String id, name, type, purl;
    private double price;
    private int status;
    private double square;

    Room() {};

    public Room(String id, String name, String type, String purl, double price, int status, double square) {
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

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
