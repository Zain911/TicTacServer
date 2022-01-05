package Connect;

import helper.DataObject;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LoginPlayer;
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
                while (!serverSocket.isClosed()) {
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

    public ServerHandeler(Socket socket) {
        try {
            if (!socket.isClosed()) {
                outputStream = socket.getOutputStream();
                inputStream = socket.getInputStream();
                start();
            }
        } catch (IOException ex) {
            System.out.println("user has left");
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                DataObject.getConnection();
                obj = new ObjectInputStream(inputStream);
                Object object = obj.readObject();
                
                if (object instanceof LoginPlayer) {
                    LoginPlayer loginModel = (LoginPlayer) object;
                    checkLogin(loginModel);
                } else if (object instanceof Player) {
                    Player registerModel = (Player) object;
                    addRegisterData(registerModel);
                }

            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void checkLogin(LoginPlayer loginPlayer) {
        try {

            Player player = DataObject.selectPlayerForLogin(loginPlayer);
            if (player != null) {
                if (player.isIsPasswordCorrect()) {
                    player.setIsactive(true);
                    DataObject.updataUser(player);
                    sendAcceptLogin(player, "");
                } else {
                    sendAcceptLogin(null, "PasswordNotCorrect");
                }
            } else {
                sendAcceptLogin(null, "NotFound");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
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
            objectOutputStream = new ObjectOutputStream(outputStream);
            
            if (response.equals("Accept")) {
                objectOutputStream.writeObject(newPlayer);
            } else {
                objectOutputStream.writeObject(response);
            }
            objectOutputStream.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendAcceptLogin(Player player, String status) {
        try {
            objectOutputStream = new ObjectOutputStream(outputStream);
            try {
                if (player != null) {
                    objectOutputStream.writeObject(player);
                } else {
                    objectOutputStream.writeObject(status);
                }
                objectOutputStream.flush();

            } catch (IOException ex) {
                Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerHandeler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
