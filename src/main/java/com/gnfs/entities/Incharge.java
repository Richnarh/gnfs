/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Richard Narh
 */
@Entity
@Table(name = "incharge")
public class Incharge extends BaseModel{
    public static final String _officerInCharge = "officerInCharge";
    @Column(name = "officer_in_charge")
    private String officerInCharge;
    
    @Column(name = "date_of_collection")
    @Temporal(TemporalType.DATE)
    private Date dateOfCollection;
    
    public static final String _signature = "signature";
    @Column(name = "signature")
    private String signature;

    public String getOfficerInCharge() {
        return officerInCharge;
    }

    public void setOfficerInCharge(String officerInCharge) {
        this.officerInCharge = officerInCharge;
    }

    public Date getDateOfCollection() {
        return dateOfCollection;
    }

    public void setDateOfCollection(Date dateOfCollection) {
        this.dateOfCollection = dateOfCollection;
    }
    
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
}
