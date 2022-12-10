package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class MenuController {
    @FXML
    Label username;
    String $username;
    String word;

    private Socket socket;
    @FXML
    ImageView img;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void setUsername(String name) {
        username.setText("Welcome back, " + name);
        $username = name;
    }

    public String getRandomWord() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");


        // Check if username is not taken
        Statement statement = conn.createStatement();
        int rand = (int) Math.random()*100;
        ResultSet result = statement.executeQuery("SELECT word FROM valid_words \n" +
                "ORDER BY RAND()\n" +
                "LIMIT 1");
        word="samir";
        while(result.next()) {
            word = result.getString("word");
        }
        return word;
    }

    public void play(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WordleGame.fxml"));
        root = loader.load();

        WordleGame wordleGame = loader.getController();

        wordleGame.setWord(getRandomWord());
        wordleGame.setUsername($username);

        socket = new Socket("localhost", 1234);

        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }

    public void leaderBoard(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaderBoard.fxml"));
        root = loader.load();

        LeaderBoard leaderBoard = loader.getController();
        leaderBoard.setImg("pin-leanne-dlamini-women-women-gold-king-crown-35.png");
        leaderBoard.setUsername($username);
        leaderBoard.setTop10();

        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
}
