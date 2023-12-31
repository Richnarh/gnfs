/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.services;

import com.gnfs.entities.FireFightingEquipment;
import com.gnfs.entities.Incharge;
import com.gnfs.entities.ParticularOccupyers;
import com.gnfs.entities.ParticularOwners;
import com.gnfs.entities.ParticularPremises;
import com.gnfs.entities.SafetyCertificate;
import com.gnfs.entities.SpecialInstallation;
import com.gnfs.entities.TrainedFireSafetyStaff;
import com.gnfs.util.HibernateUtil;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Richard Narh
 */
public class GnfsManager {
    public static List<ParticularOwners> createOwners(LinkedHashMap<Integer, LinkedHashSet<TextField>> fieldMap) {
        List<ParticularOwners> ownerList = new LinkedList<>();
        LinkedHashSet<TextField> fieldList = new LinkedHashSet<>();

        for (Integer key : fieldMap.keySet()) {
            fieldList = fieldMap.get(key);
            ParticularOwners owner = new ParticularOwners();
            for (TextField field : fieldList) {
                if (field.getId().equalsIgnoreCase("ownerNameField" + key)) {
                    owner.setOwnerName(field.getText());
                } else if (field.getId().equalsIgnoreCase("ownerTeleHandyField" + key)) {
                    owner.setTeleHandy(field.getText());
                } else if (field.getId().equalsIgnoreCase("ownerTeleOfficeField" + key)) {
                    owner.setTeleOffice(field.getText());
                } else if (field.getId().equalsIgnoreCase("ownerPurposeField" + key)) {
                    owner.setPurpose(field.getText());
                }
            }
            ownerList.add(owner);
        }
        
        System.out.println("ownerList: " + ownerList.size());
        for (ParticularOwners po : ownerList) {
            System.out.println("Name: " + po.getOwnerName() +"\t Tele-Handy: "+po.getTeleHandy() +"\t Tele-Office: "+po.getTeleOffice() +"\t Purpose: "+po.getPurpose());
        }
        return ownerList;
    }

    public static List<ParticularOccupyers> createOccupyers(LinkedHashMap<Integer, LinkedHashSet<TextField>> occupyerMap) {
        List<ParticularOccupyers> occupyerList = new LinkedList<>();
        LinkedHashSet<TextField> fieldList = new LinkedHashSet<>();
        
        for (Integer key : occupyerMap.keySet()) {
            fieldList = occupyerMap.get(key);
            ParticularOccupyers occupyers = new ParticularOccupyers();
            for (TextField field : fieldList) {
                if (field.getId().equalsIgnoreCase("occupyerNameField" + key)) {
                    occupyers.setOccupyerName(field.getText());
                } else if (field.getId().equalsIgnoreCase("occupyerTeleHandyField" + key)) {
                    occupyers.setTeleHandy(field.getText());
                } else if (field.getId().equalsIgnoreCase("occupyerTeleOfficeField" + key)) {
                    occupyers.setTeleOffice(field.getText());
                } else if (field.getId().equalsIgnoreCase("occupyerPurposeField" + key)) {
                    occupyers.setUsageActivate(field.getText());
                }
            }
            occupyerList.add(occupyers);
        }
        System.out.println("occupyerList: " + occupyerList.size());
        occupyerList.forEach(occupyer -> {
            System.out.println("Name: " + occupyer.getOccupyerName() +"\t Tele-Handy: "+occupyer.getTeleHandy() +"\t Tele-Office: "+occupyer.getTeleOffice() +"\t Usage/Activity: "+occupyer.getUsageActivate());
        });
        return occupyerList;
    }

