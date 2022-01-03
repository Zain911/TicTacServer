package helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.LoginPlayer;
import model.Player;
import model.RegisterPlayer;
import org.apache.derby.jdbc.ClientDriver;
import sun.net.www.content.text.plain;

/**
 *
 * @author mka
 */
public class DataObject {

    public static Connection con;
    public static ArrayList<Player> arrayOfPlayers = new ArrayList<>();

    public static ArrayList<Player> onLineArrayOfPlayer = new ArrayList<>();
    public static ArrayList<Player> offLineArrayOfPlayer = new ArrayList<>();
    public static ArrayList<Player> ArrayOfPlayerByOrderScoreDecs = new ArrayList<>();
    public static ArrayList<Player> ArrayOfPlayerByOrderNumberOfWinsDecs = new ArrayList<>();

    public static void getConnection() {

        try {
            DriverManager.registerDriver(new ClientDriver());

            con = DriverManager.getConnection("jdbc:derby://localhost:1527/Tic-Tac_Toc_DataBase", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Player selectPlayer(Player player) {
        Player selectedPlayer = null;
        try {
            PreparedStatement pst = con.prepareStatement("select *from PLAYERDATA where PLAYERNAME=?");
            pst.setString(1, player.getUsername());

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                selectedPlayer = new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return selectedPlayer;

    }
///select all player

    public static ArrayList<Player> selectAllPlayers() {
        try {
            PreparedStatement pst = con.prepareStatement("select * from PLAYERDATA");

            ResultSet rs = pst.executeQuery();
            if (rs != null) {
                System.out.println("cccccc");

            }
            while (rs.next()) {
                System.out.println("nnnnnnnn");
                arrayOfPlayers.add(new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4) * 5, rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return arrayOfPlayers;

    }

    public static ArrayList<Player> selectAllPlayersByOrderDese() {
        try {
            System.out.println("nnnnnnnn");
            PreparedStatement pst = con.prepareStatement("select * from PLAYERDATA order by  numberofwin desc");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ArrayOfPlayerByOrderNumberOfWinsDecs.add(new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ArrayOfPlayerByOrderNumberOfWinsDecs;
    }

    public static ArrayList<Player> selectAllPlayersByOrderScoreDese() {
        try {
            System.out.println("nnnnnnnn");
            PreparedStatement pst = con.prepareStatement("select * from PLAYERDATA order by  numberofwin*5 desc");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ArrayOfPlayerByOrderScoreDecs.add(new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ArrayOfPlayerByOrderScoreDecs;
    }

    // add new user
    public static int addNewPlayer(Player player) {
        System.out.println(player.getUsername());
        System.out.println(player.getUserPassword());
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("INSERT INTO PLAYERDATA VALUES (?,?,?,?,?,?)");

            pst.setString(1, player.getUsername());

            pst.setString(2, player.getUserPassword());
            pst.setBoolean(3, player.isIsactive());

            pst.setInt(4, player.getNumberOfWin());
            pst.setInt(5, player.getNumberOfLose());
            pst.setInt(6, player.getNumberOfDraw());

            result = pst.executeUpdate();

            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static Player selectTopPlayers() {
        Player topPlayer = null;
        try {
            PreparedStatement pst = con.prepareStatement("select * from playerData where NUMBEROFWIN= (select Max(NUMBEROFWIN) from PLAYERDATA )");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                topPlayer = new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4), rs.getInt(5), rs.getInt(6));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return topPlayer;

    }

    public static int deleteUser(Player player) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("Delete from playerData where PLAYERNAME=?");
            pst.setString(1, player.getUsername());

            result = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;

    }

    public static int updateUserStats(Player player) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE playerData set playerStatus=? where PLAYERNAME=?");

            pst.setBoolean(1, player.isIsactive());
            pst.setString(2, player.getUsername());

            result = pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /*public static int updateNumberOfWin(Player play) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("select playerScore from playerData  where playerId=?");
            pst.setInt(1, model.getPlayerStatus());
            ResultSet rs = pst.executeQuery();
            int totalScore = model.getPlayerScore() + rs.getInt(4);
            PreparedStatement pst2 = con.prepareStatement("UPDATE playerData set playerScore=? where playerId=?");

            pst.setInt(1, totalScore);
            pst.setInt(2, model.getPlayerId());

            result = pst.executeUpdate();

            //stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataTransferObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;

    }*/
    public static ArrayList<Player> selectOnlinePlyer() {
        System.out.println("hhh");
        try {
            PreparedStatement pst = con.prepareStatement("select *from PLAYERDATA where playerStatus =true");

            ResultSet rs = pst.executeQuery();
            // System.out.println(rs.isFirst());
            while (rs.next()) {
                System.out.println("ffffffff");
                onLineArrayOfPlayer.add(new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return onLineArrayOfPlayer;

    }

    public static ArrayList<Player> selectOfflinePlayer() {

        try {
            PreparedStatement pst = con.prepareStatement("select * from PLAYERDATA where playerStatus =false");

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                offLineArrayOfPlayer.add(new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4), rs.getInt(5), rs.getInt(6)));
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return offLineArrayOfPlayer;
    }

    public static int updataUser(Player player) {
        int result = 0;
        try {
            PreparedStatement pst = con.prepareStatement("UPDATE playerData set playerPassword=?,playerStatus=?,NUMBEROFWIN=? ,NUMBEROFLOSE=?,NUMBEROFDRAW=?where  playerName=?");
            pst.setString(1, player.getUserPassword());
            pst.setBoolean(2, player.isIsactive());
            pst.setInt(3, player.getNumberOfWin());
            pst.setInt(4, player.getNumberOfLose());
            pst.setInt(5, player.getNumberOfDraw());
            pst.setString(6, player.getUsername());

            result = pst.executeUpdate();

            //stmt.close();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Player selectPlayerForLogin(LoginPlayer player) {
        Player playerFounded;
        try {
            PreparedStatement pst = con.prepareStatement("select from PLAYERDATA where playerName=? ");
            pst.setString(1, player.getUsername());

            ResultSet rs = pst.executeQuery();

            if (rs != null) {
                playerFounded = new Player(rs.getString(1), rs.getString(2),
                        rs.getBoolean(3), rs.getInt(4) * 5, rs.getInt(4), rs.getInt(5), rs.getInt(6));
                return playerFounded;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataObject.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public static Player registerFunctionality(Player player) {

        Player registerPlayer = null;
        int add = addNewPlayer(player);

        if (add == 1) {
            registerPlayer = selectPlayer(player);
        }

        return registerPlayer;

    }

}
