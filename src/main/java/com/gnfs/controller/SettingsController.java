/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.entities.Settings;
import com.gnfs.services.SmsService;
import com.gnfs.util.DefaultManager;
import com.gnfs.util.Popup;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class SettingsController implements Initializable {
    @FXML
    private TextField textFieldSenderId;
    @FXML
    private TextField expiredLimitTextField;
    
    private String settingsId = null;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldSenderId.setText(SmsService.getSenderId());
    }    
    
    @FXML
    public void saveSettingsAction(ActionEvent event){
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        try {
           Settings settings = settings();
            if(settings.getSenderId() == null){
                Popup.error(owner, "Sender ID is required.");
                return;
            }
           if(settings.getSenderId() == null && settings.getExpiryLimit() == null) return;
           settings =  DefaultManager.save(settings);
           if(settings != null)
               Popup.info(owner, "Settings saved successfully.");
           else
               Popup.error(owner, "Could not save settings");
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public Settings settings(){
        Settings settings = new Settings();
        settings.setId(settingsId);
        settings.setSenderId(textFieldSenderId.getText());
        settings.setExpiryLimit(expiredLimitTextField.getText());
        return settings;
    }
}
