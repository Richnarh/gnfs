/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Zenoph.SMSLib.Enums.MSGTYPE;
import Zenoph.SMSLib.Enums.REQSTATUS;
import Zenoph.SMSLib.ZenophSMS;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Window;
import util.Popup;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class SmsController implements Initializable {

    @FXML
    private Button sendMessageAction;

    @FXML
    private TextArea textAreaFieldMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        try {
            ZenophSMS zsms = new ZenophSMS();
            zsms.setUser("pascal");
            zsms.setPassword("pascal001");
            zsms.authenticate();
            zsms.setMessageType(MSGTYPE.TEXT);
            zsms.setMessage(textMessage);
            String phoneNumber = "0574417585";
            List<String> numbers = zsms.extractPhoneNumbers(phoneNumber);
            for (String number : numbers) {
                zsms.addRecipient(number);
            }
            zsms.setSenderId("GNFS");
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
