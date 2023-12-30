/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.services;

import com.gnfs.entities.Settings;
import com.gnfs.util.DefaultManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.gnfs.util.DbUtil;

/**
 *
 * @author Richard Narh
 */
public class SmsService {
    public static boolean save(String strId){
        Settings settings = new Settings();
        settings.setSenderId(strId);
        settings.setExpiryLimit(strId);
        Settings s = DefaultManager.save(settings);
        return s != null;
    }

    public static String getSenderId() throws SQLException {
        String query = "SELECT * FROM settings LIMIT 1";
        try {
            ResultSet rs = DbUtil.dbExecuteQuery(query);
            if(!rs.next()) return null;
            return rs.getString("sender_id");
        } catch (SQLException e) {
            throw e;
        }
    }
}
