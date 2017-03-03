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

/**
 *
 * @author Janakshi
 */
public class StudentBean extends UserBean{
    
    private String stdID ;
    private String stdName;
    private String stdDegree;
    private String stdMail;
    private String stdBatch;
    private String enKey;
    ArrayList<AssessmentBean> submissionList = new ArrayList<AssessmentBean>();
    
    Connection con;
    Statement stmt, stmt1,stmt2;

    public StudentBean(){
        stdID = "STXXXX";
    }

    public StudentBean(Connection con) {
        this.con = con;
    }
    
    

    public void init(String stdID, String stdName, String stdDegree, String stdMail, String stdBatch, String enKey) {
        this.stdID = stdID;
        this.stdName = stdName;
        this.stdDegree = stdDegree;
        this.stdMail = stdMail;
        this.stdBatch = stdBatch;
        this.enKey = enKey;
    }

    public String getStdID() {
        return stdID;
    }

    public void setStdID(String stdID) {
        this.stdID = stdID;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdDegree() {
        return stdDegree;
    }

    public void setStdDegree(String stdDegree) {
        this.stdDegree = stdDegree;
    }

    public String getStdMail() {
        return stdMail;
    }

    public void setStdMail(String stdMail) {
        this.stdMail = stdMail;
    }

    public String getStdBatch() {
        return stdBatch;
    }

    public void setStdBatch(String stdBatch) {
        this.stdBatch = stdBatch;
    }

    public String getEnKey() {
        return enKey;
    }

    public void setEnKey(String enKey) {
        this.enKey = enKey;
    }
    
    public void addAssessment(AssessmentBean a){
        this. submissionList.add(a);
    }

    public ArrayList<AssessmentBean> getSubmissionList() {
        return submissionList;
    }

    public void setSubmissionList(ArrayList<AssessmentBean> submissionList) {
        this.submissionList = submissionList;
    }
    
    public String getStudentDetail(String stdID){
       ResultSet rs1;
        String sql1;
        String result  = "";
        
        
        sql1= "Select * from STUDENTS where STDID='"+stdID+"'";
        
        
        try {
            stmt = con.createStatement();
            rs1 = stmt.executeQuery(sql1);
            if(rs1.next()){
               result= ("\n Student ID: "+rs1.getString("STDID")+"\n Student Name: "+rs1.getString("STDNAME")+"\n Batch: "+rs1.getString("STDBATCH")+"\n\n");      
            }
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
   }
    
    
}
