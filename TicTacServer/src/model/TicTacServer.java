package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

/**
 *
 * @author Ghaly
 */
public class TicTacServer extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD
        Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardFXML.fxml"));
        
=======
        Parent root = FXMLLoader.load(getClass().getResource("/view/FXMLDocument.fxml"));
 
>>>>>>> df0b3884210af7785ba5579a067e9d70e1fc633c
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe Server");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
