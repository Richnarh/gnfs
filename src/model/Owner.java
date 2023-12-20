/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Richard Narh
 */
public class Owner {
    private String ownerName;
    private String teleHandy;
    private String teleOffice;
    private String purpose;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getTeleHandy() {
        return teleHandy;
    }

    public void setTeleHandy(String teleHandy) {
        this.teleHandy = teleHandy;
    }

    public String getTeleOffice() {
        return teleOffice;
    }

    public void setTeleOffice(String teleOffice) {
        this.teleOffice = teleOffice;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    
}
