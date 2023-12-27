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
@Table(name = "fire_fighting_equipment")
public class FireFightingEquipment extends BaseModel{
    @JoinColumn(name = "incharge", referencedColumnName = "id")
    @ManyToOne
    private Incharge incharge;
    
    @Column(name = "dcp_qty")
    private int dcpQty;
    
    @Column(name = "dcp_inst_date")
    @Temporal(TemporalType.DATE)
    private Date dcpInstDate;
    
    @Column(name = "dcp_service_date")
    @Temporal(TemporalType.DATE)
    private Date dcpServiceDate;
    
    @Column(name = "dcp_service_by")
    private String dcpServiceby;
        
    
    @Column(name = "emergency_light_qty")
    private int emergencyLightQty;
    
    @Column(name = "emergency_light_inst_date")
    @Temporal(TemporalType.DATE)
    private Date emergencyLightInstDate;
    
    @Column(name = "emergency_Light_service_date")
    @Temporal(TemporalType.DATE)
    private Date emergencyLightServiceDate;
    
    @Column(name = "emergency_Light_service_by")
    private String emergencyLightServiceby;
        
    
    @Column(name = "smoke_detector_qty")
    private int smokeDetectorQty;
    
    @Column(name = "smoke_detector_inst_date")
    @Temporal(TemporalType.DATE)
    private Date smokeDetectorInstDate;
    
    @Column(name = "smoke_detector_service_date")
    @Temporal(TemporalType.DATE)
    private Date smokeDetectorServiceDate;
    
    @Column(name = "smoke_detector_service_by")
    @Temporal(TemporalType.DATE)
    private String smokeDetectorServiceby;
        
    
    @Column(name = "heat_detector_qty")
    private int heatDetectorQty;
    
    @Column(name = "heat_detector_inst_date")
    @Temporal(TemporalType.DATE)
    private Date heatDetectorInstDate;
    
    @Column(name = "heat_detector_service_date")
    @Temporal(TemporalType.DATE)
    private Date heatDetectorServiceDate;
    
    @Column(name = "heat_detector_service_by")
    private String heatDetectorServiceby;
    
    
    @Column(name = "fire_alarm_qty")
    private int fireAlarmQty;
    
    @Column(name = "fire_alarm_inst_date")
    @Temporal(TemporalType.DATE)
    private Date fireAlarmInstDate;
    
    @Column(name = "fire_alarm_service_date")
    @Temporal(TemporalType.DATE)
    private Date fireAlarmServiceDate;
    
    @Column(name = "fire_alarm_service_by")
    private String fireAlarmServiceby;
    
    
    @Column(name = "general_notice_qty")
    private int generalNoticeQty;
    
    @Column(name = "exit_sign_qty")
    private int exitSignQty;
    
    @Column(name = "assemble_point_qty")
    private int assemblyPointQty;
    
    @Column(name = "water_source")
    private String waterSource;
    
