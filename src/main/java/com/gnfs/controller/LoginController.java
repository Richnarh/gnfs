/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.entities.UserData;
import com.gnfs.services.AuthService;
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
import util.Popup;

/**
 * FXML Controller class
 *
 * @author Pascal
 */
public class LoginController implements Initializable {
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

        System.out.println(textFieldUsername.getText());
        System.out.println(textFieldPassword.getText());

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
        Scene scene = new Scene(root);
        stage.initOwner(window);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle("GNFS - FIRE PRECAUTION DATA COLLECTION FORM");
//        stage.sizeToScene();

        
        stage.setScene(scene);
        stage.setHeight(680);
        stage.setResizable(false);
        stage.show();
        stage.setX(stage.getX() + stage.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(stage.getY() + stage.getHeight() / 2 - stage.getHeight() / 2);
    }
}
