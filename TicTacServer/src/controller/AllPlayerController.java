/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import helper.DataObject;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Player;

/**
 * FXML Controller class
 *
 * @author Mohamed Galal
 */
public class AllPlayerController implements Initializable {


    SceneController controller;
    @FXML
    private ListView<String> myListView;

    ObservableList<String> onlineObservable;
    ObservableList<String> offlineObservable;
    @FXML
    private ListView<String> myListView1;
    @FXML
    private ImageView goToDashboardScreen;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();

        new Thread(new Runnable() {
            @Override
            public void run() {

          
                try {
                    DataObject.getConnection();
                    ArrayList<Player> onlinePlayers = DataObject.selectOnlinePlyer("");
                    ArrayList<String> onlinePlayerName = new ArrayList<>();
                    for (Player p : onlinePlayers){
                        onlinePlayerName.add(p.getUsername());
                    }
                    onlineObservable = FXCollections.observableArrayList(onlinePlayerName);
                    myListView.setItems(onlineObservable);
                    
                    
                    ArrayList<Player> offlinePlayers = DataObject.selectOfflinePlayer();
                    ArrayList<String> offlinePlayerName = new ArrayList<>();
                    for (Player p : offlinePlayers){
                        offlinePlayerName.add(p.getUsername());
                    }
                    offlineObservable = FXCollections.observableArrayList(offlinePlayerName);
                    myListView1.setItems(offlineObservable);
                } catch (SQLException ex) {
                    Logger.getLogger(AllPlayerController.class.getName()).log(Level.SEVERE, null, ex);
                }
             

            }
        }).start();
    }

   

    @FXML
    private void setOnAllPlayerListener(MouseEvent event) {
        
        
        try {
            controller.switchToDashboardScene(event);
        } catch (IOException ex) {
            Logger.getLogger(AllPlayerController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }


}
