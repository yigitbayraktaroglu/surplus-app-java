package com.example.surplusapp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

public class mController implements Initializable  {
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
    public Button takipEtikllerin;
    @FXML
    public TableView<song> songTableView;
    @FXML
    public TableColumn<song,String> adCol;
    @FXML
    public TableColumn <song,String>artistCol;
    @FXML
    public TableColumn<song,String> lenCol;
    @FXML
    public TableColumn<song,String>catCol;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adCol.setCellValueFactory(new PropertyValueFactory<>("songName"));
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        lenCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        songTableView.setItems(observableList);
    }
    ObservableList<song> observableList= FXCollections.observableArrayList(
            new song(1,"a","b","c","f")
    );


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
                observableList.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void getUserPlaylist(){
        String sql = "SELECT * FROM playlists WHERE userID="+currUser.getUserID();
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs  = pstmt.executeQuery()){
            while (rs.next()){

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
