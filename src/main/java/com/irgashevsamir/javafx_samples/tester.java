package com.irgashevsamir.javafx_samples;

import java.sql.*;

public class tester {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

        // Check if username is not taken
        PreparedStatement stmt = conn.prepareStatement("INSERT into Players values(?,?,?)");
        stmt.setString(1, "Yukino");
        stmt.setString(2, "pass");
        stmt.setInt(3, 7700);
        int cnt = stmt.executeUpdate();
    }
}
