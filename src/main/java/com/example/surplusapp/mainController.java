package com.example.surplusapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.surplusapp.dbConn.Connect;

public class mainController  {
    public  User currUser;
    public ArrayList songsArray=new ArrayList();
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
    public void getallSongs(){
        String sql = "SELECT songs.songID, artistName, categories.categoryName,songName,length FROM songs INNER JOIN categories ON songs.categoryID = categories.categoryID";
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs  = pstmt.executeQuery()){
            while (rs.next()){
                song song=new song();
                song.setSongID(rs.getInt("songID"));
                song.setSongName(rs.getString("songName"));
                song.setLength(rs.getString("length"));
                song.setArtistName(rs.getString("artistName"));
                song.setCategoryName(rs.getString("categoryName"));
                songsArray.add(song);
            }
        } catch (SQLException e) {
        e.printStackTrace();
        }

    }

    @FXML
    public void cikis() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("giris.fxml"));
        root.getChildren().setAll(pane);
    }


}
