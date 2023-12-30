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
    private Integer dcpQty;
    
    @Column(name = "dcp_inst_date")
    @Temporal(TemporalType.DATE)
    private Date dcpInstDate;
    
    @Column(name = "dcp_service_date")
    @Temporal(TemporalType.DATE)
    private Date dcpServiceDate;
    
    @Column(name = "dcp_service_by")
    private String dcpServiceby;
        
    
    @Column(name = "emergency_light_qty")
    private Integer emergencyLightQty;
    
    @Column(name = "emergency_light_inst_date")
    @Temporal(TemporalType.DATE)
    private Date emergencyLightInstDate;
    
    @Column(name = "emergency_Light_service_date")
    @Temporal(TemporalType.DATE)
    private Date emergencyLightServiceDate;
    
    @Column(name = "emergency_Light_service_by")
    private String emergencyLightServiceby;
        
    
    @Column(name = "smoke_detector_qty")
    private Integer smokeDetectorQty;
    
    @Column(name = "smoke_detector_inst_date")
    @Temporal(TemporalType.DATE)
    private Date smokeDetectorInstDate;
    
    @Column(name = "smoke_detector_service_date")
    @Temporal(TemporalType.DATE)
    private Date smokeDetectorServiceDate;
    
    @Column(name = "smoke_detector_service_by")
    private String smokeDetectorServiceby;
        
    
    @Column(name = "heat_detector_qty")
    private Integer heatDetectorQty;
    
    @Column(name = "heat_detector_inst_date")
    @Temporal(TemporalType.DATE)
    private Date heatDetectorInstDate;
    
    @Column(name = "heat_detector_service_date")
    @Temporal(TemporalType.DATE)
    private Date heatDetectorServiceDate;
    
    @Column(name = "heat_detector_service_by")
    private String heatDetectorServiceby;
    
    
    @Column(name = "fire_alarm_qty")
    private Integer fireAlarmQty;
    
    @Column(name = "fire_alarm_inst_date")
    @Temporal(TemporalType.DATE)
    private Date fireAlarmInstDate;
    
    @Column(name = "fire_alarm_service_date")
    @Temporal(TemporalType.DATE)
    private Date fireAlarmServiceDate;
    
    @Column(name = "fire_alarm_service_by")
    private String fireAlarmServiceby;
    
    
    @Column(name = "general_notice_qty")
    private Integer generalNoticeQty;
    
    @Column(name = "exit_sign_qty")
    private Integer exitSignQty;
    
    @Column(name = "assemble_point_qty")
    private Integer assemblyPointQty;
    
    @Column(name = "water_source")
    private String waterSource;
    
    @Column(name = "water_source_qty")
    private Integer waterSourceQty;

    public Incharge getIncharge() {
        return incharge;
    }

    public void setIncharge(Incharge incharge) {
        this.incharge = incharge;
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

    public String getEmergencyLightServiceby() {
        return emergencyLightServiceby;
    }

    public void setEmergencyLightServiceby(String emergencyLightServiceby) {
        this.emergencyLightServiceby = emergencyLightServiceby;
    }

    public String getSmokeDetectorServiceby() {
        return smokeDetectorServiceby;
    }

    public void setSmokeDetectorServiceby(String smokeDetectorServiceby) {
        this.smokeDetectorServiceby = smokeDetectorServiceby;
    }

    public String getHeatDetectorServiceby() {
        return heatDetectorServiceby;
    }

    public void setHeatDetectorServiceby(String heatDetectorServiceby) {
        this.heatDetectorServiceby = heatDetectorServiceby;
    }

    public String getFireAlarmServiceby() {
        return fireAlarmServiceby;
    }

    public void setFireAlarmServiceby(String fireAlarmServiceby) {
        this.fireAlarmServiceby = fireAlarmServiceby;
    }

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
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

    public Integer getDcpQty() {
        return dcpQty;
    }

    public void setDcpQty(Integer dcpQty) {
        this.dcpQty = dcpQty;
    }

    public Integer getEmergencyLightQty() {
        return emergencyLightQty;
    }

    public void setEmergencyLightQty(Integer emergencyLightQty) {
        this.emergencyLightQty = emergencyLightQty;
    }

    public Integer getSmokeDetectorQty() {
        return smokeDetectorQty;
    }

    public void setSmokeDetectorQty(Integer smokeDetectorQty) {
        this.smokeDetectorQty = smokeDetectorQty;
    }

    public Integer getHeatDetectorQty() {
        return heatDetectorQty;
    }

    public void setHeatDetectorQty(Integer heatDetectorQty) {
        this.heatDetectorQty = heatDetectorQty;
    }

    public Integer getFireAlarmQty() {
        return fireAlarmQty;
    }

    public void setFireAlarmQty(Integer fireAlarmQty) {
        this.fireAlarmQty = fireAlarmQty;
    }

    public Integer getGeneralNoticeQty() {
        return generalNoticeQty;
    }

    public void setGeneralNoticeQty(Integer generalNoticeQty) {
        this.generalNoticeQty = generalNoticeQty;
    }

    public Integer getExitSignQty() {
        return exitSignQty;
    }

    public void setExitSignQty(Integer exitSignQty) {
        this.exitSignQty = exitSignQty;
    }

    public Integer getAssemblyPointQty() {
        return assemblyPointQty;
    }

    public void setAssemblyPointQty(Integer assemblyPointQty) {
        this.assemblyPointQty = assemblyPointQty;
    }

    public Integer getWaterSourceQty() {
        return waterSourceQty;
    }

    public void setWaterSourceQty(Integer waterSourceQty) {
        this.waterSourceQty = waterSourceQty;
    }    
}
