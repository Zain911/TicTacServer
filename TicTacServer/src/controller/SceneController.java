package controller;

import java.io.IOException;
import java.util.Optional;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.derby.client.am.ClientConnection;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToAllPlayerScene(MouseEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/view/AllPlayersFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
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

    public void switchToDashboardScene(MouseEvent event) throws IOException {

        root = FXMLLoader.load(getClass().getResource("/view/DashboardFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.resizableProperty().setValue(false);
        stage.setScene(scene);
        stage.show();
    }

}
