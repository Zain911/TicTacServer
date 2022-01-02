package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Mohamed Galal
 */
public class DashboardController implements Initializable {

    @FXML
    private ToggleButton startBtn;
    @FXML
    private Label status;
    @FXML
    private Label noOfPlyers;
    @FXML
    private Label onlinePlyers;
    @FXML
    private Label offlinePlyers;
    @FXML
    private Label sumOfScore;
    @FXML
    private Label numOfPlyersValue;
    @FXML
    private Label onlinePlyersValue;
    @FXML
    private Label offlinePlyersValue;
    @FXML
    private Label sumOfScoreValue;
    @FXML
    private Label firstPlayer;
    @FXML
    private Label firstPlayerValue;
    @FXML
    private Button goToAllPlayerScreen;
    @FXML
    private ImageView serverImg;
    
    SceneController controller;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();
        Image image =new Image("/resources/server.png");
        serverImg.setImage(image);
    }    

    @FXML
    private void setOnDashboardListener(ActionEvent event) {
        try {
            controller.switchToAllPlayerScene(event);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
