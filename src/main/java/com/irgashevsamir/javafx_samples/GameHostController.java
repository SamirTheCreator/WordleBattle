package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameHostController {
    @FXML
    Label code;

    public void generateCode(ActionEvent e){
        // ---------------------------------------------------
        // --Send the code to the server and to the Opponent--
        // ---------------------------------------------------

        code.setText("Code: " + (int)(Math.floor(Math.random()*100000)));
    }
}
