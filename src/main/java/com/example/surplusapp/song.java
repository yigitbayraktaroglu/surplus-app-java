package com.example.surplusapp;

public class song {
    private  int songID;
    private  int categoryID;
    private String songName;
    private String length;

    public song(int songID, int categoryID, String songName, String length) {
        this.songID = songID;
        this.categoryID = categoryID;
        this.songName = songName;
        this.length = length;
    }

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
