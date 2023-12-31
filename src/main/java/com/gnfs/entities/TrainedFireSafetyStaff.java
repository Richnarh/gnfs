/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

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
@Table(name = "fire_safety_staff")
public class TrainedFireSafetyStaff extends BaseModel{
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    public static final String _particularPremises = "particularPremises";
    @JoinColumn(name = "particulars_premises", referencedColumnName = "id")
    @ManyToOne
    private ParticularPremises particularPremises;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "position")
    private String position;
    
    @Column(name = "type_of_training")
    private String typeOftraining;
    
    @Column(name = "trained_date")
    @Temporal(TemporalType.DATE)
    private Date trainedDate;
    
    @Column(name = "telephone_no")
    private String telephoneNo;

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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTypeOftraining() {
        return typeOftraining;
    }

    public void setTypeOftraining(String typeOftraining) {
        this.typeOftraining = typeOftraining;
    }

    public Date getTrainedDate() {
        return trainedDate;
    }

    public void setTrainedDate(Date trainedDate) {
        this.trainedDate = trainedDate;
    }
    
    public String getTelephoneNo() {
        return telephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        this.telephoneNo = telephoneNo;
    }

    public ParticularPremises getParticularPremises() {
        return particularPremises;
    }

    public void setParticularPremises(ParticularPremises particularPremises) {
        this.particularPremises = particularPremises;
    }
    
}
