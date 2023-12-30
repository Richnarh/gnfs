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
public class InchargeDto extends BasePojo {
    private String officerInCharge;
    private String signature;
    
    private InchargeDto() {
    }
    
    public static InchargeDto newInstance() {
        return new InchargeDto();
    }

    public InchargeDto(String id, String officerInCharge) {
        this.id = id;
        this.officerInCharge = officerInCharge;
    }
    
    public InchargeDto id(String id) {
        this.id = id;
        return this;
    }
    public InchargeDto officerInCharge(String officerInCharge) {
        this.officerInCharge = officerInCharge;
        return this;
    }

    public String getOfficerInCharge() {
        return officerInCharge;
    }

    public void setOfficerInCharge(String officerInCharge) {
        this.officerInCharge = officerInCharge;
    }
    
    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public String toString() {
        return officerInCharge;
    }
}
