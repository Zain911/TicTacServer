package model;

import java.io.Serializable;

public class Player implements Serializable{
    
    private String username;
    private  String userPassword;
    private int status;
    private int score;
    private boolean isPasswordCorrect;
    private int numberOfWin;
    private int numberOfLose;
    private  int numberOfDraw;
    private boolean isplaying;
    private String requestStatus;

    public Player(String username, String userPassword, int status, int numberOfWin, int numberOfLose, int numberOfDraw) {
        this.username = username;
        this.userPassword = userPassword;
        this.status = status;
        this.numberOfWin = numberOfWin;
        this.numberOfLose = numberOfLose;
        this.numberOfDraw = numberOfDraw;
    }

   

    public Player(String username, String userPassword, int score, int numberOfWin, int numberOfLose, int numberOfDraw, int status, boolean isplaying) {
        this.username = username;
        this.userPassword = userPassword;
        this.score = score;
        this.numberOfWin = numberOfWin;
        this.numberOfLose = numberOfLose;
        this.numberOfDraw = numberOfDraw;
        this.status = status;
        this.isplaying = isplaying;
    }

    public Player(String username, String userPassword, int status, int score, int numberOfWin, int numberOfLose, int numberOfDraw) {
        this.username = username;
        this.userPassword = userPassword;
        this.status = status;
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

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isIsplaying() {
        return isplaying;
    }

    public void setIsplaying(boolean isplaying) {
        this.isplaying = isplaying;
    }
    
 
}