    public static List<TrainedFireSafetyStaff> createTrainedFireSafetyStaff(LinkedHashMap<Integer, LinkedHashSet<TextField>> trainedFireSafetyStaffMap) {
        List<TrainedFireSafetyStaff> trainedFireSafetyStaffList = new LinkedList<>();
        LinkedHashSet<TextField> fieldList = new LinkedHashSet<>();
        
        for (Integer key : trainedFireSafetyStaffMap.keySet()) {
            fieldList = trainedFireSafetyStaffMap.get(key);
            TrainedFireSafetyStaff safetyStaff = new TrainedFireSafetyStaff();
            for (Object obj : fieldList) {
                if (obj instanceof DatePicker) {
                    DatePicker dateField = (DatePicker) obj;
                    if (dateField.getId().equalsIgnoreCase("staffDateField" + key)) {
                        safetyStaff.setTrainedDate(Date.from(Instant.from(dateField.getValue().atStartOfDay(ZoneId.systemDefault()))));
                    }
                } else {
                    TextField field = (TextField) obj;
                    if (field != null) {
                        if (field.getId().equalsIgnoreCase("staffNameField" + key)) {
                            safetyStaff.setName(field.getText());
                        } else if (field.getId().equalsIgnoreCase("staffPositionField" + key)) {
                            safetyStaff.setPosition(field.getText());
                        } else if (field.getId().equalsIgnoreCase("staffTrainingField" + key)) {
                            safetyStaff.setTypeOftraining(field.getText());
                        } else if (field.getId().equalsIgnoreCase("staffTelephoneField" + key)) {
                            safetyStaff.setTelephoneNo(field.getText());
                        }
                    }
                }
            }
            trainedFireSafetyStaffList.add(safetyStaff);
        }
        System.out.println("trainedFireSafetyStaffList: " + trainedFireSafetyStaffList.size());
        trainedFireSafetyStaffList.forEach(trainedStaff -> {
            System.out.println("Name: " + trainedStaff.getName() +"\t Position: "+trainedStaff.getPosition() +"\t Training: "+trainedStaff.getTypeOftraining() +"\t Date: "+trainedStaff.getTrainedDate() +"\t Telephone: "+trainedStaff.getTelephoneNo());
        });
        return trainedFireSafetyStaffList;
    }
    
    public static Incharge getIncharge(String officerName, String signature){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(Incharge.class);
        crit.add(Restrictions.eq(Incharge._officerInCharge, officerName));
        crit.add(Restrictions.eq(Incharge._signature, signature));
        return (Incharge)crit.uniqueResult();
    }
    
    public static Incharge getIncharge(String officerInChargeId){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(Incharge.class);
        crit.add(Restrictions.eq(Incharge._id, officerInChargeId));
        return (Incharge)crit.uniqueResult();
    }
    
    public static ParticularPremises getPremisesById(String premisesId){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(ParticularPremises.class);
        crit.add(Restrictions.eq(ParticularPremises._id, premisesId));
        return (ParticularPremises)crit.uniqueResult();
    }
    
    public static List<FireFightingEquipment> getFireFightingByIncharge(Incharge incharge){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(FireFightingEquipment.class);
        crit.add(Restrictions.eq(FireFightingEquipment._incharge, incharge));
        return crit.list();
    }
    
    public static ParticularPremises getParticularPremises(Incharge incharge){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(ParticularPremises.class);
        crit.add(Restrictions.eq(ParticularPremises._incharge, incharge));
        return (ParticularPremises)crit.uniqueResult();
    }
    public static ParticularPremises searchPremises(String telephone){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(ParticularPremises.class);
        crit.add(Restrictions.ilike(ParticularPremises._telephone, telephone, MatchMode.ANYWHERE));
        return (ParticularPremises)crit.uniqueResult();
    }
    
    public static SafetyCertificate getSafetyCertificate(Incharge incharge){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(SafetyCertificate.class);
        crit.add(Restrictions.eq(SafetyCertificate._incharge, incharge));
        return (SafetyCertificate)crit.uniqueResult();
    }
    
    public static SafetyCertificate searchCert(String cert, String hseNo){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(SafetyCertificate.class);
        if(hseNo != null)
            crit.add(Restrictions.ilike(SafetyCertificate._houseNo, hseNo, MatchMode.ANYWHERE));
        if(cert != null)
            crit.add(Restrictions.ilike(SafetyCertificate._certificateNo, cert, MatchMode.ANYWHERE));
        return (SafetyCertificate)crit.uniqueResult();
    }

    public static FireFightingEquipment getFireFightingEquipment(ParticularPremises particularPremises) {
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(FireFightingEquipment.class);
        crit.add(Restrictions.eq(FireFightingEquipment._particularPremises, particularPremises));
        return (FireFightingEquipment)crit.uniqueResult();
    }

    public static SpecialInstallation getSpecialInstallation(ParticularPremises particularPremises) {
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(SpecialInstallation.class);
        crit.add(Restrictions.eq(SpecialInstallation._particularPremises, particularPremises));
        return (SpecialInstallation)crit.uniqueResult();
    }
}
