package com.example.surplusapp;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class playlist {
    private SimpleIntegerProperty playlistID;
    private SimpleIntegerProperty userID;
    private SimpleStringProperty playlistName;

    public playlist(int playlistID, int userID, String playlistName) {
        this.playlistID = new SimpleIntegerProperty(playlistID);
        this.userID = new SimpleIntegerProperty(userID);
        this.playlistName = new SimpleStringProperty(playlistName);
    }

    public playlist() {

    }

    public int getPlaylistID() {
        return playlistID.get();
    }

    public void setPlaylistID(int playlistID) {
        this.playlistID = new SimpleIntegerProperty(playlistID);
    }

    public int getUserID() {
        return userID.get();
    }

    public void setUserID(int userID) {
        this.userID =  new SimpleIntegerProperty(userID);
    }

    public String getPlaylistName() {
        return playlistName.get();
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = new SimpleStringProperty(playlistName);
    }
}
