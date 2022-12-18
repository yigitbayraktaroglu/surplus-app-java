package com.example.surplusapp;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

public class AdminController {

    @FXML
    AnchorPane root;
    @FXML
    TextField songName;
    @FXML
    TextField length;
    @FXML
    TextField artistName;
    @FXML
    TextField category;
    @FXML
    Label feedbacklbl=new Label();



    public void sarkıEkle(){
        String sName=songName.getText();
        String le=length.getText();
        String aName=artistName.getText();
        String cat=category.getText();
        String sql = "Insert into songs (songName,categoryID,length,artistName) Values ('"+sName+"',(Select categoryID from categories where categoryName='"+cat+"'),'"+le+"','"+aName+"');";
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
        ){
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        songName.clear();
        length.clear();
        artistName.clear();
        category.clear();
        feedbacklbl.setText("Şarkı Eklendi");
    }




    public void cikis() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("giris.fxml"));
        root.getChildren().setAll(pane);
    }


}
