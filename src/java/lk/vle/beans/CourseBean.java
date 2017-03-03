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
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import lk.vle.dao.DbConnector;

/**
 *
 * @author Janakshi
 */
public class CourseBean {
    
    private String courseID;
    private String crsTitle;
    private String dCategory;
    private String degree;
    private String crsDesc;
    private boolean isReady;
    private String lOutcome;
    ArrayList<AssessmentBean> assessmentList = new ArrayList<AssessmentBean>();
    ArrayList<CourseMaterialBean> materialList = new ArrayList<CourseMaterialBean>();
    Connection con;
    Statement stmt, stmt1,stmt2;

    public CourseBean(Connection con) {
        this.con = con;
    }
    
    public CourseBean() {
        
        courseID = "CRSXXX";
    }

    public void init(String courseID, String crsTitle, String dCategory, String degree, String crsDesc, boolean isReady, String lOutcome) {
        this.courseID = courseID;
        this.crsTitle = crsTitle;
        this.dCategory = dCategory;
        this.degree = degree;
        this.crsDesc = crsDesc;
        this.isReady = isReady;
        this.lOutcome = lOutcome;
    }

    public String getlOutcome() {
        return lOutcome;
    }

    public void setlOutcome(String lOutcome) {
        this.lOutcome = lOutcome;
    }

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getCrsTitle() {
        return crsTitle;
    }

    public void setCrsTitle(String crsTitle) {
        this.crsTitle = crsTitle;
    }

    public String getdCategory() {
        return dCategory;
    }

    public void setdCategory(String dCategory) {
        this.dCategory = dCategory;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCrsDesc() {
        return crsDesc;
    }

    public void setCrsDesc(String crsDesc) {
        this.crsDesc = crsDesc;
    }

    public boolean isIsReady() {
        return isReady;
    }

    public void setIsReady(boolean isReady) {
        this.isReady = isReady;
    }

    public ArrayList<AssessmentBean> getAssessmentList() {
        return assessmentList;
    }

    public void setAssessmentList(ArrayList<AssessmentBean> assessmentList) {
        this.assessmentList = assessmentList;
    }

    public ArrayList<CourseMaterialBean> getMaterialList() {
        return materialList;
    }

    public void setMaterialList(ArrayList<CourseMaterialBean> materialList) {
        this.materialList = materialList;
    }
    
    
    public void addAssessmentItem(AssessmentBean a){
        this.assessmentList.add(a);
    }
    
    public void addMaterial(CourseMaterialBean m){
        this.materialList.add(m);
    }
    
     public String readCrsDesc(String c){
        String result="";
        try {
            
            
            String sql = "Select CRSDESC from COURSES where courseID = '"+c+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result= rs.getString("CRSDESC");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     
      public boolean isEnrolled(String u,String cID,int role){
        boolean result= false;
        String sql;
        try {
            
            switch (role) {
                case 2:
                    sql = "Select * from ENROLLMENTS where LECID='"+u+"' AND COURSEID='"+cID+"'";
                    break;
                case 3:
                    sql = "Select * from ENROLLMENTS where STDID='"+u+"' AND COURSEID='"+cID+"'";
                    break;
                default:
                    sql= "";
                    break;
            }
            
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) return true;
        } catch (SQLException ex) {
            return false;
        }
        
        
        return false;
    }
      
    public int enrollForCourse(String id,int role, String cID,String enKey){
        
        ResultSet rs, rs1;
        String sql,sql1,sql2;
        String enID = id+cID;
        String enDate;
        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("dd.MM.yyyy");
        enDate = ft.format(dNow);
        try {
            
            switch (role) {
                case 2:
                    sql = "Select * from LECTURERS where LECID='"+id+"' AND ENKEY='"+enKey+"'";

                    sql2= "INSERT INTO ENROLLMENTS(ENID,ENDATE,COURSEID,STDID,LECID) VALUES ('"+enID+"','"+enDate+"','"+cID+"',null,'"+id+"')";
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(sql);
                    if (rs.next()){   
                        stmt1 = con.createStatement();
                        if(stmt1.executeUpdate(sql2) == 1)
                            return 1;
                        
                    } 
                    
                    break;
                    
                case 3:
                    sql = "Select * from STUDENTS where STDID='"+id+"' AND ENKEY='"+enKey+"'";
                    
                    sql1= "Select ISREADY from COURSES where COURSEID='"+cID+"'";
 
                    sql2= "INSERT INTO ENROLLMENTS VALUES ('"+enID+"','"+enDate+"','"+cID+"','"+id+"',null)";
                    
                    stmt = con.createStatement();
                    rs = stmt.executeQuery(sql);
                    stmt2 = con.createStatement();
                    rs1 = stmt2.executeQuery(sql1);
                    if (rs.next()){
                        
                        if(rs1.next()){
                            if(rs1.getBoolean("ISREADY")){
                                stmt1 = con.createStatement();
                                if( stmt1.executeUpdate(sql2) == 1)
                                return 1;
                            }
                            else return 2;
                        }
                        
                    } 

                    break;
                    
                default:
                    sql ="";
                    sql1="";
                    
                    break;
            }
  
        } catch (SQLException ex) {
            return 3;
        }
        return 3;
    }  
    
    public boolean addCourse(String id, String title,String dCat, String degree, String desc){
        String sql;
        
            sql= "INSERT INTO COURSES VALUES ('"+id+"','"+title+"','"+dCat+"','"+degree+"','"+desc+"',false,null)";
            
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
            
        }
        return false;
    }
    
    public boolean makeCrsReady(String crsID){
        String sql;
        sql= "UPDATE COURSES SET ISREADY=true WHERE COURSEID='"+crsID+"'";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
           
        }
         return false;
    }
    
    public boolean setLearningOutcomes(String cID, String loutcome){
        String sql;
        sql= "UPDATE COURSES SET LOUTCOME='"+loutcome+"' WHERE COURSEID='"+cID+"'";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
            
        }
        return false;
    }
    
    public boolean isCourseComplete(String cID, String stdID){
        ResultSet rs;
        String sql;
        int sum = 0;
        
        sql= "Select COUNT(*) AS ACOUNT from SUBMISSIONS where (STDID='"+stdID+"' AND QUIZID IN(Select QUIZID From QUIZES where COURSEID='"+cID+"'))OR (STDID='"+stdID+"' AND ASSIGNMENTID IN(Select ASSIGNMENTID From ASSIGNMENTS where COURSEID='"+cID+"'))" ;
                                                                                                                
        
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
               sum= Integer.parseInt(rs.getString("ACOUNT"))+1;
                System.out.println("ACount: "+sum);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        
        if(sum>=3){
            return true;
        }
        else{
            return false;
        }
    }
    
    public String getCourseDetail(String cID){
       ResultSet rs1;
        String sql1;
        String result  = "";
        
        
        sql1= "Select * from COURSES where COURSEID='"+cID+"'";
        
        
        try {
            stmt = con.createStatement();
            rs1 = stmt.executeQuery(sql1);
            if(rs1.next()){
               result= ("\n     STAR ACADEMY - "+rs1.getString("DCATEGORY")+"\n\n Degree: "+rs1.getString("DEGREE")+"\n Course ID: "+rs1.getString("COURSEID")+"\n Course Title : "+rs1.getString("CRSTITLE")+"\n\n");      
            }
           
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return result;
   }
    
    
}
