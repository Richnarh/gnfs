/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author khoders
 */
@MappedSuperclass
public class BaseModel implements Serializable {

    public static final String _id = "id";
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;

    public static final String _createdDate = "createdDate";
    @Column(name = "date_created")
    @Temporal(TemporalType.DATE)
    private Date createdDate = Date.from(Instant.now());
    
    public BaseModel(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BaseModel other = (BaseModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 9;
        hash = 91 * hash + Objects.hashCode(this.id);
        return hash;
    }

}
