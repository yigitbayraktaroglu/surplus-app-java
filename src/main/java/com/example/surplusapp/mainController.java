package com.example.surplusapp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class mainController implements Initializable {

    @FXML
    Button cikis;
    @FXML
    Button sarkiSil;
    @FXML
    Button sarkiEkle;
    @FXML
    Button playlistGoruntule;
    @FXML
    Button yeniPlaylist;
    @FXML
    Button takipEtikllerin;
    @FXML
    Label label;
    public  User currUser;


    public  void setUser(User user) {
        currUser=user;

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        
    }
}
