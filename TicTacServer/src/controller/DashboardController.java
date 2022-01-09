package controller;

import helper.DataObject;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import model.ClientHandler;
import model.Player;

public class DashboardController implements Initializable {

    @FXML
    private Label status;
    @FXML
    private ImageView goToAllPlayerScreen;
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

    
    @FXML
    private PieChart pieChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        DataObject.getConnection();
        
        getPieChart();
      
        //=================================================
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
        //=================================================

    }

    public void getPieChart() {

        
        try {
            int noOfPlayers = DataObject.selectAllPlayers().size();
            int noOfOnlinePlayers = DataObject.selectOnlinePlyer("").size();
            int noOfOfflinePlayers = DataObject.selectOfflinePlayer().size();
            int noOfWin = DataObject.numberOfWin();
            int noOfLose = DataObject.numberOfLose();
            int noOfDraw = DataObject.numberOfDraw();
            Player topPlayer = DataObject.selectTopPlayers();
            String topPlayerName = topPlayer.getUsername();
            
            ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList(
                    new PieChart.Data("All players", noOfPlayers),
                    new PieChart.Data("No of online", noOfOnlinePlayers),
                    new PieChart.Data("No of offline", noOfOfflinePlayers),
                    new PieChart.Data("No of win", noOfWin),
                    new PieChart.Data("No of lose", noOfLose),
                    new PieChart.Data("No of draw", noOfDraw)
    
            );
            //topPlayer.
            pieChart.setTitle("The 1st player is : " + topPlayerName);
            
            pieChart.setData(chartData);
            pieChart.setLegendSide(Side.LEFT);
            pieChart.setClockwise(true);
            pieChart.setLegendVisible(true);
            
            pieChart.setLabelLineLength(10);
        } catch (SQLException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    

    @FXML
    private void startServer(ActionEvent event) {

        isStartServer = !isStartServer;

        clients = new Vector<ClientHandler>();
        try {
            serverSocket = new ServerSocket(5010);
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
           
            for (int i = 0; i < clients.size(); i++) {
                clients.get(i).closeConnection();
            }
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

    @FXML
    private void setOnDashboardListener(MouseEvent event) {
        try {
            SceneController controller=new SceneController();
            controller.switchToAllPlayerScene(event);
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
