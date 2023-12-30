/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Richard Narh
 */
@Entity
@Table(name = "settings")
public class Settings extends BaseModel{
    @Column(name = "sender_id")
    private String senderId;
    
    @Column(name = "expiry_limit")
    private String expiryLimit;

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getExpiryLimit() {
        return expiryLimit;
    }

    public void setExpiryLimit(String expiryLimit) {
        this.expiryLimit = expiryLimit;
    }
}
