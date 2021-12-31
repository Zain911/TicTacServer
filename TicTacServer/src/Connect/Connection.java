package Connect;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Player;

/**
 *
 * @author PCM
 */
public class Connection {

    private ServerSocket serverSocket;

    public Connection(int port) {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                while (true) {
                    Socket s = serverSocket.accept();
                    new ServerHandeler(s);
                }
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }).start();

    }

}

class ServerHandeler extends Thread {

    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    private InputStream inputStream;
    private ObjectInputStream obj;
    private Socket socket;

    public ServerHandeler(Socket s) {
        try {
            socket = s;
            
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
            inputStream = socket.getInputStream();
            obj = new ObjectInputStream(inputStream);
            start();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Player player = (Player) obj.readObject();
                
                
                ///////// for testing ///////////
                System.out.println("id : " + player.getId() + " name : " + player.getName());
                System.out.println("data of " + player.getName() + " : " + socket);
                
                if (player.getName().equals("mohamed")) {
                    objectOutputStream.writeObject("accept");
                    objectOutputStream.flush();
                }
                if (player.getName().equals("ali")) {
                    objectOutputStream.writeObject("refuse");
                    objectOutputStream.flush();
                }
                /////////////////////
                
                
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
