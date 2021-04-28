package com.example.hotelbooking.model;

import java.io.Serializable;

public class HotelFilter implements Serializable {

    private RoomType roomType = RoomType.SINGLE;
    private RoomPrice roomPrice = RoomPrice.F10_T30;
    private int amount;

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public RoomPrice getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(RoomPrice roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
