/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import com.gnfs.entities.BaseModel;
import java.time.Instant;
import java.util.Date;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Richard Narh
 */
public class DefaultManager {
      
    public static boolean delete(BaseModel model) {
        Session sess = HibernateUtil.open();
        if (model == null) {
            return false;
        }
        try {
            sess.delete(model);
            sess.getTransaction().commit();
            System.out.println("Deleted....");
            return true;
        } catch (Exception e) {
            e.getMessage();
        }finally{
            if(sess != null){
                sess.close();
            }
        }
        return false;
    }
    
//    public static <T> T findById(Class<T> t, Object id) {
//        Session sess = HibernateUtil.open();
//        if (id == null) {
//            return null;
//        }
//        try {
//            return (T) sess.load(t, (Serializable) id);
//        } catch (Exception e) {
//            e.getMessage();
//            System.out.println("Error finding " + t.getName() + " with ID " + id);
//        }
//        return null;
//    }
    
    public static <T> T findAll(Class<?> clazz){
        Session s = HibernateUtil.open();
        Query query = s.createQuery("FROM "+clazz.getSimpleName());
        return (T) query.list();
    }
        
    public static <T> T findById(Class<?> clazz, Object id){
        Session s = HibernateUtil.open();
        Criteria crit = s.createCriteria(clazz);
        crit.add(Restrictions.eq("id", id));
        return (T) crit.uniqueResult();
    }

    public static <T> T save(BaseModel model) {
        Session sess = HibernateUtil.open();
        try {
            if (model.getCreatedDate() == null) {
                model.setCreatedDate(Date.from(Instant.now()));
            }
            if (model.getId() == null) {
                model.setId(JUtils.genId());
                System.out.println("saving.......");
                sess.save(model);
                sess.getTransaction().commit();
            } else {
                System.out.println("Updating.......");
                sess.update(model);
                sess.getTransaction().commit();
            }
            return (T) model;
        } catch (HibernateException e) {
            System.out.println("Error.....");
            e.getMessage();
        }finally{
            if(sess != null){
                sess.close();
            }
        }
        return null;
    }
}
