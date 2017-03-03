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

/**
 *
 * @author Janakshi
 */
public class AssessmentBean{
    private String assessmentID;
    private String lecturerName;
    private String lFeedback;
    private char grade;
    private String peerReview;
    Connection con;
    Statement stmt, stmt1,stmt2;

    public AssessmentBean() {
        assessmentID = "ASMXXX";
    }

    public AssessmentBean(Connection con) {
        this.con = con;
    }
    
    

    public void init(String assessmentID, String lecturerName, String lFeedback, char grade, String peerReview) {
        this.assessmentID = assessmentID;
        this.lecturerName = lecturerName;
        this.lFeedback = lFeedback;
        this.grade = grade;
        this.peerReview = peerReview;
    }

    public String getPeerReview() {
        return peerReview;
    }

    public void setPeerReview(String peerReview) {
        this.peerReview = peerReview;
    }

    public String getAssessmentID() {
        return assessmentID;
    }

    public void setAssessmentID(String assessmentID) {
        this.assessmentID = assessmentID;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    public void setLecturerName(String lecturerName) {
        this.lecturerName = lecturerName;
    }

    public String getlFeedback() {
        return lFeedback;
    }

    public void setlFeedback(String lFeedback) {
        this.lFeedback = lFeedback;
    }

    public char getGrade() {
        return grade;
    }

    public void setGrade(char grade) {
        this.grade = grade;
    }
    
    public String getAssessmentDetail(){
        return "xxxx";
    }
    
    public int getFeedbackSum(){
        ResultSet rs;
        String sql;
        int sum=0;
        
        sql= "Select COUNT(*) AS FCOUNT from LECTURERFEEDBACKS";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
               sum= Integer.parseInt(rs.getString("FCOUNT"))+1;
                System.out.println("FCount: "+sum);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return sum;
    }
    
    public int getReviewSum(){
        ResultSet rs;
        String sql;
        int sum=0;
        
        sql= "Select COUNT(*) AS PCOUNT from PEERREVIEWS";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
               sum= Integer.parseInt(rs.getString("PCOUNT"))+1;
                System.out.println("PCount: "+sum);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return sum;
    }
    
    public boolean insertLFeedbackTODB(int fNo, String com, char grade, int subNo,String lecID){
       String sql;
        sql= "INSERT INTO LECTURERFEEDBACKS VALUES("+fNo+",'"+com+"','"+grade+"',"+subNo+",'"+lecID+"')" ;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false;
    }
    
     public boolean insertPReviewTODB(int rNo, String desc, int rate, int subNo,String stdID){
       String sql;
        sql= "INSERT INTO LECTURERFEEDBACKS VALUES("+rNo+",'"+desc+"',"+rate+","+subNo+",'"+stdID+"')" ;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false;
    }
     
   public boolean eligibleForViewFeedback(String stdID){
       ResultSet rs;
        String sql;
        int sum=0;
        
        sql= "Select COUNT(*) AS ECOUNT from PEERREVIEWS where STDID='"+stdID+"'";
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
               sum= Integer.parseInt(rs.getString("ECOUNT"));
                System.out.println("ECount: "+sum);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        return sum>= 2;
   } 
   
   public String getFinalSubmissionDetail(String stdID, String cID){
       ResultSet rs1,rs2;
        String sql1,sql2;
        String result  = "  Assignment      Grade   \n";
        
        
        sql1= "Select l.GRADE, s.ASSIGNMENTID from LECTURERFEEDBACKS l,SUBMISSIONS s where l.SUBMISSIONNO=s.SUBMISSIONNO AND s.STDID='"+stdID+"' AND s.ASSIGNMENTID IN(SELECT ASSIGNMENTID from ASSIGNMENTS where COURSEID='"+cID+"')";
       
        sql2= "Select l.GRADE, s.QUIZID from LECTURERFEEDBACKS l,SUBMISSIONS s where l.SUBMISSIONNO=s.SUBMISSIONNO AND s.STDID='"+stdID+"' AND s.QUIZID IN(SELECT QUIZID from QUIZES where COURSEID='"+cID+"')";
        
        try {
            stmt = con.createStatement();
            rs1 = stmt.executeQuery(sql1);
            if(rs1.next()){
               result= result+("    "+rs1.getString("ASSIGNMENTID")+"       "+rs1.getString("GRADE").charAt(0)+"    \n");      
            }
            result = result+ ("\n  Quiz      Grade   \n");
            
            stmt1 = con.createStatement();
            rs2 = stmt1.executeQuery(sql2);
            if(rs2.next()){
               result= result+("    "+rs1.getString("QUIZID")+"       "+rs1.getString("GRADE").charAt(0)+"    \n");      
            }
            System.out.println(result);
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result+"\n\n";
   }

    
}
