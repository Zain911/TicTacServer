/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import controller.DashboardController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
            System.out.println("Client handler THread");
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

                    }

                }

            } catch (Exception ex) {

                try {
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
                }
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

}
