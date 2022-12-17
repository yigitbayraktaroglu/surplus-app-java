package com.example.surplusapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainController  {
    public  User currUser;
    @FXML
    AnchorPane root;
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


    public  void setUser(User user) {
        currUser=user;
        System.out.println(currUser.getUserName());
    }

    @FXML
    public void cikis() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("giris.fxml"));
        root.getChildren().setAll(pane);
    }


}
