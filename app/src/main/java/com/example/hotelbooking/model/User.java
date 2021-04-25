package com.example.hotelbooking.model;

import java.util.ArrayList;
import java.util.HashMap;

public class User {
    private int userID;
    private String username;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String avatar;
    private HashMap<String, ArrayList<Room>> history = new HashMap();

    public User(int userID, String username, String birthday, String gender, String phoneNumber, String email, String avatar, HashMap<String, ArrayList<Room>> history) {
        this.userID = userID;
        this.username = username;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
        this.history = history;
    }

    public User() {
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getUserID() {
        return this.userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
