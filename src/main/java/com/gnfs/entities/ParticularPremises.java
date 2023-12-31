package com.gnfs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@Entity
@Table(name = "particulars_premises")
public class ParticularPremises extends BaseModel {
    public static final String _incharge = "incharge";
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "prem_type")
    private String premType;
    
    @Column(name = "location")
    private String location;
    
    @Column(name = "land_mark")
    private String landMark;
    
    public static final String _telephone = "telephone";
    @Column(name = "telephone")
    private String telephone;

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

    public String getPremType() {
        return premType;
    }

    public void setPremType(String premType) {
        this.premType = premType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}
