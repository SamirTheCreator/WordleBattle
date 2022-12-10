package com.irgashevsamir.javafx_samples;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.*;


public class LeaderBoard {
    private String username;

    @FXML
    ImageView imageView;

    
    @FXML
    Label l1;
    @FXML
    Label l2;
    @FXML
    Label l3;
    @FXML
    Label l4;
    @FXML
    Label l5;
    @FXML
    Label l6;
    @FXML
    Label l7;
    @FXML
    Label l8;
    @FXML
    Label l9;
    @FXML
    Label l10;

    Parent root;
    Stage stage;
    Scene scene;

    public void backToMenu(ActionEvent e) throws Exception{
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

    public Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {
        List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });
        Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public void setTop10() throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Wordle?autoReconnect=true&useSSL=false", "root", "password");

            // Check if username is not taken
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("select * from Players");
            Map map = new HashMap();
            while(result.next()) {
                String _username = result.getString("player_name");
                int score = result.getInt("player_score");
                map.put(_username, score);
            }
            Map<String, Integer> sortedMap = sortByValue(map);
            l1.setText("1. " + sortedMap.keySet().toArray()[sortedMap.size() - 1] + " - " + sortedMap.values().toArray()[sortedMap.size() - 1]);
            l2.setText("2. " + sortedMap.keySet().toArray()[sortedMap.size() - 2] + " - " + sortedMap.values().toArray()[sortedMap.size() - 2]);
            l3.setText("3. " + sortedMap.keySet().toArray()[sortedMap.size() - 3] + " - " + sortedMap.values().toArray()[sortedMap.size() - 3]);
            l4.setText("4. " + sortedMap.keySet().toArray()[sortedMap.size() - 4] + " - " + sortedMap.values().toArray()[sortedMap.size() - 4]);
            l5.setText("5. " + sortedMap.keySet().toArray()[sortedMap.size() - 5] + " - " + sortedMap.values().toArray()[sortedMap.size() - 5]);
            l6.setText("6. " + sortedMap.keySet().toArray()[sortedMap.size() - 6] + " - " + sortedMap.values().toArray()[sortedMap.size() - 6]);
            l7.setText("7. " + sortedMap.keySet().toArray()[sortedMap.size() - 7] + " - " + sortedMap.values().toArray()[sortedMap.size() - 7]);
            l8.setText("8. " + sortedMap.keySet().toArray()[sortedMap.size() - 8] + " - " + sortedMap.values().toArray()[sortedMap.size() - 8]);
            l9.setText("9. " + sortedMap.keySet().toArray()[sortedMap.size() - 9] + " - " + sortedMap.values().toArray()[sortedMap.size() - 9]);
            l10.setText("10. " + sortedMap.keySet().toArray()[sortedMap.size() - 10] + " - " + sortedMap.values().toArray()[sortedMap.size() - 10]);
            


    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setImg(String url) throws IOException {
        imageView = new ImageView(new Image(getClass().getResource(url).toString()));
    }
}
