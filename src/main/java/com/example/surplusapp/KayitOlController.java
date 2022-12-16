package com.example.surplusapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import static com.example.surplusapp.dbConn.Connect;

public class KayitOlController {
    @FXML
     AnchorPane root;
    @FXML
     Button geriDon;
    @FXML
     Button kayitOl;
    @FXML
     TextField isim;
    @FXML
     TextField mail;
    @FXML
     PasswordField pass;
    @FXML
     Label feedback;
    @FXML
    public void geriDon() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("giris.fxml"));
        root.getChildren().setAll(pane);
    }
    @FXML
    public void kayitOl() throws SQLException, IOException, InterruptedException {
        String userName=isim.getText();
        String userMail=mail.getText();
        String userPass=pass.getText();

        if(Objects.equals(userName, "") || Objects.equals(userMail, "") || Objects.equals(userPass, "")){
            isim.clear();
            mail.clear();
            pass.clear();
            feedback.setText("Tekrar Giriş Yapınız.");
        }else if(eslesme(userMail,userName)){
            isim.clear();
            mail.clear();
            pass.clear();
            feedback.setText("Bu isim veya mail zaten bulunmaktadır");
        }
        else {

            String sql = "INSERT INTO users (userName,userMail,userPass)" +
                    "VALUES('"+userName+"','"+userMail+"','"+userPass+"')";
            try (Connection conn = Connect();
                 PreparedStatement pstmt  = conn.prepareStatement(sql);
            ){
                pstmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            geriDon();


        }




    }
    public boolean eslesme(String userMail,String userName){
        String sql = "SELECT userName,userMail FROM users WHERE userMail='"+userMail+"'OR userName='"+userName+"'";
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs  = pstmt.executeQuery()){
            return rs.next();

        } catch (SQLException e) {
            return false;

        }


    }



}
