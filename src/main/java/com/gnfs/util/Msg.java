/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.util;


/**
 *
 * @author khoders
 */
public class Msg 
{

    public Msg(){}
    
    public static final String SUCCESS_MESSAGE = "Action applied successfully!";
    public static final String FAILED_MESSAGE = "Oops! could not execute action!!";
    public static final String DELETE_MESSAGE = "Record deleted successfuly!";
    public static final String REQUIRED_FIELD_MESSAGE = "All field required";
    public static final String ADD_TO_LIST_MESSAGE = "Item added!";
    
    // API
    public static final String CREATED = "Record Created Successfully!";
    public static final String DELETED = "Record Deleted Successfully!";
    public static final String UPDATED = "Record Updated Successfully!";
    public static final String UNKNOWN_ERROR = "Unknown Error";
    public static final String RECORD_NOT_FOUND = "No Record Found";
    public static final String RECORD_FOUND = "Record Found";
    public static final String OOPS = "Oops! something went wrong, please try again later!";
    public static final String FAILED = "Action Failed.";
    
    
    public static String successResponse() {
        return SUCCESS_MESSAGE;
    }
    
    public static String failedResponse() {
        return FAILED_MESSAGE;
    }
    
    public static String deleteResponse() {
        return DELETE_MESSAGE;
    }
    
    public static final String setMsg(String msg) {
        return msg;
    }
}
