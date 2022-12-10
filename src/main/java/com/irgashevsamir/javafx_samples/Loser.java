package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;

public class Loser {
    @FXML
    Label label;
    private int score;


    private Parent root;
    private Stage stage;
    private Scene scene;

    private String username;

    public void setUsername (String username){
        this.username = username;
    }

    public void setScore(int score){
        this.score = score;
    }

    public void setLabel(String s) {
        label.setText(s);
    }

    public void newGame(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("WordleGame.fxml"));
        root = loader.load();

        WordleGame wordleGame = loader.getController();

        wordleGame.setWord("hello");
        wordleGame.setUsername(username);

        Socket socket = new Socket("localhost", 1234);

        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }

    public void quit(ActionEvent e) {
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        stage.close();
    }

    public void menu(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        root = loader.load();

        MenuController menuController = loader.getController();
        menuController.setUsername(username);

        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();

    }


}
