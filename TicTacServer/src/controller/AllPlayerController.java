/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.DataObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import model.Player;

/**
 * FXML Controller class
 *
 * @author Mohamed Galal
 */
public class AllPlayerController implements Initializable {

    @FXML
    private Button goToDashScreen;

    SceneController controller;
    @FXML
    private ListView<String> myListView;

    ObservableList<String> observable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();

        new Thread(new Runnable() {
            @Override
            public void run() {

                DataObject.getConnection();
                ArrayList<Player> players = DataObject.selectAllPlayers();
                ArrayList<String> playerName = new ArrayList<>();
                for (Player p : players){
                    playerName.add(p.getUsername());
                }
                observable = FXCollections.observableArrayList(playerName);
                myListView.setItems(observable);

            }
        }).start();
    }

    @FXML
    private void setOnAllPlayerListener(ActionEvent event) {
        try {
            controller.switchToDashboardScene(event);
        } catch (IOException ex) {

            Logger.getLogger(AllPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
