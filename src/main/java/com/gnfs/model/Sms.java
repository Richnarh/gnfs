/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.model;

/**
 *
 * @author Richard Narh
 */
public final class Sms {
    private String receipient;
     private final static Sms INSTANCE = new Sms();

    public Sms() {
    }

    public Sms(String receipient) {
        this.receipient = receipient;
    }
    
    public Sms addReceipient(String receipient){
        this.receipient = receipient;
        return this;
    }
    
    public static Sms newInstance(){
        return INSTANCE;
    }

    public String getReceipient() {
        return receipient;
    }

    public void setReceipient(String receipient) {
        this.receipient = receipient;
    }
    
}
