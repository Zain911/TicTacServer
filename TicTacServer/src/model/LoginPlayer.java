/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author mka
 */
public class LoginPlayer implements Serializable{
    private String username;
    private  String userPassword;

    public LoginPlayer(String username, String userPassword) {
        this.username = username;
        this.userPassword = userPassword;
    }
    public LoginPlayer(){
        
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
    
}
