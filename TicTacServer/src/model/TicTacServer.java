package model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author Ghaly
 */
public class TicTacServer extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/DashboardFXML.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe Server");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Alert closeConfirmation = new Alert(Alert.AlertType.CONFIRMATION,
                        "Are you sure you want to Exit?");
                Button exitButton = (Button) closeConfirmation.getDialogPane().lookupButton(ButtonType.OK);
                exitButton.setText("Exit");

                closeConfirmation.setHeaderText("Confirm Exit");
                closeConfirmation.initModality(Modality.APPLICATION_MODAL);
                closeConfirmation.initOwner(stage);

                Optional<ButtonType> closeResponse = closeConfirmation.showAndWait();
                if (!ButtonType.OK.equals(closeResponse.get())) {
                    event.consume();
                }
            }
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        Platform.exit();
    }

}
