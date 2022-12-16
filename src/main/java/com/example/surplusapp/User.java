package com.example.surplusapp;

public class User {
    private int userID;
    private String userName;
    private String userMail;
    private String userPass;

    public User(int userID, String userName, String userMail, String userPass) {
        this.userID = userID;
        this.userName = userName;
        this.userMail = userMail;
        this.userPass = userPass;
    }

    public User() {
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
