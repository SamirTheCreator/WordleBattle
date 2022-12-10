package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.*;
import java.net.Socket;

public class GameJoinController{
    Client client;
    @FXML
    TextField codeField;

    public void sendTheCode (ActionEvent e) throws IOException {
        if (client == null) {
            client = new Client(new Socket("127.0.0.1", 1234), "joinPlayer");
        }
        client.sendMessage("[" + codeField.getText());
        client.listenForMessage();
    }
}
