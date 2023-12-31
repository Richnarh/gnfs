/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Richard Narh
 */
@Entity
@Table(name = "fire_safety_certificate")
public class SafetyCertificate extends BaseModel{
    public static final String _incharge = "incharge";
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    public static final String _particularPremises = "particularPremises";
    @JoinColumn(name = "particulars_premises", referencedColumnName = "id")
    @ManyToOne
    private ParticularPremises particularPremises;
    
    @Column(name = "name")
    private String name;
    
    public static final String _houseNo = "houseNo";
    @Column(name = "house_no")
    private String houseNo;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "type_of_risk")
    private String typeOfRisk;
    
    @Column(name = "issued_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    
    @Column(name = "gprs")
    private String gprs;
    
    @Column(name = "expiry_date")
    @Temporal(TemporalType.DATE)
    private Date expiryDate;
    
    public static final String _certificateNo = "certificateNo";
    @Column(name = "certificate_no")
    private String certificateNo;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTypeOfRisk() {
        return typeOfRisk;
    }

    public void setTypeOfRisk(String typeOfRisk) {
        this.typeOfRisk = typeOfRisk;
    }

    public String getGprs() {
        return gprs;
    }

    public void setGprs(String gprs) {
        this.gprs = gprs;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public ParticularPremises getParticularPremises() {
        return particularPremises;
    }

    public void setParticularPremises(ParticularPremises particularPremises) {
        this.particularPremises = particularPremises;
    }
    
}
