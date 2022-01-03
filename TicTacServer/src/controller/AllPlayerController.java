/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mohamed Galal
 */
public class AllPlayerController implements Initializable {

    @FXML
    private Button goToDashScreen;

    SceneController controller;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller =new SceneController();
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
