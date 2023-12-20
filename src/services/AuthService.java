/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import util.DbUtil;

/**
 *
 * @author Pascal
 */
public class AuthService {

    public static boolean login(String username, String password) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM user_account WHERE username = '" + username + "' AND password = '" + password + "'";
        ResultSet rs = DbUtil.dbExecuteQuery(sql);
        return rs.next();
    }
}
