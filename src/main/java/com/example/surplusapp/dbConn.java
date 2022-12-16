package com.example.surplusapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class dbConn {
    public static Connection Connect() {
        Connection conn=null;
        String url = "jdbc:mysql://localhost:3306/surplus";
        String username = "root";
        String password = "root";
        try{
            conn=DriverManager.getConnection(url,username,password);
        }
        catch(SQLException e){
            System.out.println("sorun var");
        }
        return conn;

    }



    public static User getUser(String mail, String pass) throws SQLException {
        User user =new User();
        String sql = "SELECT * FROM users WHERE userMail='"+mail+"'AND userPass='"+pass+"'";
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
            e.printStackTrace();

        }
        return user;}
}




