/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.vle.beans;

/**
 *
 * @author Janakshi
 */
public class AdminBean extends UserBean{
    
    private String adminID;
    private String adName;
    private String adSection;

    public AdminBean() {
        adminID= "ADXX";
    }

    public void init(String adminID, String adName, String adSection) {
        this.adminID = adminID;
        this.adName = adName;
        this.adSection = adSection;
    }

    public String getAdSection() {
        return adSection;
    }

    public void setAdSection(String adSection) {
        this.adSection = adSection;
    }

    public String getAdminID() {
        return adminID;
    }

    public void setAdminID(String adminID) {
        this.adminID = adminID;
    }

    public String getAdName() {
        return adName;
    }

    public void setAdName(String adName) {
        this.adName = adName;
    }
    
    
    
    
    
    
}
