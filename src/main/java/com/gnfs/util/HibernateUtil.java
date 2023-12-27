/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import com.gnfs.entities.FireFightingEquipment;
import com.gnfs.entities.Incharge;
import com.gnfs.entities.ParticularOccupyers;
import com.gnfs.entities.ParticularOwners;
import com.gnfs.entities.ParticularPremises;
import com.gnfs.entities.SafetyCertificate;
import com.gnfs.entities.Sender;
import com.gnfs.entities.SpecialInstallation;
import com.gnfs.entities.TrainedFireSafetyStaff;
import com.gnfs.entities.UserData;
import java.util.Properties;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author Richard Narh
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory init() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, DbUtil.JDBC_DRIVER);
                settings.put(Environment.URL, DbUtil.DB_URL);
                settings.put(Environment.USER, DbUtil.DB_USER);
                settings.put(Environment.PASS, DbUtil.DB_PASSWORD);
                settings.put(Environment.SHOW_SQL, DbUtil.SHOW_SQL);
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5InnoDBDialect");
                settings.put(Environment.AUTOCOMMIT, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "update");

                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Sender.class);
                configuration.addAnnotatedClass(FireFightingEquipment.class);
                configuration.addAnnotatedClass(Incharge.class);
                configuration.addAnnotatedClass(ParticularOccupyers.class);
                configuration.addAnnotatedClass(ParticularOwners.class);
                configuration.addAnnotatedClass(ParticularPremises.class);
                configuration.addAnnotatedClass(SafetyCertificate.class);
                configuration.addAnnotatedClass(SpecialInstallation.class);
                configuration.addAnnotatedClass(TrainedFireSafetyStaff.class);
                configuration.addAnnotatedClass(UserData.class);
                
                ServiceRegistry serviceRegistry = (ServiceRegistry) new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                e.getMessage();
            }
        }
        return sessionFactory;
    }

    public static Session getSession() {
        Session s = getSessionFactory().getCurrentSession();
        try {
            s.beginTransaction();
        } catch (Exception e) {
            e.getMessage();
            s.close();
            s = getSessionFactory().getCurrentSession();
        }
        return s;
    }
    
    public static Session open(){
        Session s = getSessionFactory().openSession();
        try {
            s.beginTransaction();
        } catch (Exception e) {
            e.getMessage();
            s.close();
            s = getSessionFactory().openSession();
        }
        return s;
    }
    
    public static Transaction getTransaction(){
         Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.getMessage();
        }
        return transaction;
    }
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            init();
        }
        return sessionFactory;
    }
}
