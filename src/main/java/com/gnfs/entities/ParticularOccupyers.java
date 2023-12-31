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
@Table(name = "particulars_occupyers")
public class ParticularOccupyers extends BaseModel{
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    public static final String _particularPremises = "particularPremises";
    @JoinColumn(name = "particulars_premises", referencedColumnName = "id")
    @ManyToOne
    private ParticularPremises particularPremises;
    
    @Column(name = "occupyer_name")
    private String occupyerName;
    
    @Column(name = "tele_handy")
    private String teleHandy;
    
    @Column(name = "tele_office")
    private String teleOffice;
    
    @Column(name = "usage_activate")
    private String usageActivate;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
    }

    public String getOccupyerName() {
        return occupyerName;
    }

    public void setOccupyerName(String occupyerName) {
        this.occupyerName = occupyerName;
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

    public String getUsageActivate() {
        return usageActivate;
    }

    public void setUsageActivate(String usageActivate) {
        this.usageActivate = usageActivate;
    }

    public ParticularPremises getParticularPremises() {
        return particularPremises;
    }

    public void setParticularPremises(ParticularPremises particularPremises) {
        this.particularPremises = particularPremises;
    }
    
}
