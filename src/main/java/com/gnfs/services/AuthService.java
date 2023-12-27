/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.services;

import com.gnfs.entities.UserData;
import com.gnfs.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Pascal
 */
public class AuthService {
    
    public static UserData doLogin(String username, String password){
        Session s = HibernateUtil.open();
        Criteria dc = s.createCriteria(UserData.class);
        dc.add(Restrictions.eq("username", username));
        dc.add(Restrictions.eq("password", password));
        UserData data = (UserData)dc.uniqueResult();
        return data;
    }
}
