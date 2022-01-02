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
public class LoginModel implements Serializable{
    
    
   private String user_name;
   private String user_password;

    public LoginModel() {
    }

    public LoginModel(String user_name, String user_password) {
        this.user_name = user_name;
        this.user_password = user_password;
        
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

   
    
    
    
    
    
}