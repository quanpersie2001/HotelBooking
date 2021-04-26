package com.example.hotelbooking.model;


public class Person {
    private String username;
    private String birthday;
    private String gender;
    private String phoneNumber;
    private String email;
    private String avatar;

    public Person(String username, String birthday, String gender, String phoneNumber, String email, String avatar) {
        this.username = username;
        this.birthday = birthday;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.avatar = avatar;
    }

    public Person(){

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

    public String toString() {
        return String.format("Your Information: \n"
                        + "Username: %s \nGender: %s \nPhone Number: %s \nGmail: %s",
                username, gender, phoneNumber, email);
    }

}
