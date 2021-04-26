package com.example.hotelbooking.model;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

public class Admin extends Person {
    private int adminID;
    private String username;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String avatar;

    private HashMap<String, Room> roomList = new HashMap<>();

    public Admin(int adminID, String username, String birthday, String gender, String phoneNumber, String email, String avatar, HashMap<String, Room> roomList) {
        super(username, birthday, gender, phoneNumber, email, avatar);
        this.adminID = adminID;
        this.roomList = roomList;
    }

    public Admin() {

    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("\nMangementID: %d", adminID);
    }

    // Add a room in database
    public void addRoom(String roomId, Room room){
        roomList.put(roomId, room);
    }

    // Delete a room from database
    public void deleteRoom(String roomId) {
        roomList.remove(roomId);
    }

    // Update a room in database
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void updateRoom(String roomId, Room room) {
        roomList.replace(roomId, room);
    }


    // Function for get a information room
    public void getRoom(String roomID) {
        System.out.println("Information for room " + roomID +":\n");
        System.out.println(roomList.get(roomID));
    }
}
