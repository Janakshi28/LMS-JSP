/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class UserBean {
    private String userID;
    private String password;
    private int role;

    public UserBean() {
    }

    public void init(String username, String password, int role) {
        this.userID = username;
        this.password = password;
        this.role = role;
    }
    
        public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
}
