package com.example.hotelbooking.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person{
    private int userID;
    private String username;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String avatar;
    private HashMap<String, ArrayList<Room>> history = new HashMap();

    public User(int userID, String username, String birthday, String gender, String phoneNumber, String email, String avatar, HashMap<String, ArrayList<Room>> history) {
        super(username, birthday, gender, phoneNumber, email, avatar);
        this.userID = userID;
        this.history = history;
    }

    public User() {
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

}
