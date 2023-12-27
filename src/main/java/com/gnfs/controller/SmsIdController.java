/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.services.SmsService;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import util.Popup;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class SmsIdController implements Initializable {
    @FXML
    private TextField textFieldSenderId;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            textFieldSenderId.setText(SmsService.getSenderId());
        } catch (SQLException ex) {
            ex.getMessage();
            Popup.error(ex.getMessage());
        }
    }    
    
    @FXML
    public void saveIdAction(ActionEvent event){
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        String senderId = textFieldSenderId.getText();
        if(senderId == null || senderId.isEmpty()){
            Popup.error(owner, "Sender ID is required.");
            return;
        }
        try {
           boolean saved =  SmsService.save(senderId);
           if(saved)
               Popup.info(owner, "sender Id saved successfully.");
           else
               Popup.error(owner, "Could not save sender ID");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
