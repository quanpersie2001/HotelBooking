package com.example.hotelbooking.model;

public class Admin extends Person {

    private String adminID;
    private String username;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String avatar;


    public Admin(String adminID, String username, String birthday, String gender, String phoneNumber, String email, String avatar) {
        super(username, birthday, gender, phoneNumber, email, avatar);
        this.adminID = adminID;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }
}
