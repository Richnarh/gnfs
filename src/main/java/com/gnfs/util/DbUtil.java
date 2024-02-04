/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Pascal
 */
public class DbUtil {

    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/gnfs?useSSL=false";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "1234";
    public static final String SHOW_SQL = "true";
    private static Connection conn = null;

    private static Connection dbConnect() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.getMessage();
            throw e;
        }
        
        try {
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            System.out.println("Connection SUCCESSFUL!");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console: " + e);
            e.getMessage();
            throw e;
        }
        return conn;
    }
    
    public static Connection getConn(){
        try {
            if(conn == null){
                conn =  dbConnect();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.getMessage();
        }
        return conn;
    }

    public static void dbDisconnect() throws SQLException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException {
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            if(conn == null) conn = getConn();
            System.out.println("Select statement: " + queryStmt + "\n");
            stmt = conn.createStatement();
            resultSet = stmt.executeQuery(queryStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        }
       return resultSet;
    }

    public static void executeQuery(String sqlStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        try {
            if(conn == null) conn = getConn();
            stmt = conn.createStatement();
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        }
    }
    
    public static PreparedStatement preparedStatement(String sqlQuery) throws SQLException{
        PreparedStatement pst = null;
        try {
            if(conn == null) conn = getConn();
            pst = conn.prepareStatement(sqlQuery);
        } catch (SQLException e) {
            e.getMessage();
            throw e;
        }
        return pst;
    }
    
    public static ResultSet getResultSet(String sqlQuery) throws SQLException{
        ResultSet rs = null;
        PreparedStatement pst = null;
        try {
            if(conn == null) conn = getConn();
            pst = conn.prepareStatement(sqlQuery);
            rs = pst.executeQuery(sqlQuery);
        } catch (SQLException e) {
            e.getMessage();
            throw e;
        }
        return rs;
    }
}
