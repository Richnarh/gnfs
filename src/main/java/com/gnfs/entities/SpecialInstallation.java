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
@Table(name = "special_installation")
public class SpecialInstallation extends BaseModel{
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    @Column(name = "hydrant")
    private String hydrant;
    
    @Column(name = "smoke_extractor")
    private String smokeExtractor;
    
    @Column(name = "dry_risers")
    private String dryRisers;
    
    @Column(name = "heat_extractor")
    private String heatExtractor;
    
    @Column(name = "wet_risers")
    private String wetRisers;
    
    @Column(name = "hose_reel")
    private String hoseReel;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
    }

    public String getHydrant() {
        return hydrant;
    }

    public void setHydrant(String hydrant) {
        this.hydrant = hydrant;
    }

    public String getSmokeExtractor() {
        return smokeExtractor;
    }

    public void setSmokeExtractor(String smokeExtractor) {
        this.smokeExtractor = smokeExtractor;
    }

    public String getDryRisers() {
        return dryRisers;
    }

    public void setDryRisers(String dryRisers) {
        this.dryRisers = dryRisers;
    }

    public String getHeatExtractor() {
        return heatExtractor;
    }

    public void setHeatExtractor(String heatExtractor) {
        this.heatExtractor = heatExtractor;
    }

    public String getWetRisers() {
        return wetRisers;
    }

    public void setWetRisers(String wetRisers) {
        this.wetRisers = wetRisers;
    }

    public String getHoseReel() {
        return hoseReel;
    }

    public void setHoseReel(String hoseReel) {
        this.hoseReel = hoseReel;
    }
    
}
