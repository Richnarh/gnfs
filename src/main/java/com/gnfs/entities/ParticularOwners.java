/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Richard Narh
 */
@Entity
@Table(name = "particulars_owners")
public class ParticularOwners extends BaseModel{
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    @Column(name = "owner_name")
    private String ownerName;
    
    @Column(name = "tele_handy")
    private String teleHandy;
    
    @Column(name = "tele_office")
    private String teleOffice;
    
    @Column(name = "purchase")
    private String purchase;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
    }

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

    public String getPurchase() {
        return purchase;
    }

    public void setPurchase(String purchase) {
        this.purchase = purchase;
    }
}
