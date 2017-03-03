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
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class QuestionBean {
    
        private int qNo;
        private String qDesc;
        private String choice1;
        private String choice2;
        private String choice3;
        private String choice4;
        private int correctChoice;
        private String quizID;
        Connection con;
        Statement stmt, stmt1,stmt2;

    public QuestionBean(){
        qNo = 1;
    }

    public QuestionBean(Connection con) {
        this.con = con;
    }
    

    public void init(int qNo, String qDesc, String choice1, String choice2, String choice3, String choice4, int correctChoice, String quizID) {
        this.qNo = qNo;
        this.qDesc = qDesc;
        this.choice1 = choice1;
        this.choice2 = choice2;
        this.choice3 = choice3;
        this.choice4 = choice4;
        this.correctChoice = correctChoice;
        this.quizID = quizID;
    }

    public String getQuizID() {
        return quizID;
    }

    public void setQuizID(String quizID) {
        this.quizID = quizID;
    }

    public int getqNo() {
        return qNo;
    }

    public void setqNo(int qNo) {
        this.qNo = qNo;
    }

    public String getqDesc() {
        return qDesc;
    }

    public void setqDesc(String qDesc) {
        this.qDesc = qDesc;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public int getCorrectChoice() {
        return correctChoice;
    }

    public void setCorrectChoice(int correctChoice) {
        this.correctChoice = correctChoice;
    }
    
    public int retrieveCorrectChoice(String qID,int i){
        int result=0;
            
        try {  
            String sql = "Select CORRECTCHOICE from QUESTIONS where QUIZID= '"+qID+"' AND QNO= "+i+"";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result= rs.getInt("CORRECTCHOICE");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
        
       
    }
    
    public boolean insertQuestionTODB(int qNo, String qDesc, int correct, String qID, String ch1,String ch2,String ch3,String ch4){
       String sql;
        sql= "INSERT INTO QUESTIONS VALUES("+qNo+",'"+qDesc+"',"+correct+",'"+qID+"','"+ch1+"','"+ch2+"','"+ch3+"','"+ch4+"')" ;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false;
    }
        
        
    
}
