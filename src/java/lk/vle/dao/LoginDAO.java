/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janakshi
 */
public class LoginDAO {
    
        Connection con;
        Statement stmt;

    public LoginDAO(Connection c) {
        this.con = c;
    }


    public boolean isUserExist(String u, String p){
        boolean flag = false;
        try {
            
            
            String sql = "select userID from USERLOGIN where userID = '"+u+"' and password = '"+p+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                flag = true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public int readUserRole(String u){
        int flag = 0;
        try {
            
            
            String sql = "Select ROLE from USERLOGIN where userID = '"+u+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                flag= rs.getInt("role");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public String readID(String uID, int role){
           
                String result="";
                
                try { 
                ResultSet rs;    
                    
                switch (role) {
                    case 1:
                        String sql= "Select ADMINID from ADMINS where userID='"+uID+"'";
                                       
                                        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_READ_ONLY);
                                        rs = stmt.executeQuery(sql);
                                        if(rs.next())
                                        result = rs.getString("adminID");
                            break;

                    case 2:
                        String sql1 = "Select LECID from LECTURERS where userID='"+uID+"'";
                                        
                                        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_READ_ONLY);
                                        rs = stmt.executeQuery(sql1);
                                        if(rs.next())
                                        result = rs.getString("lecID");
                            break;
                                   
                    case 3:
                        String sql2 = "Select STDID from STUDENTS where userID = '"+uID+"'";
                                  
                                        stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                           ResultSet.CONCUR_READ_ONLY);
                                        rs = stmt.executeQuery(sql2);
                                        if(rs.next())
                                        result = rs.getString("stdID");
                            break;
                        
                    default:
                        result="";
                }
                } catch (SQLException ex) {
                    Logger.getLogger(LoginDAO.class.getName()).log(Level.SEVERE, null, ex);
                }

            
            return result;
    }
    
     
    
    
}
