/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import com.gnfs.entities.BaseModel;
import com.gnfs.model.BaseKey;
import static com.gnfs.util.FxUtil.genId;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 *
 * @author Richard Narh
 */
public class DefaultManager {
      
    public static boolean delete(BaseKey model) {
        Session sess = HibernateUtil.getSession();
        if (model == null) {
            return false;
        }
        try {
            sess.delete(model);
            return true;
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }
    
    public static <T> T find(Class<T> t, Object id) {
        Session sess = HibernateUtil.getSession();
        if (id == null) {
            return null;
        }
        try {
            return (T) sess.load(t, (Serializable) id);
        } catch (Exception e) {
            e.getMessage();
            System.out.println("Error finding " + t.getName() + " with ID " + id);
        }
        return null;
    }
    
    public static <T> T findAll(Class<T> t, Number id) {
        Session sess = HibernateUtil.getSession();
        return (T) sess.load(t, id);
    }

    public static <T> T save(Object obj) {
        BaseModel model = (BaseModel)obj;
        Session sess = HibernateUtil.open();
        try {
            if (model.getCreatedDate() == null) {
                model.setCreatedDate(Date.from(Instant.now()));
            }
            if (model.getId() == null) {
                model.setId(genId());
                System.out.println("saving.......");
                sess.save(obj);
                sess.getTransaction().commit();
            } else if (find(model.getClass(), model.getId()) != null) {
                System.out.println("Updaating.......");
                sess.update(model);
            } else {
                sess.persist(model);
            }
            return (T) model;
        } catch (HibernateException e) {
            e.getMessage();
        }finally{
            if(sess != null){
                sess.close();
            }
        }
        return null;
    }
}
