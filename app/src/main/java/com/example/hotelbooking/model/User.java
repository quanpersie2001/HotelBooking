package com.example.hotelbooking.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User extends Person {

    private String userID;
    private String username;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String avatar;
    private HashMap<String, ArrayList<Room>> history = new HashMap();


    public User(String userID, String username, String birthday, String gender, String phoneNumber, String email, String avatar) {
        super(username, birthday, gender, phoneNumber, email, avatar);
        this.userID = userID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
