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
import java.time.temporal.Temporal;
import java.util.Objects;

import static com.example.surplusapp.dbConn.Connect;

public class girisController extends mController{
    @FXML
    private AnchorPane root;
    @FXML
     Button giris;
    @FXML
     Button kayıtOl;
    @FXML
     Label feedback;
    @FXML
     public TextField mail;
    @FXML
     PasswordField pass;
    @FXML
    private void goKayitOl() throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("kayitOl.fxml"));
        root.getChildren().setAll(pane);
    }
    @FXML
    public void girisYap() throws SQLException, IOException {
        String userMail=mail.getText();
        String userPass=pass.getText();

        User user =new User();
        String sql = "SELECT * FROM users WHERE userMail='"+userMail+"'AND userPass='"+userPass+"'";
        try (Connection conn = Connect();
             PreparedStatement pstmt  = conn.prepareStatement(sql);
             ResultSet rs  = pstmt.executeQuery()){
            while (rs.next()){
                user.setUserID(rs.getInt("userID"));
                user.setUserMail(rs.getString("userMail"));
                user.setUserName(rs.getString("userName"));
                user.setUserPass(rs.getString("userPass"));
            }
        } catch (SQLException e) {

        }
        if(Objects.equals(user.getUserPass(), userPass)){
            AnchorPane pane = FXMLLoader.load(getClass().getResource("main.fxml"));
            root.getChildren().setAll(pane);
            setUser(user);
        }else{
            feedback.setText("Tekrar Giriş Yapınız.");
            mail.clear();
            pass.clear();
        }

    }


}