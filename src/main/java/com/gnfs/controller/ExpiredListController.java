/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Modality;
import util.FxPageLoader;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class ExpiredListController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void initiateSMSAction(ActionEvent event){
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Sms", "GNFS - SMS", Modality.APPLICATION_MODAL, false);
    }
}
