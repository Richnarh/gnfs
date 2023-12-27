/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.services;

import com.gnfs.entities.Incharge;
import com.gnfs.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Richard Narh
 */
public class GnfsService {
    public static Incharge findIncharge(String officerName, String signature){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(Incharge.class);
        crit.add(Restrictions.eq(Incharge._officerInCharge, officerName));
        crit.add(Restrictions.eq(Incharge._signature, signature));
        return (Incharge)crit.uniqueResult();
    }
}
