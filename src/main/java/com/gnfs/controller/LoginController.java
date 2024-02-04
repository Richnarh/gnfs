/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.entities.UserData;
import com.gnfs.services.AuthService;
import com.gnfs.util.Popup;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Pascal
 */
public class LoginController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(LoginController.class);
    
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private TextField textFieldUsername;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        textFieldUsername.requestFocus();
    }

    @FXML
    public void doLoginAction(ActionEvent event){
        Window owner = ((Node) event.getSource()).getScene().getWindow();

        log.debug("username: {}",textFieldUsername.getText());
        log.debug("password: {}",textFieldPassword.getText());

        if (textFieldUsername.getText().isEmpty()) {
            Popup.error(owner, "Form Error", "Please enter your login number");
            return;
        }
        if (textFieldPassword.getText().isEmpty()) {
            Popup.error(owner, "Form Error", "Please enter your login password");
            return;
        }
        String emailId = textFieldUsername.getText();
        String password = textFieldPassword.getText();

        UserData data = AuthService.doLogin(emailId, password);
        if (data == null){
            Popup.error(owner,"Please enter correct Email and Password");
        } else {
            Stage stage = (Stage)owner;
            stage.close();
            
            mainPage(owner);
        }
    }

    private void mainPage(Window window){
        Stage stage  = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
        } catch (IOException ex) {
            ex.getMessage();
        }
        stage.initOwner(window);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("GNFS - FIRE PRECAUTION DATA COLLECTION FORM");
 
        stage.setScene(new Scene(root));
        stage.setHeight(680);
        stage.setResizable(false);
        stage.show();
        stage.setX(stage.getX() + stage.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(stage.getY() + stage.getHeight() / 2 - stage.getHeight() / 2);
        
    }
}
