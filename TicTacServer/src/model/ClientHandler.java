/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.DashboardController;
import helper.DataObject;
import static helper.DataObject.selectOnlinePlyer;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ghaly
 */
public class ClientHandler extends Thread {

    ObjectInputStream objectInputStream;
    ObjectOutputStream objectOutputStream;
    Socket socket;
    GameSession gameSession;

    public ClientHandler(Socket s) {

        try {
            if (s.isConnected()) {
                socket = s;
                objectInputStream = new ObjectInputStream(s.getInputStream());
                objectOutputStream = new ObjectOutputStream(s.getOutputStream());
            }
            start();
        } catch (IOException ex) {

            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            // System.out.println("Client handler THread");
            try {
                if (objectInputStream != null) {
                    Object recievedObject = objectInputStream.readObject();
                    if (recievedObject instanceof RequestGame) {
                        RequestGame request = (RequestGame) recievedObject;
                        if (!request.isSent()) {
                            ClientHandler client = DashboardController.clients.get(request.getRecieverPlayerId() - 1);
                            request.setSent(true);
                            client.objectOutputStream.writeObject(request);
                        } else {
                            //already sent
                            if (request.isAccepted()) {
                                gameSession = new GameSession(
                                        DashboardController.clients.get(request.getRequesterPlayerId() - 1),
                                        DashboardController.clients.get(request.getRecieverPlayerId() - 1)
                                );

                                DashboardController.clients.get(request.getRecieverPlayerId() - 1).objectOutputStream.writeObject(gameSession.game);
                                DashboardController.clients.get(request.getRequesterPlayerId() - 1).objectOutputStream.writeObject(gameSession.game);
                                DashboardController.clients.get(request.getRequesterPlayerId() - 1).gameSession = gameSession;
                            }
                        }
                    } else if (recievedObject instanceof PlayerMove) {

                        ((PlayerMove) recievedObject).setIsX(gameSession.turn);
                        gameSession.playerOne.objectOutputStream.writeObject(recievedObject);
                        gameSession.playerTwo.objectOutputStream.writeObject(recievedObject);

                        gameSession.game.playersMoves[gameSession.game.counter] = (PlayerMove) recievedObject;
                        gameSession.game.counter++;

                        gameSession.turn = !gameSession.turn;

                    } else if (recievedObject instanceof LoginPlayer) {
                        LoginPlayer loginModel = (LoginPlayer) recievedObject;
                        System.out.println(loginModel.getUsername() + " " + loginModel.getUserPassword());
                        checkLogin(loginModel);
                    } else if (recievedObject instanceof Player) {
                        Player registerModel = (Player) recievedObject;
                        System.out.println(registerModel.getUsername() + " " + registerModel.getUserPassword());

                        if (registerModel.getRequestStatus().equals("logOut")) {
                            updatePlayerStatus(registerModel);

                        } else {
                            addRegisterData(registerModel);
                        }
                    } else if (recievedObject instanceof String) {

                        getAllOnlineUsers((String) recievedObject);
                        System.out.println("getAllOnlineUsers");

                    }

                }

            } catch (Exception ex) {

                /* try {
                    socket.close();
                    if (gameSession.playerOne.socket.isClosed()) {
                        gameSession.playerTwo.objectOutputStream.writeObject("Player disconnected");
                    }
                    if (gameSession.playerTwo.socket.isClosed()) {
                        gameSession.playerOne.objectOutputStream.writeObject("Player disconnected");
                    }
                    this.stop();
                    DashboardController.clients.remove(this);

                } catch (IOException exe) {
                    ex.printStackTrace();
                    System.out.println("Player has left");
                }*/
            }

        }
    }

    public void closeConnection() {
        try {
            DashboardController.clients.remove(this);
            this.stop();
            this.objectInputStream.close();
            this.objectOutputStream.close();

        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String toString() {
        return "ClientHandler{" + "objectInputStream=" + objectInputStream + ", objectOutputStream=" + objectOutputStream + '}';
    }

    private void checkLogin(LoginPlayer loginPlayer) {
        try {
            DataObject.getConnection();
            Player player = DataObject.selectPlayerForLogin(loginPlayer);
            if (player != null) {
                if (player.isIsPasswordCorrect()) {
                    player.setStatus(1);
                    DataObject.updataUser(player);
                    sendAcceptLogin(player, "");
                } else {
                    sendAcceptLogin(null, "PasswordNotCorrect");
                }
            } else {
                sendAcceptLogin(null, "NotFound");
            }
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addRegisterData(Player player) {
        Player newPlayer = null;
        String response = "";
        try {
            newPlayer = DataObject.registerFunctionality(player);

            if (newPlayer != null) {
                response = "Accept";
            } else {
                response = "Error";
            }

        } catch (SQLException ex) {
            response = "User is Already Exist";
        }
        try {

            if (response.equals("Accept")) {
                objectOutputStream.writeObject(newPlayer);
            } else {
                objectOutputStream.writeObject(response);
            }
            objectOutputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendAcceptLogin(Player player, String status) {
        try {
            if (player != null) {
                objectOutputStream.writeObject(player);
            } else {
                objectOutputStream.writeObject(status);
            }
            objectOutputStream.flush();

        } catch (IOException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getAllOnlineUsers(String playerName) {
        try {
            ArrayList<Player> playerList = selectOnlinePlyer(playerName);
            sendListToClient(playerList);
        } catch (SQLException ex) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendListToClient(ArrayList<Player> playerList) {
        if (playerList != null) {
            try {
                objectOutputStream.writeObject(playerList);
                objectOutputStream.flush();
                System.out.println("data is flushed");
            } catch (IOException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("don't found data");
        }
    }

    private void updatePlayerStatus(Player player) {
        try {
            DataObject.updataUser(player);
        } catch (SQLException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
