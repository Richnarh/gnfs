/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.services;

import com.gnfs.entities.CollectionDate;
import com.gnfs.entities.FireFightingEquipment;
import com.gnfs.entities.Incharge;
import com.gnfs.entities.ParticularOccupyers;
import com.gnfs.entities.ParticularOwners;
import com.gnfs.entities.ParticularPremises;
import com.gnfs.entities.SafetyCertificate;
import com.gnfs.entities.SpecialInstallation;
import com.gnfs.entities.TrainedFireSafetyStaff;
import com.gnfs.util.DateUtil;
import com.gnfs.util.HibernateUtil;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Richard Narh
 */
public class GnfsManager {
    public static List<ParticularOwners> createOwners(LinkedHashMap<Integer, LinkedHashSet<Object>> fieldMap) {
        List<ParticularOwners> ownerList = new LinkedList<>();
        LinkedHashSet<Object> fieldList = new LinkedHashSet<>();
        for (Integer key : fieldMap.keySet()) {
            fieldList = fieldMap.get(key);
            ParticularOwners owner = new ParticularOwners();
            for (Object obj : fieldList){
                if (obj instanceof Label) {
                    Label l = (Label) obj;
                    if (!l.getId().contains("owner")){
                        System.out.println("Id: " + l.getId());
                        owner.setId(l.getId());
                    }
                } else {
                    TextField field = (TextField) obj;
                    if (field != null) {
                        if (field.getId().equalsIgnoreCase("ownerNameField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            owner.setOwnerName(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("ownerTeleHandyField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            owner.setTeleHandy(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("ownerTeleOfficeField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            owner.setTeleOffice(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("ownerPurposeField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            owner.setPurpose(field.getText().trim());
                        }
                    }
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

    public static List<ParticularOccupyers> createOccupyers(LinkedHashMap<Integer, LinkedHashSet<Object>> occupyerMap) {
        List<ParticularOccupyers> occupyerList = new LinkedList<>();
        LinkedHashSet<Object> fieldList = new LinkedHashSet<>();
        for (Integer key : occupyerMap.keySet()) {
            fieldList = occupyerMap.get(key);
            ParticularOccupyers occupyers = new ParticularOccupyers();
            for (Object obj : fieldList) {
                if (obj instanceof Label) {
                    Label l = (Label) obj;
                    if (!l.getId().contains("occupyer")) {
                        System.out.println("Id: " + l.getId());
                        occupyers.setId(l.getId());
                    }
                } else {
                    TextField field = (TextField) obj;
                    if (field != null) {
                        if (field.getId().equalsIgnoreCase("occupyerNameField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            occupyers.setOccupyerName(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("occupyerTeleHandyField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            occupyers.setTeleHandy(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("occupyerTeleOfficeField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            occupyers.setTeleOffice(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("occupyerPurposeField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            occupyers.setUsageActivate(field.getText().trim());
                        }
                    }
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

    public static List<TrainedFireSafetyStaff> createTrainedFireSafetyStaff(LinkedHashMap<Integer, LinkedHashSet<Object>> trainedFireSafetyStaffMap) {
        List<TrainedFireSafetyStaff> trainedFireSafetyStaffList = new LinkedList<>();
        LinkedHashSet<Object> fieldList = new LinkedHashSet<>();
        for (Integer key : trainedFireSafetyStaffMap.keySet()) {
            fieldList = trainedFireSafetyStaffMap.get(key);
            TrainedFireSafetyStaff safetyStaff = new TrainedFireSafetyStaff();
            for (Object obj : fieldList) {
                if (obj instanceof DatePicker) {
                    DatePicker dateField = (DatePicker) obj;
                    if (dateField.getId().equalsIgnoreCase("staffDateField" + key)) {
                        safetyStaff.setTrainedDate(DateUtil.localDateToDate(dateField.getValue()));
                    }
                } else if (obj instanceof Label) {
                    Label l = (Label) obj;
                    if (!l.getId().contains("staff")) {
                        System.out.println("Id: " + l.getId());
                        safetyStaff.setId(l.getId());
                    }
                } else {
                    TextField field = (TextField) obj;
                    if (field != null) {
                        if (field.getId().equalsIgnoreCase("staffNameField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            safetyStaff.setName(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("staffPositionField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            safetyStaff.setPosition(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("staffTrainingField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            safetyStaff.setTypeOftraining(field.getText().trim());
                        } else if (field.getId().equalsIgnoreCase("staffTelephoneField" + key) && (field.getText() != null && !field.getText().isEmpty())) {
                            safetyStaff.setTelephoneNo(field.getText().trim());
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
        crit.add(Restrictions.eq(ParticularPremises._telephone, telephone));
        return (ParticularPremises)crit.uniqueResult();
    }
    public static SafetyCertificate getSafetyCertificate(Incharge incharge){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(SafetyCertificate.class);
        crit.add(Restrictions.eq(SafetyCertificate._incharge, incharge));
        return (SafetyCertificate)crit.uniqueResult();
    }
    public static SafetyCertificate getCertificate(String cert, String hseNo){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(SafetyCertificate.class);
        if(hseNo != null)
            crit.add(Restrictions.eq(SafetyCertificate._houseNo, hseNo));
        if(cert != null)
            crit.add(Restrictions.eq(SafetyCertificate._certificateNo, cert));
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
    public static CollectionDate getCollectionDate(ParticularPremises particularPremises) {
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(CollectionDate.class);
        crit.add(Restrictions.eq(CollectionDate._particularPremises, particularPremises));
        return (CollectionDate)crit.uniqueResult();
    }
    public static List<ParticularOwners> getParticularOwners(ParticularPremises particularPremises) {
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(ParticularOwners.class);
        crit.add(Restrictions.eq(ParticularOwners._particularPremises, particularPremises));
        return crit.list();
    }
    public static List<ParticularOccupyers> getParticularOccupyers(ParticularPremises particularPremises) {
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(ParticularOccupyers.class);
        crit.add(Restrictions.eq(ParticularOccupyers._particularPremises, particularPremises));
        return crit.list();
    }
    public static List<TrainedFireSafetyStaff> getTrainedFireSafetyStaff(ParticularPremises particularPremises) {
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(TrainedFireSafetyStaff.class);
        crit.add(Restrictions.eq(TrainedFireSafetyStaff._particularPremises, particularPremises));
        return crit.list();
    }
}