    @Column(name = "water_source_qty")
    private int waterSourceQty;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
    }

    public int getDcpQty() {
        return dcpQty;
    }

    public void setDcpQty(int dcpQty) {
        this.dcpQty = dcpQty;
    }

    public Date getDcpInstDate() {
        return dcpInstDate;
    }

    public void setDcpInstDate(Date dcpInstDate) {
        this.dcpInstDate = dcpInstDate;
    }
    
    public String getDcpServiceby() {
        return dcpServiceby;
    }

    public void setDcpServiceby(String dcpServiceby) {
        this.dcpServiceby = dcpServiceby;
    }

    public int getEmergencyLightQty() {
        return emergencyLightQty;
    }

    public void setEmergencyLightQty(int emergencyLightQty) {
        this.emergencyLightQty = emergencyLightQty;
    }

    public String getEmergencyLightServiceby() {
        return emergencyLightServiceby;
    }

    public void setEmergencyLightServiceby(String emergencyLightServiceby) {
        this.emergencyLightServiceby = emergencyLightServiceby;
    }

    public int getSmokeDetectorQty() {
        return smokeDetectorQty;
    }

    public void setSmokeDetectorQty(int smokeDetectorQty) {
        this.smokeDetectorQty = smokeDetectorQty;
    }

    public String getSmokeDetectorServiceby() {
        return smokeDetectorServiceby;
    }

    public void setSmokeDetectorServiceby(String smokeDetectorServiceby) {
        this.smokeDetectorServiceby = smokeDetectorServiceby;
    }

    public int getHeatDetectorQty() {
        return heatDetectorQty;
    }

    public void setHeatDetectorQty(int heatDetectorQty) {
        this.heatDetectorQty = heatDetectorQty;
    }

    public String getHeatDetectorServiceby() {
        return heatDetectorServiceby;
    }

    public void setHeatDetectorServiceby(String heatDetectorServiceby) {
        this.heatDetectorServiceby = heatDetectorServiceby;
    }

    public int getFireAlarmQty() {
        return fireAlarmQty;
    }

    public void setFireAlarmQty(int fireAlarmQty) {
        this.fireAlarmQty = fireAlarmQty;
    }

    public String getFireAlarmServiceby() {
        return fireAlarmServiceby;
    }

    public void setFireAlarmServiceby(String fireAlarmServiceby) {
        this.fireAlarmServiceby = fireAlarmServiceby;
    }

    public int getGeneralNoticeQty() {
        return generalNoticeQty;
    }

    public void setGeneralNoticeQty(int generalNoticeQty) {
        this.generalNoticeQty = generalNoticeQty;
    }

    public int getExitSignQty() {
        return exitSignQty;
    }

    public void setExitSignQty(int exitSignQty) {
        this.exitSignQty = exitSignQty;
    }

    public int getAssemblyPointQty() {
        return assemblyPointQty;
    }

    public void setAssemblyPointQty(int assemblyPointQty) {
        this.assemblyPointQty = assemblyPointQty;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }
    
    public int getWaterSourceQty() {
        return waterSourceQty;
    }

    public void setWaterSourceQty(int waterSourceQty) {
        this.waterSourceQty = waterSourceQty;
    }

    public Date getDcpServiceDate() {
        return dcpServiceDate;
    }

    public void setDcpServiceDate(Date dcpServiceDate) {
        this.dcpServiceDate = dcpServiceDate;
    }

    public Date getEmergencyLightInstDate() {
        return emergencyLightInstDate;
    }

    public void setEmergencyLightInstDate(Date emergencyLightInstDate) {
        this.emergencyLightInstDate = emergencyLightInstDate;
    }

    public Date getEmergencyLightServiceDate() {
        return emergencyLightServiceDate;
    }

    public void setEmergencyLightServiceDate(Date emergencyLightServiceDate) {
        this.emergencyLightServiceDate = emergencyLightServiceDate;
    }

    public Date getSmokeDetectorInstDate() {
        return smokeDetectorInstDate;
    }

    public void setSmokeDetectorInstDate(Date smokeDetectorInstDate) {
        this.smokeDetectorInstDate = smokeDetectorInstDate;
    }

    public Date getSmokeDetectorServiceDate() {
        return smokeDetectorServiceDate;
    }

    public void setSmokeDetectorServiceDate(Date smokeDetectorServiceDate) {
        this.smokeDetectorServiceDate = smokeDetectorServiceDate;
    }

    public Date getHeatDetectorInstDate() {
        return heatDetectorInstDate;
    }

    public void setHeatDetectorInstDate(Date heatDetectorInstDate) {
        this.heatDetectorInstDate = heatDetectorInstDate;
    }

    public Date getHeatDetectorServiceDate() {
        return heatDetectorServiceDate;
    }

    public void setHeatDetectorServiceDate(Date heatDetectorServiceDate) {
        this.heatDetectorServiceDate = heatDetectorServiceDate;
    }

    public Date getFireAlarmInstDate() {
        return fireAlarmInstDate;
    }

    public void setFireAlarmInstDate(Date fireAlarmInstDate) {
        this.fireAlarmInstDate = fireAlarmInstDate;
    }

    public Date getFireAlarmServiceDate() {
        return fireAlarmServiceDate;
    }

    public void setFireAlarmServiceDate(Date fireAlarmServiceDate) {
        this.fireAlarmServiceDate = fireAlarmServiceDate;
    }
    
}
