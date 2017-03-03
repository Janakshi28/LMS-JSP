/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.vle.beans.AdminBean;
import lk.vle.beans.CourseBean;
import lk.vle.beans.CourseMaterialBean;
import lk.vle.beans.LecturerBean;
import lk.vle.beans.StudentBean;
import lk.vle.beans.UserBean;

/**
 *
 * @author Janakshi
 */
public class DbConnector {
    
    Connection con;
    Statement stmt;

    
    public void setCon(Connection con) {
        this.con = con;
    }

    public Connection getCon() {
        return con;
    }
    
            
    public void openConnection(){
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/dbvle", "janakshi", "janakshi"); //dbURL , dbUsername, dbPassword
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     public boolean isConnectionValid() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            return this.getCon() != null;
        } catch (ClassNotFoundException e){
            return false;
        }
    }
    
    
    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException ex) {
            
        }
        
    }
    
    public Object getItem(String tableName, String id){
        //Return the item based on the table name passed
        switch (tableName) {
            case "USERLOGIN":
                //Return a customer getting details from the database
                UserBean user = null;
                String userSql = "SELECT * FROM " + tableName + " WHERE USERID='" + id + "'";
                try {
                    Statement statement = getCon().createStatement();
                    ResultSet rs = statement.executeQuery(userSql);
                    boolean isSubscribed = false;
                    if (rs.next()){
                        user = new UserBean();
                        user.init(rs.getString("USERID"), rs.getString("PASSWORD"),rs.getInt("ROLE"));
                    }
                } catch (SQLException e){
                    return null;
                }
                return user;
                
            case "STUDENTS":
                //Return a customer getting details from the database
                StudentBean student = null;
                String stdSql = "SELECT * FROM " + tableName + " WHERE USERID='" + id + "'";
                try {
                    Statement statement = getCon().createStatement();
                    ResultSet rs = statement.executeQuery(stdSql);
                    //boolean isSubscribed = false;
                    if (rs.next()){
                        student = new StudentBean();
                        student.init(rs.getString("STDID"), rs.getString("STDNAME"),rs.getString("STDDEGREE"),
                                rs.getString("STDMAIL"),rs.getString("STDBATCH"),rs.getString("ENKEY"));
                    }
                } catch (SQLException e){
                    return null;
                }
                return student;
                
                
            case "ADMINS":
                //Return a customer getting details from the database
                AdminBean admin = null;
                String adSql = "SELECT * FROM " + tableName + " WHERE USERID='" + id + "'";
                try {
                    Statement statement = getCon().createStatement();
                    ResultSet rs = statement.executeQuery(adSql);
                    //boolean isSubscribed = false;
                    if (rs.next()){
                        admin = new AdminBean();
                        admin.init(rs.getString("ADMINID"), rs.getString("ADNAME"),rs.getString("ADSECTION"));
                    }
                } catch (SQLException e){
                    return null;
                }
                return admin;
            
            case "LECTURERS":
                //Return a customer getting details from the database
                LecturerBean lecturer = null;
                String lecSql = "SELECT * FROM " + tableName + " WHERE USERID='" + id + "'";
                try {
                    Statement statement = getCon().createStatement();
                    ResultSet rs = statement.executeQuery(lecSql);
                    //boolean isSubscribed = false;
                    if (rs.next()){
                        lecturer = new LecturerBean();
                        lecturer.init(rs.getString("LECID"), rs.getString("LECNAME"),rs.getString("LECSECTION"),
                                rs.getString("LECMAIL"),rs.getBoolean("ISCC"),rs.getString("ENKEY"));
                    }
                } catch (SQLException e){
                    return null;
                }
                return lecturer;
            
            case "COURSES":
                //Return a customer getting details from the database
                CourseBean course = null;
                String crsSql = "SELECT * FROM " + tableName + " WHERE COURSEID='" + id + "'";
                try {
                    Statement statement = getCon().createStatement();
                    ResultSet rs = statement.executeQuery(crsSql);
                    //boolean isSubscribed = false;
                    if (rs.next()){
                        course = new CourseBean();
                        course.init(rs.getString("COURSEID"), rs.getString("CRSTITLE"),rs.getString("DCATEGORY"),
                                rs.getString("DEGREE"),rs.getString("CRSDESC"),rs.getBoolean("ISREADY"),rs.getString("LOUTCOME"));
                    }
                } catch (SQLException e){
                    return null;
                }
                return course;
            
            case "COURSEMATERIALS":
                //Return a customer getting details from the database
                CourseMaterialBean material = null;
                String mSql = "SELECT * FROM " + tableName + " WHERE MATERIALID='" + id + "'";
                try {
                    Statement statement = getCon().createStatement();
                    ResultSet rs = statement.executeQuery(mSql);
                    //boolean isSubscribed = false;
                    if (rs.next()){
                        material = new CourseMaterialBean();
                        material.init(rs.getString("MATERIALID"), rs.getString("MTITLE"),rs.getString("MTYPE"),
                                rs.getString("MFILENAME"),rs.getBoolean("VISIBILITY"),rs.getString("COURSEID"));
                    }
                } catch (SQLException e){
                    return null;
                }
                return material;    
   
             default:
                return null;    
                
        }
        
    }
    
    
    
}
