/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Janakshi
 */
public class SessionDAO {
    Connection con;
        Statement stmt;

    public SessionDAO(Connection con) {
        this.con = con;
    }
        
    public boolean insertSessionDetail(String uID, String date, String start,String end) {
       String sql;
        sql= "INSERT INTO SESSIONS VALUES('"+start+"','"+end+"','"+date+"','"+uID+"')";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false; 
    }   
    
}
