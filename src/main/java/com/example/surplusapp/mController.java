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
    public  User currUser=new User();

    ObservableList<song> observableList= FXCollections.observableArrayList();
    ObservableList<playlist> playlistObservableList= FXCollections.observableArrayList();
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
    public TableView<playlist> playlistTableview=new TableView<>();
    @FXML
    public TableColumn<playlist,String> playlistNameCol=new TableColumn<>();
    @FXML
    public TableView<song> songTableView=new TableView<>();
    @FXML
    public TableColumn<song,String> adCol=new TableColumn<>();
    @FXML
    public TableColumn <song,String>artistCol=new TableColumn<>();
    @FXML
    public TableColumn<song,String> lenCol=new TableColumn<>();
    @FXML
    public TableColumn<song,String>catCol=new TableColumn<>();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        adCol.setCellValueFactory(new PropertyValueFactory<>("songName"));
        artistCol.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        lenCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        catCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        playlistNameCol.setCellValueFactory(new PropertyValueFactory<>("playlistName"));
        getallSongs();
        songTableView.setItems(observableList);
        playlistTableview.setItems(playlistObservableList);

    }

    public  void setUser(User user) {
        currUser.setUserID(user.getUserID());
        currUser.setUserMail(user.getUserMail());
        currUser.setUserName(user.getUserMail());
        currUser.setUserPass(user.getUserPass());
        getUserPlaylist(user);
    }

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
    public void getUserPlaylist(User user){
        String id=Integer.toString(user.getUserID());
        String sql = "SELECT * FROM playlists WHERE userID="+id;
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs  = pstmt.executeQuery()){
            while (rs.next()){
                playlist playlist=new playlist();
                playlist.setUserID(rs.getInt("userID"));
                playlist.setPlaylistID(rs.getInt("playlistID"));
                playlist.setPlaylistName(rs.getString("playlistName"));
                playlistObservableList.add(playlist);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public void getPlaylistSong(){

    }

    public void sarkiEkle(){
        ObservableList<song> ekle;
        ObservableList<playlist> playlists;
        playlists=playlistTableview.getSelectionModel().getSelectedItems();
        int plid=playlists.get(0).getPlaylistID();
        ekle=songTableView.getSelectionModel().getSelectedItems();
        int songID=ekle.get(0).getSongID();
        String sql = "INSERT INTO playlistsongs (playlistID,songID)" +
                "VALUES('"+plid+"','"+songID+"')";
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
        ){
            pstmt.executeUpdate();
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
