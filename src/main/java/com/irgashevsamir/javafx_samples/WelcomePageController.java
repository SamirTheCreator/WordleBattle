package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.*;

public class WelcomePageController {
    @FXML
    TextField username;
    @FXML
    TextField password;
    @FXML
    Label error;
    private Parent root;
    private Stage stage;
    private Scene scene;
    public boolean usernameExists;

    public void signup(ActionEvent e) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

            // Check if username is not taken
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select * from Players");
            usernameExists = false;
            while(result.next()) {
                String _username = result.getString("player_name");
                if (_username.equals(username.getText())) {
                    error.setText("Username is already taken");
                    usernameExists = true;
                    break;
                }
            }
            if (!usernameExists) {
                PreparedStatement stmt = conn.prepareStatement("INSERT into Players values(?,?,?)");
                stmt.setString(1, username.getText());
                stmt.setString(2, password.getText());
                stmt.setInt(3, 0);
                int cnt = stmt.executeUpdate();

            /*
                After user has succesfully signed up, the stage gets the menu-scene
                and we pass the username to the class menu-scene to keep track of the
                user and add points.
            */



            }

            conn.close();
        } catch (Exception exception) {
            System.out.println(exception.getClass().getName());
        }
    }

    public void login(ActionEvent e) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

        // Check if username is not taken
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select * from Players");
        while(result.next()) {
            String _username = result.getString("player_name");
            String _password = result.getString("player_password");
            if (_username.equals(username.getText())) {
                if (_password.equals(password.getText())) {
                    // menu
                    SceneController
                } else error.setText("Password is incorrect");
            } else error.setText("Username does not exist");
        }

        conn.close();
    }
}
