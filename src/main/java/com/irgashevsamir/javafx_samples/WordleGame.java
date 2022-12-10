package com.irgashevsamir.javafx_samples;


import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.Socket;
import java.sql.*;

public class WordleGame {
    public int count; // keeps the count of tries up to 6

    public String word;

    private int score;

    public void setWord(String word) {
        this.word = word;
    }
    public String username;
    @FXML
    TextField textField;
    @FXML
    Label error;
    @FXML
    Label l00;
    @FXML
    Label l01;
    @FXML
    Label l02;
    @FXML
    Label l03;
    @FXML
    Label l04;
    @FXML
    Label l10;
    @FXML
    Label l11;
    @FXML
    Label l12;
    @FXML
    Label l13;
    @FXML
    Label l14;
    @FXML
    Label l20;
    @FXML
    Label l21;
    @FXML
    Label l22;
    @FXML
    Label l23;
    @FXML
    Label l24;
    @FXML
    Label l30;
    @FXML
    Label l31;
    @FXML
    Label l32;
    @FXML
    Label l33;
    @FXML
    Label l34;
    @FXML
    Label l40;
    @FXML
    Label l41;
    @FXML
    Label l42;
    @FXML
    Label l43;
    @FXML
    Label l44;
    @FXML
    Label l50;
    @FXML
    Label l51;
    @FXML
    Label l52;
    @FXML
    Label l53;
    @FXML
    Label l54;

    private Parent root;
    private Stage stage;
    private Scene scene;
    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isValid(String word) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

        // Check if username is not taken
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery("select * from valid_words");
        while(result.next()) {
            String _word = result.getString("word");
            if (word.equalsIgnoreCase(_word)) {
                return true;
            }
        }
        return false;
    }

    public void changeColor(Label label, Color color) {
        final Animation animation = new Transition() {

            @Override
            protected void interpolate(double v) {
                Color vColor = new Color(color.getRed(), color.getGreen(), color.getBlue(), v);
                label.setBackground(Background.fill(vColor));
            }

            {
                setCycleDuration(Duration.millis(3000));
                setInterpolator(Interpolator.EASE_IN);
                try {
                    Thread.onSpinWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };
        animation.play();
    }

    public void won(ActionEvent event) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

            // Check if username is not taken
            Statement statement = conn.createStatement();


            ResultSet result = statement.executeQuery("select * from Players where player_name = \"" + username + "\"");
            while(result.next()) {
                score = result.getInt("player_score");
                System.out.println(score);
            }
            score += (5 - count)*1000;

            PreparedStatement stmt = conn.prepareStatement("update Players set player_score = " + score + " where player_name = \"" +username + "\"");
            int a = stmt.executeUpdate();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Winner.fxml"));
            root = loader.load();

            Winner winner = loader.getController();
            winner.setUsername(username);
            winner.setScore(score);
            winner.setLabel("+" + (5 - count)*1000 + "pts, total: " + score + "pts");

            stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.setFullScreen(true);
            stage.show();
        } catch (SQLException e) {
            System.out.println(e.getErrorCode());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void lost(ActionEvent event) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

        // Check if username is not taken
        Statement statement = conn.createStatement();


        ResultSet result = statement.executeQuery("select * from Players where player_name = \"" + username + "\"");
        while(result.next()) {
            score = result.getInt("player_score");
        }

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Loser.fxml"));
        root = loader.load();


        Loser loser = loader.getController();
        loser.setLabel("The word was " + word + ". Curret score: " + score);
        loser.setUsername(username);

        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
    }
    public void go(ActionEvent event) throws Exception {
            Label[][] labels = {{l00, l01, l02, l03, l04}, {l10,l11,l12,l13,l14}, {l20,l21,l22,l23,l24}, {l30,l31,l32,l33,l34}, {l40,l41,l42,l43,l44}, {l50,l51,l52,l53,l54}};
            if (!isValid(textField.getText())) {
                error.setText("Enter valid word!");
            } else {
                error.setText("");
                String guess = textField.getText();
                for (int i=0; i<5; i++) {
                    String letter = guess.substring(i,i+1);
                    labels[count][i].setText(letter.toLowerCase());
                    if (word.charAt(i) == guess.charAt(i)) {
                        changeColor(labels[count][i], Color.GREEN);
                    } else if (word.indexOf(guess.charAt(i)) != -1) {
                        changeColor(labels[count][i], Color.valueOf("#FFC425"));
                    } else {
                        changeColor(labels[count][i], Color.GRAY);
                    }
                }
                if (textField.getText().equals(word)) {
                    won(event);
                } else if (textField.getText().equals("enjoy")) {
                    error.setText("Big fan of Prof Dragunov?");
                }
                count += 1;
                if (count == 6) {
                    lost(event);
                }
                System.out.println(count);
            }
    }
}
