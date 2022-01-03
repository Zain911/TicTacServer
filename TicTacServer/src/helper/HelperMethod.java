/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import javafx.scene.control.Button;

/**
 *
 * @author mka
 */
public class HelperMethod {
    
    
    public  static void playAgin(boolean  isGameEnded,Button[] boardButtons) {

        isGameEnded = false;
        for (Button boardButton : boardButtons) {
            boardButton.setText("");
            boardButton.setStyle("-fx-background-color:");
        }

    }
    
}
