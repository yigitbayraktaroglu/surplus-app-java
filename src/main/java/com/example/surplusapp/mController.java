package com.example.surplusapp;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static com.example.surplusapp.dbConn.Connect;

public class mController implements Initializable  {
    public static User currUser=new User();
    public playlist pl=new playlist(10,9,"test");

    ObservableList<song> observableList= FXCollections.observableArrayList();
    ObservableList<song> plSongobservableList= FXCollections.observableArrayList();
    ObservableList<playlist> playlistObservableList= FXCollections.observableArrayList();

    @FXML
    AnchorPane root;
    @FXML
    Button cikis;
    @FXML
    Button sarkiSil;
    @FXML
    Label lbl=new Label();
    @FXML
    Button sarkiEkle;
    @FXML
    Button playlistGoruntule;
    @FXML
    Button yeniPlaylist;
    @FXML
    Button olustur;
    @FXML
    TextField playlistName;

    @FXML
    public TableView<song> plsongTableView=new TableView<>();
    @FXML
    public TableColumn<song,String> pladCol=new TableColumn<>();
    @FXML
    public TableColumn <song,String> plartistCol=new TableColumn<>();
    @FXML
    public TableColumn<song,String> pllenCol=new TableColumn<>();
    @FXML
    public TableColumn<song,String> plcatCol=new TableColumn<>();


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

        pladCol.setCellValueFactory(new PropertyValueFactory<>("songName"));
        plartistCol.setCellValueFactory(new PropertyValueFactory<>("artistName"));
        pllenCol.setCellValueFactory(new PropertyValueFactory<>("length"));
        plcatCol.setCellValueFactory(new PropertyValueFactory<>("categoryName"));

        playlistNameCol.setCellValueFactory(new PropertyValueFactory<>("playlistName"));

        getallSongs();
        getUserPlaylist(currUser);

        songTableView.setItems(observableList);

        plsongTableView.setItems(plSongobservableList);

        playlistTableview.setItems(playlistObservableList);


    }

    public static void setUser(User user) {
        currUser.setUserID(user.getUserID());
        currUser.setUserMail(user.getUserMail());
        currUser.setUserName(user.getUserMail());
        currUser.setUserPass(user.getUserPass());

    }

    public void getallSongs(){
        String sql = "SELECT songs.songID, artistName, categories.categoryName,songName,length " +
                "FROM songs INNER JOIN categories ON songs.categoryID = categories.categoryID";
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
        plSongobservableList.clear();
        ObservableList<playlist> playlists;
        playlists=playlistTableview.getSelectionModel().getSelectedItems();
        int plid=playlists.get(0).getPlaylistID();
        plSark??Sayici(plid);
        String sql = "SELECT songs.songID,categories.categoryName,songs.songName,songs.length,songs.artistName FROM playlists RIGHT JOIN playlistsongs ON playlistsongs.playlistID=playlists.playlistID RIGHT JOIN songs ON songs.songID=playlistsongs.songID INNER JOIN categories ON categories.categoryID=songs.categoryID WHERE playlists.playlistID="+plid;
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
                plSongobservableList.add(song);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public void deletePlaylistSong(){
       ObservableList<playlist> playlists;
        playlists=playlistTableview.getSelectionModel().getSelectedItems();
        ObservableList<song> song;
        song=plsongTableView.getSelectionModel().getSelectedItems();
        int plid=playlists.get(0).getPlaylistID();
        int sid=song.get(0).getSongID();

        String sql = "DELETE FROM playlistsongs where songID="+sid+" and playlistID="+plid;
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
        ){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        getPlaylistSong();

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
        getPlaylistSong();
    }

    public void newPlaylist(){
        olustur.setVisible(true);
        playlistName.setVisible(true);
    }

    public void createPlaylist(){
        String plname=playlistName.getText();

        String id=Integer.toString(currUser.getUserID());

        String sql = "INSERT INTO playlists(userID,playlistName) VALUES("+id+",'"+plname+"')";
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
        ){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        olustur.setVisible(false);
        playlistName.setVisible(false);

    }

    public void plSark??Sayici(int plid){
        String songName=null;
        int sayi=0;

        String sql = "SELECT playlistName,count(songID) as SarkiSayisi FROM surplus.playlistsongs INNER JOIN  playlists ON playlists.playlistID=playlistsongs.playlistID  group by playlistsongs.playlistID HAVING playlistsongs.playlistID="+plid;
            try (Connection conn = Connect();
                 PreparedStatement pstmt  = conn.prepareStatement(sql);
                 ResultSet rs  = pstmt.executeQuery()){
                while (rs.next()){
                    songName=rs.getString(1);
                    sayi=rs.getInt(2);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            lbl.setText("Playlist Ad??: "+songName+" Toplam ??ark?? Say??s??: "+sayi);

        }

    @FXML
    public void cikis() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("giris.fxml"));
        root.getChildren().setAll(pane);
    }


}
