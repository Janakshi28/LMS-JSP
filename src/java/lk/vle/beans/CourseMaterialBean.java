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
public class CourseMaterialBean {
    private String materialID;
    private String mTitle;
    private String mType;
    private String mFileName;
    private boolean visibility;
    private String crsID;
    Connection con;
    Statement stmt, stmt1,stmt2;

    public CourseMaterialBean(Connection con) {
        this.con = con;
    }

    public CourseMaterialBean() {
        materialID = "MTXXX";
    }

    public void init(String materialID, String mTitle, String mType, String mFileName, boolean visibility,String crsID) {
        this.materialID = materialID;
        this.mTitle = mTitle;
        this.mType = mType;
        this.mFileName = mFileName;
        this.visibility = visibility;
        this.crsID = crsID;
    }

    public boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public String getMaterialID() {
        return materialID;
    }

    public void setMaterialID(String materialID) {
        this.materialID = materialID;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getmFileName(String mID) {
        String result="";
        try {
            
            
            String sql = "Select * from COURSEMATERIALS where MATERIALID = '"+mID+"'";
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                result= rs.getString("MFILENAME");
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DbConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void setmFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    public String getCrsID() {
        return crsID;
    }

    public void setCrsID(String crsID) {
        this.crsID = crsID;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void setStmt(Statement stmt) {
        this.stmt = stmt;
    }
    
    
    
    public boolean modifyMaterialFile(String mID, String fName){
       String sql;
        sql= "UPDATE COURSEMATERIALS SET MFILENAME='"+fName+"' WHERE MATERIALID='"+mID+"'";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
           return false;
        }
         
    }
    
    public boolean deleteMaterial(String mID){
       String sql;
        sql= "DELETE FROM COURSEMATERIALS WHERE MATERIALID='"+mID+"'";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
           return false;
        }
         
    }
    
    public boolean insertMaterialTODB(String mID, String title, String type, String fName, boolean v, String cID){
       String sql;
        sql= "INSERT INTO COURSEMATERIALS VALUES('"+mID+"','"+title+"','"+type+"','"+fName+"',"+v+",'"+cID+"')" ;
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql)== 1;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
         return false;
    }
    
    public boolean modifyVisibility(String mID){
       String sql;
       boolean v = this.getVisibility();
        sql= "UPDATE COURSEMATERIALS SET VISIBILITY='"+v+"' WHERE MATERIALID='"+mID+"'";
        try {
            stmt = con.createStatement();
            return stmt.executeUpdate(sql) == 1;
        } catch (SQLException ex) {
           return false;
        }
         
    }
    
}
