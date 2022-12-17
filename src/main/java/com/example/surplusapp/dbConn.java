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
            System.out.println("Database baglanamadi.");
        }
        return conn;

    }

}




