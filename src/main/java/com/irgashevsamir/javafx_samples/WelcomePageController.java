package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class WelcomePageController {
    @FXML
    TextField name;
    @FXML
    Button host_btn;
    @FXML
    Button join_btn;
    private Parent root;
    private Stage stage;
    private Scene scene;

    public void validateButton(ActionEvent e) {
        if (!name.getText().isEmpty()) {
            host_btn.setDisable(false);
            join_btn.setDisable(false);
        }
    }

    public void hostTheGame(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameHost.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void joinTheGame(ActionEvent e) throws IOException {
        root = FXMLLoader.load(getClass().getResource("GameJoin.fxml"));
        stage = (Stage) ((Node)e.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
