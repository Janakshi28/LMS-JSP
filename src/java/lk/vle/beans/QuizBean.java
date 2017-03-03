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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Janakshi
 */
public class QuizBean extends AssessmentBean{
    
    private String quizID;
    private String qTitle;
    private String qZDeadLine;
    private int qZTimePeriod;
    private String courseID;
    QuestionBean[] questions = new QuestionBean[10];
    Connection con;
    Statement stmt, stmt1;

    public QuizBean() {
        quizID = "QZXXX";
    } 

    public QuizBean(Connection con) {
        this.con = con;
    }
    
    

    public void init(String quizID, String qTitle, String qZDeadLine, int qZTimePeriod, String courseID) {
        this.quizID = quizID;
        this.qTitle = qTitle;
        this.qZDeadLine = qZDeadLine;
        this.qZTimePeriod = qZTimePeriod;
        this.courseID = courseID;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public String getqTitle() {
        return qTitle;
    }

    public void setqTitle(String qTitle) {
        this.qTitle = qTitle;
    }

    public String getqZDeadLine() {
        return qZDeadLine;
    }

    public void setqZDeadLine(String qZDeadLine) {
        this.qZDeadLine = qZDeadLine;
    }

    public int getqZTimePeriod() {
        return qZTimePeriod;
    }

    public void setqZTimePeriod(int qZTimePeriod) {
        this.qZTimePeriod = qZTimePeriod;
    }

    public QuestionBean[] getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionBean[] questions) {
        this.questions = questions;
    }
    
    
    public void addQuestion(QuestionBean q){
        this.questions[questions.length]= q;
    }
    
     public boolean addQuizSubmission(String stdID, String qID, String qFile){
        ResultSet rs, rs1;
        String sql,sql1,sql2;
        int subNo=0;
        String subDate;
        String subTime;
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        SimpleDateFormat ft2 = new SimpleDateFormat ("hh:mm:ss");        
        subDate = ft.format(dNow);
        subTime = ft2.format(dNow);
             
            sql1= "Select COUNT(*) AS SCOUNT from SUBMISSIONS";
        
        try {
            stmt1 = con.createStatement();
            rs = stmt1.executeQuery(sql1);
            if(rs.next()){
               subNo= Integer.parseInt(rs.getString("SCOUNT"))+1;
                System.out.println("SCount: "+subNo);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
                    
        
            sql= "INSERT INTO SUBMISSIONS VALUES ("+subNo+",'Quiz','"+subDate+"','"+subTime+"','"+stdID+"',null,'"+qID+"','"+qFile+"')";
            
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex1) {
            System.out.println(ex1);
        }
        return false;
    }
     
    public boolean isQuizReady(String qID){
        boolean result;
        String sql;
        ResultSet rs;
        sql= "Select ISREADY from QUIZES where QUIZID='"+qID+"'";
        try {
            stmt1 = con.createStatement();
            rs = stmt1.executeQuery(sql);
            if(rs.next()){
               result= rs.getBoolean("ISREADY");
                System.out.println("qrdy: "+result);
                return result;
            }
        } catch (SQLException ex) {
            System.out.println(ex);
           
        }
         return false;   
    }
    
    public boolean makeQuizReady(String qID){
       String sql;
        sql= "UPDATE QUIZES SET ISREADY=true WHERE QUIZID='"+qID+"'";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
           return false;
        }
         
    }
    
    public boolean insertQuizTODB(String qID, String qtitle, String deadline, int period, String cID){
       String sql;
        sql= "INSERT INTO QUIZES VALUES('"+qID+"','"+qtitle+"','"+deadline+"',"+period+",'"+cID+"',false)" ;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false;
    }
    
    
    @Override
    public String getAssessmentDetail() {
        String result;
        result = " Course ID : "+courseID+"\n Quiz ID: "+quizID+"\n Grade:"+super.getGrade();
        
        return result;
    }
    
    


}