/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.model;

import java.util.Date;

/**
 *
 * @author Richard Narh
 */
public class ExpiredItem {
    private Integer id;
    private Date serviceDate;
    private String premisesName;
    private String certificateNo;
    private String inchargeName;

    public ExpiredItem() {
    }

    public ExpiredItem(Integer id, Date serviceDate, String premisesName, String certificateNo, String inchargeName) {
        this.id = id;
        this.serviceDate = serviceDate;
        this.premisesName = premisesName;
        this.certificateNo = certificateNo;
        this.inchargeName = inchargeName;
    }
    
    public ExpiredItem newInstance(Integer id){
        this.id = id;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getPremisesName() {
        return premisesName;
    }

    public void setPremisesName(String premisesName) {
        this.premisesName = premisesName;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    public String getInchargeName() {
        return inchargeName;
    }

    public void setInchargeName(String inchargeName) {
        this.inchargeName = inchargeName;
    }
    
}
