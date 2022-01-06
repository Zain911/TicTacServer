package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ResourceBundle;
import java.util.Vector;
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
import model.ClientHandler;

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

    @FXML
    Label serverIP;
    @FXML
    private Button startServerButton;
    @FXML
    private Button stopServerButton;
    SceneController controller;

    static ServerSocket serverSocket;
    static Thread socketAccpetListener;
    public static Vector<ClientHandler> clients;
    public static boolean isStartServer = false;

    public static String CurrentStatusServer = "OFF";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        controller = new SceneController();
        Image image = new Image("/resources/server.png");
        serverImg.setImage(image);
        if (CurrentStatusServer.equalsIgnoreCase("OFF")) {
            stopServerButton.setDisable(true);
            startServerButton.setDisable(false);

        } else {
            startServerButton.setDisable(false);
            startServerButton.setDisable(true);

        }
        try {
            serverIP.setText("Server IP is " + getIP());
        } catch (UnknownHostException ex) {
            serverIP.setText("Not Avalible Now...");
        }
        System.out.println("from int " + CurrentStatusServer);
        status.setText(CurrentStatusServer);
    }

    @FXML
    private void setOnDashboardListener(ActionEvent event
    ) {
        try {
            controller.switchToAllPlayerScene(event);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void startServer(ActionEvent event
    ) {
        isStartServer = !isStartServer;

        clients = new Vector<ClientHandler>();
        try {
            serverSocket = new ServerSocket(5006);
            status.setText("On");
            System.out.println("ServerStarted");
            startServerButton.setDisable(isStartServer);
            stopServerButton.setDisable(!isStartServer);
            CurrentStatusServer = "On";
            System.out.println("start" + isStartServer);
            socketAccpetListener = new Thread(() -> {
                while (true) {
                    System.out.println("Socket Listener Thread");
                    try {
                        Socket s = serverSocket.accept();
                        clients.add(new ClientHandler(s));
                        System.out.println("Clients number : " + clients.size());
                        for (int i = 0; i < clients.size(); i++) {
                            System.out.println(clients.get(i).toString());
                        }
                    } catch (IOException ex) {
                        Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            socketAccpetListener.setDaemon(true);
            socketAccpetListener.start();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void stopServer(ActionEvent event) {
        startServerButton.setDisable(!isStartServer);
        stopServerButton.setDisable(isStartServer);
        isStartServer = !isStartServer;
        try {
            clients.forEach((client) -> {
                client.closeConnection();

            });
            clients.clear();
            socketAccpetListener.stop();
            serverSocket.close();

            System.out.println("ServerStopped");
            status.setText("OFF");
            CurrentStatusServer = "OFF";
            System.out.println("stop" + isStartServer);

        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String getIP() throws UnknownHostException {
        return Inet4Address.getLocalHost().getHostAddress();
    }

}
