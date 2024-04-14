/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.model;

import java.util.Date;

/**
 *
 * @author Richard Narh
 */
public class FireFightingEquipmentDto extends BasePojo{
    private Integer dcpQty;
    private Date dcpInstDate;
    private Date dcpServiceDate;
    private String dcpServiceby;
    private Integer emergencyLightQty;
    private Date emergencyLightInstDate;
    private Date emergencyLightServiceDate;
    private String emergencyLightServiceby;
    private Integer smokeDetectorQty;
    private Date smokeDetectorInstDate;
    private Date smokeDetectorServiceDate;
    private String smokeDetectorServiceby;
    private Integer heatDetectorQty;
    private Date heatDetectorInstDate;
    private Date heatDetectorServiceDate;
    private String heatDetectorServiceby;
    private Integer fireAlarmQty;
    private Date fireAlarmInstDate;
    private Date fireAlarmServiceDate;
    private String fireAlarmServiceby;
    private Integer generalNoticeQty;
    private Integer exitSignQty;
    private Integer assemblyPointQty;
    private String waterSource;
    private Integer waterSourceQty;

    public FireFightingEquipmentDto() {
    }

    public FireFightingEquipmentDto newInstance(String id){
        this.id = id;
        return this;
    }

    public Integer getDcpQty() {
        return dcpQty;
    }

    public void setDcpQty(Integer dcpQty) {
        this.dcpQty = dcpQty;
    }

    public Date getDcpInstDate() {
        return dcpInstDate;
    }

    public void setDcpInstDate(Date dcpInstDate) {
        this.dcpInstDate = dcpInstDate;
    }

    public Date getDcpServiceDate() {
        return dcpServiceDate;
    }

    public void setDcpServiceDate(Date dcpServiceDate) {
        this.dcpServiceDate = dcpServiceDate;
    }

    public String getDcpServiceby() {
        return dcpServiceby;
    }

    public void setDcpServiceby(String dcpServiceby) {
        this.dcpServiceby = dcpServiceby;
    }

    public Integer getEmergencyLightQty() {
        return emergencyLightQty;
    }

    public void setEmergencyLightQty(Integer emergencyLightQty) {
        this.emergencyLightQty = emergencyLightQty;
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

    public String getEmergencyLightServiceby() {
        return emergencyLightServiceby;
    }

    public void setEmergencyLightServiceby(String emergencyLightServiceby) {
        this.emergencyLightServiceby = emergencyLightServiceby;
    }

    public Integer getSmokeDetectorQty() {
        return smokeDetectorQty;
    }

    public void setSmokeDetectorQty(Integer smokeDetectorQty) {
        this.smokeDetectorQty = smokeDetectorQty;
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

    public String getSmokeDetectorServiceby() {
        return smokeDetectorServiceby;
    }

    public void setSmokeDetectorServiceby(String smokeDetectorServiceby) {
        this.smokeDetectorServiceby = smokeDetectorServiceby;
    }

    public Integer getHeatDetectorQty() {
        return heatDetectorQty;
    }

    public void setHeatDetectorQty(Integer heatDetectorQty) {
        this.heatDetectorQty = heatDetectorQty;
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

    public String getHeatDetectorServiceby() {
        return heatDetectorServiceby;
    }

    public void setHeatDetectorServiceby(String heatDetectorServiceby) {
        this.heatDetectorServiceby = heatDetectorServiceby;
    }

    public Integer getFireAlarmQty() {
        return fireAlarmQty;
    }

    public void setFireAlarmQty(Integer fireAlarmQty) {
        this.fireAlarmQty = fireAlarmQty;
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

    public String getFireAlarmServiceby() {
        return fireAlarmServiceby;
    }

    public void setFireAlarmServiceby(String fireAlarmServiceby) {
        this.fireAlarmServiceby = fireAlarmServiceby;
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

    public String getWaterSource() {
        return waterSource;
    }

    public void setWaterSource(String waterSource) {
        this.waterSource = waterSource;
    }

    public Integer getWaterSourceQty() {
        return waterSourceQty;
    }

    public void setWaterSourceQty(Integer waterSourceQty) {
        this.waterSourceQty = waterSourceQty;
    }
}
