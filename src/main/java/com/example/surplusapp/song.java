package com.example.surplusapp;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.beans.property.SimpleStringProperty;

public class song {
    private SimpleIntegerProperty songID;
    private SimpleStringProperty categoryName;
    private  SimpleStringProperty artistName;
    private SimpleStringProperty songName;
    private SimpleStringProperty length;

    public song(int songID, String categoryName, String artistName, String songName, String length) {
        this.songID = new SimpleIntegerProperty(songID);
        this.categoryName = new SimpleStringProperty(categoryName);
        this.artistName = new SimpleStringProperty(artistName);
        this.songName = new SimpleStringProperty(songName);
        this.length = new SimpleStringProperty(length);
    }
    public song(){}

    public int getSongID() {
        return songID.get();
    }

    public void setSongID(int songID) {
        this.songID = new SimpleIntegerProperty(songID);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = new SimpleStringProperty(categoryName);
    }

    public String getArtistName() {
        return artistName.get();
    }

    public void setArtistName(String artistName) {
        this.artistName = new SimpleStringProperty(artistName);
    }

    public String getSongName() {
        return songName.get();
    }

    public void setSongName(String songName) {
        this.songName = new SimpleStringProperty(songName);
    }

    public String getLength() {
        return length.get();
    }

    public void setLength(String length) {
        this.length = new SimpleStringProperty(length);
    }
}
