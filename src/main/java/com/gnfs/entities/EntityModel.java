/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author khoders
 */
@MappedSuperclass
public class EntityModel implements Serializable{
    
    public static final String _valueDate = "valueDate";
    @Column(name = "value_date")
    @Temporal(TemporalType.DATE)
    private LocalDate valueDate;
    
    public static final String _createdDate = "createdDate";
    @Column(name = "date_created")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate = LocalDateTime.now();
    
    public static final String _lastModifiedDate = "lastModifiedDate";
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.DATE)
    private LocalDateTime lastModifiedDate; 
    
    public static final String _lastModifiedBy = "lastModifiedBy";
    @Column(name = "last_modified_by")
    private String lastModifiedBy;

    public LocalDateTime getCreatedDate()
    {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate)
    {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate()
    {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate)
    {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    
    public LocalDate getValueDate() {
        return valueDate;
    }

    public void setValueDate(LocalDate valueDate) {
        this.valueDate = valueDate;
    }
    
}
