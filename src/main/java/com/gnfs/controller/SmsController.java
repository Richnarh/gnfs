/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import Zenoph.SMSLib.Enums.MSGTYPE;
import Zenoph.SMSLib.Enums.REQSTATUS;
import Zenoph.SMSLib.ZenophSMS;
import com.gnfs.entities.Settings;
import com.gnfs.model.Sms;
import com.gnfs.services.SmsService;
import com.gnfs.util.Popup;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class SmsController implements Initializable {
    @FXML
    private Label msgReceipient;
    
    @FXML
    private Button sendMessageAction;

    @FXML
    private TextArea textAreaFieldMessage;
    
    private String telephone = null;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        boolean nil = Sms.newInstance().getReceipient() != null;
        if(!nil){
            msgReceipient.setStyle("-fx-text-fill: #D10000; -fx-font-weight: bold;");
            msgReceipient.setText("No Receipient/Premises Selected");
            sendMessageAction.setDisable(true);
        }else{
            msgReceipient.setText(Sms.newInstance().getReceipient());
            telephone = Sms.newInstance().getTelephone();
        }
    }

    @FXML
    public void sendMessageAction(ActionEvent event) {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        System.out.println("Sending sms........");
        String textMessage = textAreaFieldMessage.getText();
        if (textMessage == null || textMessage.isEmpty()) {
            Popup.error(owner, "Please type a message");
            return;
        }
        Settings s = SmsService.findAll();
        
        if(s.getSenderId() == null){
            Popup.error(owner, "Sender ID not found!");
            return;
        }
        System.out.println("SenderID: "+s.getSenderId());
        System.out.println("Telephone: "+telephone);
        if(telephone == null){
            Popup.error(owner, "Premises telephone empty. SMS uses this number");
            return;
        }
        try { 
            ZenophSMS zsms = new ZenophSMS();
            zsms.setUser("pascal");
            zsms.setPassword("pascal001");
            zsms.authenticate();
            zsms.setMessageType(MSGTYPE.TEXT);
            zsms.setMessage(textMessage);
            String phoneNumber = telephone;
            List<String> numbers = zsms.extractPhoneNumbers(phoneNumber);
            for (String number : numbers) {
                zsms.addRecipient(number);
            }
            zsms.setSenderId(s.getSenderId());
            Popup.info(owner, "SMS sent successfully to: ");
            List<String[]> response = zsms.submit();
            for (String[] destination : response) {
                REQSTATUS reqstatus = REQSTATUS.fromInt(Integer.parseInt(destination[0]));
                if (reqstatus == null) {
                    Popup.error("failed to send message");
                    break;
                } else {
                    switch (reqstatus) {
                        case SUCCESS:
                            System.out.println(" <<--- SMS Delivered To: -->>>");
                            Popup.info(owner, "SMS sent to");
                            break;
                        case ERR_INSUFF_CREDIT:
                            Popup.error(owner, "Insufficeint Credit");
                        default:
                            Popup.error(owner, "Failed to send message");
                            return;
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
