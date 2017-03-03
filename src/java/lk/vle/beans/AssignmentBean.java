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

/**
 *
 * @author Janakshi
 */
public class AssignmentBean extends AssessmentBean {

    private String assignmentID;
    private String aType;
    private String aFileName;
    private String handOut;
    private String handIn;
    private String courseID;
    
    Connection con;
    Statement stmt, stmt1,stmt2;
    

    public AssignmentBean() {
        assignmentID = "ASNXXX";
    }

    public AssignmentBean(Connection con) {
        this.con = con;
    }
    
    

    public void init(String assignmentID, String aType, String aFileName, String handOut, String handIn, String courseID) {
        this.assignmentID = assignmentID;
        this.aType = aType;
        this.aFileName = aFileName;
        this.handOut = handOut;
        this.handIn = handIn;
        this.courseID = courseID;
    }
    

    public String getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(String assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getaType() {
        return aType;
    }

    public void setaType(String aType) {
        this.aType = aType;
    }

    public String getaFileName() {
        return aFileName;
    }

    public void setaFileName(String aFileName) {
        this.aFileName = aFileName;
    }

    public String getHandOut() {
        return handOut;
    }

    public void setHandOut(String handOut) {
        this.handOut = handOut;
    }

    public String getHandIn() {
        return handIn;
    }

    public void setHandIn(String handIn) {
        this.handIn = handIn;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }
    
    public boolean insertAssignmentTODB(String aID, String aType, String handout, String handin,String cID,String fileName){
       String sql;
        sql= "INSERT INTO ASSIGNMENTS VALUES('"+aID+"','"+aType+"','"+handout+"','"+handin+"',"+cID+",'"+fileName+"')" ;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false;
    }
    
    public boolean addAssignmentSubmission(String stdID, String aID, String aFile){
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
                    
        
            sql= "INSERT INTO SUBMISSIONS VALUES ("+subNo+",'Assignment','"+subDate+"','"+subTime+"','"+stdID+"','"+aID+"',null,'"+aFile+"')";
            
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex1) {
            System.out.println(ex1);
        }
        return false;
    }
    
    @Override
    public String getAssessmentDetail() {
        String result;
        result = " Course ID : "+courseID+"\n Assignment ID: "+assignmentID+"\n Grade:"+super.getGrade();
        
        return result;
    }
    
}
