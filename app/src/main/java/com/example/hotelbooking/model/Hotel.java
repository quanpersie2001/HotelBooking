package com.example.hotelbooking.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Hotel {
    String  purl, name, price, address, description, id;
    double rank;
    String key;
    List<Room> rooms = new ArrayList<>();
    Hotel()
    {

    }

    public Hotel(String purl, String name, String price, String address, String description, double rank) {
        this.description = description;
        this.purl = purl;
        this.name = name;
        this.price = price;
        this.address = address;
        this.rank = rank;
        this.id = UUID.randomUUID().toString();
    }

    public Hotel(String id,String purl, String name, String price, String address, String description, double rank) {
        this.description = description;
        this.purl = purl;
        this.name = name;
        this.price = price;
        this.address = address;
        this.rank = rank;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getRank() {
        return rank;
    }

    public void setRank(double rank) {
        this.rank = rank;
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

    public String getKey(){
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
