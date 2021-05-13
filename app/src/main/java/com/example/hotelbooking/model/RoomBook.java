package com.example.hotelbooking.model;

public class RoomBook extends Room {

    String dateIn, dateOut, userID;
    private String hotelID, name, type, purl, price;
    private String status;
    private String square;

    public RoomBook(String hotelID, String name, String type, String purl, String price, String status, String square, String dateIn, String dateOut) {
        super(hotelID, name, type, purl, price, status, square);
        this.dateIn = dateIn;
        this.dateOut = dateOut;

    }

    public String getDateIn() {
        return dateIn;
    }

    public void setDateIn(String dateIn) {
        this.dateIn = dateIn;
    }

    public String getDateOut() {
        return dateOut;
    }

    public void setDateOut(String dateOut) {
        this.dateOut = dateOut;
    }

}
