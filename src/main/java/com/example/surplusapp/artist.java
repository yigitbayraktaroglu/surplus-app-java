package com.example.surplusapp;

public class artist {
    private int artistID;
    private String artistName;

    public artist(int artistID, String artistName) {
        this.artistID = artistID;
        this.artistName = artistName;
    }

    public int getArtistID() {
        return artistID;
    }

    public void setArtistID(int artistID) {
        this.artistID = artistID;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
