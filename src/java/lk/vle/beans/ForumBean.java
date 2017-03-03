/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class ForumBean {
    private String forumID;
    private String fTitle;
    private String fDesc;
    private String courseID;
    ArrayList<String> messages = new ArrayList<String>();
    
    Connection con;
    Statement stmt, stmt1,stmt2;
    
    public ForumBean(Connection con) {
        this.con = con;
    }

    public ForumBean() {
        forumID = "FXXX";
    }

    public void init(String forumID, String fTitle, String fDesc, String courseID) {
        this.forumID = forumID;
        this.fTitle = fTitle;
        this.fDesc = fDesc;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getForumID() {
        return forumID;
    }

    public void setForumID(String forumID) {
        this.forumID = forumID;
    }

    public String getfTitle() {
        return fTitle;
    }

    public void setfTitle(String fTitle) {
        this.fTitle = fTitle;
    }

    public String getfDesc() {
        return fDesc;
    }

    public void setfDesc(String fDesc) {
        this.fDesc = fDesc;
    }

    public ArrayList<String> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<String> messages) {
        this.messages = messages;
    }
    
    public void addMessage(String msg){
        this.messages.add(msg);
    }
    
    public String readForumID(String c){
        String result="";
        try {
            
            
            String sql = "Select FORUMID from FORUMS where COURSEID = '"+c+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result= rs.getString("FORUMID");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public ArrayList<String> readForumMSGs(String fID){
        ArrayList<String> result= new ArrayList<String>();
        try {
            
            
            String sql = "Select * from MESSAGES where FORUMID = '"+fID+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result.add("\n Sender: "+rs.getString("SENDERID")+"        Date: "+rs.getString("MSGDATE")+"  Time: "+rs.getString("MSGTIME")+"\n "+rs.getString("MSGCONTENT"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public boolean addForumMsgToDB(String fID, String date,String time, String msg, String sender){
        String sql,sql1;
         ResultSet rs, rs1;
         int mNo=0;
         String mID="";
        sql1= "Select COUNT(*) AS MCOUNT from MESSAGES";
        
        try {
            stmt1 = con.createStatement();
            rs = stmt1.executeQuery(sql1);
            if(rs.next()){
               mNo= Integer.parseInt(rs.getString("MCOUNT"))+1;
               mID= "M"+mNo;
                System.out.println("MCount: "+mNo);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                    
        
            sql= "INSERT INTO MESSAGES VALUES ('"+mID+"','"+date+"','"+time+"','"+msg+"','"+sender+"','"+fID+"')";
            
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex1) {
            System.out.println(ex1);
        }
        return false;
    }
    
}
