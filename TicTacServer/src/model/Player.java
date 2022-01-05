package model;

import java.io.Serializable;

public class Player implements Serializable{
    
    private String username;
    private  String userPassword;
    private boolean isactive;
    private int score;
    private boolean isPasswordCorrect;
    private int numberOfWin;
    private int numberOfLose;
    private  int numberOfDraw;
  
    private boolean isplaying;

    public Player(String username, String userPassword, boolean isactive, int numberOfWin, int numberOfLose, int numberOfDraw) {
        this.username = username;
        this.userPassword = userPassword;
        this.isactive = isactive;
        this.numberOfWin = numberOfWin;
        this.numberOfLose = numberOfLose;
        this.numberOfDraw = numberOfDraw;
    }

   

    public Player(String username, String userPassword, int score, int numberOfWin, int numberOfLose, int numberOfDraw, boolean isactive, boolean isplaying) {
        this.username = username;
        this.userPassword = userPassword;
        this.score = score;
        this.numberOfWin = numberOfWin;
        this.numberOfLose = numberOfLose;
        this.numberOfDraw = numberOfDraw;
        this.isactive = isactive;
        this.isplaying = isplaying;
    }

    public Player(String username, String userPassword, boolean isactive, int score, int numberOfWin, int numberOfLose, int numberOfDraw) {
        this.username = username;
        this.userPassword = userPassword;
        this.isactive = isactive;
        this.score = score;
        this.numberOfWin = numberOfWin;
        this.numberOfLose = numberOfLose;
        this.numberOfDraw = numberOfDraw;
    }

    public boolean isIsPasswordCorrect() {
        return isPasswordCorrect;
    }

    public void setIsPasswordCorrect(boolean isPasswordCorrect) {
        this.isPasswordCorrect = isPasswordCorrect;
    }
    
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getNumberOfWin() {
        return numberOfWin;
    }

    public void setNumberOfWin(int numberOfWin) {
        this.numberOfWin = numberOfWin;
    }

    public int getNumberOfLose() {
        return numberOfLose;
    }

    public void setNumberOfLose(int numberOfLose) {
        this.numberOfLose = numberOfLose;
    }

    public int getNumberOfDraw() {
        return numberOfDraw;
    }

    public void setNumberOfDraw(int numberOfDraw) {
        this.numberOfDraw = numberOfDraw;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIsplaying() {
        return isplaying;
    }

    public void setIsplaying(boolean isplaying) {
        this.isplaying = isplaying;
    }

   
}
