package com.example.surplusapp;

public class song {
    private  int songID;
    private  String categoryName;
    private  String artistName;
    private String songName;
    private String length;

    public song(int songID, String categoryName, String artistName, String songName, String length) {
        this.songID = songID;
        this.categoryName = categoryName;
        this.artistName = artistName;
        this.songName = songName;
        this.length = length;
    }
    public song(){}

    public int getSongID() {
        return songID;
    }

    public void setSongID(int songID) {
        this.songID = songID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
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
