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
@Table(name = "media_data")
public class MediaData extends BaseModel{
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    public static final String _particularPremises = "particularPremises";
    @JoinColumn(name = "particulars_premises", referencedColumnName = "id")
    @ManyToOne
    private ParticularPremises particularPremises;
    
    @Column(name = "media_path")
    private String mediaPath;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public ParticularPremises getParticularPremises() {
        return particularPremises;
    }

    public void setParticularPremises(ParticularPremises particularPremises) {
        this.particularPremises = particularPremises;
    }
    
}
