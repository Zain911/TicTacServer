package serverui;

import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public abstract class FXMLDocumentBase extends AnchorPane {

    protected final ToggleButton startBtn;
    protected final Label label;
    protected final Label status;
    protected final Label noOfPlyers;
    protected final Label onlinePlyers;
    protected final Label offlinePlyers;
    protected final Label sumOfScore;
    protected final Label numOfPlyersValue;
    protected final Label onlinePlyersValue;
    protected final Label offlinePlyersValue;
    protected final Label sumOfScoreValue;
    protected final Label firstPlayer;
    protected final Label firstPlayerValue;
    protected final Label goToAnotherScreen;

    public FXMLDocumentBase() {

        startBtn = new ToggleButton();
        label = new Label();
        status = new Label();
        noOfPlyers = new Label();
        onlinePlyers = new Label();
        offlinePlyers = new Label();
        sumOfScore = new Label();
        numOfPlyersValue = new Label();
        onlinePlyersValue = new Label();
        offlinePlyersValue = new Label();
        sumOfScoreValue = new Label();
        firstPlayer = new Label();
        firstPlayerValue = new Label();
        goToAnotherScreen = new Label();

        setId("AnchorPane");
        setPrefHeight(495.0);
        setPrefWidth(462.0);

        startBtn.setLayoutX(97.0);
        startBtn.setLayoutY(115.0);
        startBtn.setMnemonicParsing(false);
        startBtn.setPrefHeight(32.0);
        startBtn.setPrefWidth(105.0);
        startBtn.setText("start");

        label.setLayoutX(157.0);
        label.setLayoutY(45.0);
        label.setPrefHeight(23.0);
        label.setPrefWidth(149.0);
        label.setStyle("-fx-font-size: 26;");
        label.setText("Tic Tac Toe");

        status.setLayoutX(282.0);
        status.setLayoutY(120.0);
        status.setPrefHeight(18.0);
        status.setPrefWidth(116.0);
        status.setStyle("-fx-font-size: 18;");
        status.setText("status");

        noOfPlyers.setLayoutX(97.0);
        noOfPlyers.setLayoutY(187.0);
        noOfPlyers.setPrefHeight(32.0);
        noOfPlyers.setPrefWidth(142.0);
        noOfPlyers.setStyle("-fx-font-size: 15;");
        noOfPlyers.setText("Number of players :");
        noOfPlyers.setText("Number of players :");
        noOfPlyers.setTextFill(Color.GREEN);

        onlinePlyers.setLayoutX(97.0);
        onlinePlyers.setLayoutY(232.0);
        onlinePlyers.setPrefHeight(32.0);
        onlinePlyers.setPrefWidth(142.0);
        onlinePlyers.setStyle("-fx-font-size: 15;");
        onlinePlyers.setText("Online players :");
        onlinePlyers.setTextFill(Color.GREEN);

        offlinePlyers.setLayoutX(97.0);
        offlinePlyers.setLayoutY(283.0);
        offlinePlyers.setPrefHeight(32.0);
        offlinePlyers.setPrefWidth(142.0);
        offlinePlyers.setStyle("-fx-font-size: 15;");
        offlinePlyers.setText("Offline players :");
        offlinePlyers.setTextFill(Color.GREEN);

        sumOfScore.setLayoutX(97.0);
        sumOfScore.setLayoutY(324.0);
        sumOfScore.setPrefHeight(32.0);
        sumOfScore.setPrefWidth(142.0);
        sumOfScore.setStyle("-fx-font-size: 15;");
        sumOfScore.setText("Sum of score :");
        sumOfScore.setTextFill(Color.GREEN);

        numOfPlyersValue.setLayoutX(282.0);
        numOfPlyersValue.setLayoutY(193.0);
        numOfPlyersValue.setStyle("-fx-font-size: 15;");
        numOfPlyersValue.setText("1000");

        onlinePlyersValue.setLayoutX(282.0);
        onlinePlyersValue.setLayoutY(238.0);
        onlinePlyersValue.setStyle("-fx-font-size: 15;");
        onlinePlyersValue.setText("700");

        offlinePlyersValue.setLayoutX(282.0);
        offlinePlyersValue.setLayoutY(280.0);
        offlinePlyersValue.setStyle("-fx-font-size: 15;");
        offlinePlyersValue.setText("300");

        sumOfScoreValue.setLayoutX(282.0);
        sumOfScoreValue.setLayoutY(330.0);
        sumOfScoreValue.setStyle("-fx-font-size: 15;");
        sumOfScoreValue.setText("1000");

        firstPlayer.setLayoutX(97.0);
        firstPlayer.setLayoutY(371.0);
        firstPlayer.setPrefHeight(32.0);
        firstPlayer.setPrefWidth(142.0);
        firstPlayer.setStyle("-fx-font-size: 15;");
        firstPlayer.setText("The 1st player :");
        firstPlayer.setTextFill(Color.GREEN);


        firstPlayerValue.setLayoutX(251.0);
        firstPlayerValue.setLayoutY(377.0);
        firstPlayerValue.setStyle("-fx-font-size: 15;");
        firstPlayerValue.setText("yahya Mazen");

        goToAnotherScreen.setLayoutX(149.0);
        goToAnotherScreen.setLayoutY(440.0);
        goToAnotherScreen.setText("Go to All players Screen >>>");

        getChildren().add(startBtn);
        getChildren().add(label);
        getChildren().add(status);
        getChildren().add(noOfPlyers);
        getChildren().add(onlinePlyers);
        getChildren().add(offlinePlyers);
        getChildren().add(sumOfScore);
        getChildren().add(numOfPlyersValue);
        getChildren().add(onlinePlyersValue);
        getChildren().add(offlinePlyersValue);
        getChildren().add(sumOfScoreValue);
        getChildren().add(firstPlayer);
        getChildren().add(firstPlayerValue);
        getChildren().add(goToAnotherScreen);

    }
}
