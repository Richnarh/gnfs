/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.model;

/**
 *
 * @author Richard Narh
 */
public class ExpiredParams extends BasePojo{
    private String premisesName;
    private String telephone;
    private String certificateNo;

    public ExpiredParams() {
    }
    
    public static ExpiredParams newInstance() {
        return new ExpiredParams();
    }
    
    public ExpiredParams(String id,String premisesName, String telephone) {
        this.id = id;
        this.premisesName = premisesName;
        this.telephone = telephone;
    }

    public ExpiredParams id(String id) {
        this.id = id;
        return this;
    }

    public ExpiredParams premisesName(String premisesName) {
        this.premisesName = premisesName;
        return this;
    }
    public String getPremisesName() {
        return premisesName;
    }

    public void setPremisesName(String premisesName) {
        this.premisesName = premisesName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCertificateNo() {
        return certificateNo;
    }

    public void setCertificateNo(String certificateNo) {
        this.certificateNo = certificateNo;
    }

    @Override
    public String toString() {
        return premisesName +" "+telephone;
    }
}
