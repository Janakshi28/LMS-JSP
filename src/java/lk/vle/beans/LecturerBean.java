/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.beans;

import java.util.ArrayList;

/**
 *
 * @author Janakshi
 */
public class LecturerBean extends UserBean{
    
    private String lecID;
    private String lecName;
    private String lecSection;
    private String lecMail;
    private boolean isCC;
    private String enKey;
    ArrayList<AssessmentBean>submissions = new ArrayList<AssessmentBean>();

    public LecturerBean(){
        lecID = "LXXXX";
    }

    public void init(String lecID, String lecName, String lecSection, String lecMail, boolean isCC, String enKey) {
        this.lecID = lecID;
        this.lecName = lecName;
        this.lecSection = lecSection;
        this.lecMail = lecMail;
        this.isCC = isCC;
        this.enKey = enKey;
    }

    public String getEnKey() {
        return enKey;
    }

    public void setEnKey(String enKey) {
        this.enKey = enKey;
    }

    public String getLecID() {
        return lecID;
    }

    public void setLecID(String lecID) {
        this.lecID = lecID;
    }

    public String getLecName() {
        return lecName;
    }

    public void setLecName(String lecName) {
        this.lecName = lecName;
    }

    public String getLecSection() {
        return lecSection;
    }

    public void setLecSection(String lecSection) {
        this.lecSection = lecSection;
    }

    public String getLecMail() {
        return lecMail;
    }

    public void setLecMail(String lecMail) {
        this.lecMail = lecMail;
    }

    public boolean getIsCC() {
        return isCC;
    }

    public void setIsCC(boolean isCC) {
        this.isCC = isCC;
    }

    public ArrayList<AssessmentBean> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(ArrayList<AssessmentBean> submissions) {
        this.submissions = submissions;
    }
    
    public void addSubmission(AssessmentBean a){
        this.submissions.add(a);
    }
    
    
    
}
