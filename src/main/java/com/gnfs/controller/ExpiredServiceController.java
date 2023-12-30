/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.model.ExpiredItem;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Modality;
import com.gnfs.util.FxPageLoader;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class ExpiredServiceController implements Initializable {
    @FXML
    private TableColumn<?, ?> certNoColumn;

    @FXML
    private TableView<?> expiredListTableView;

    @FXML
    private Button initiateSMSAction;

    @FXML
    private TableColumn<?, ?> officerInchargeColumn;

    @FXML
    private TableColumn<?, ?> premNameColumn;

    @FXML
    private TextField searchTextField;

    @FXML
    private TableColumn<?, ?> serviceDateColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void fetchExpiredRecords(){
        System.out.println("Initialising table data.......");
    }

    @FXML
    public void initiateSMSAction(ActionEvent event) {
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Sms", "GNFS - SMS", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void filterCmbAction(ActionEvent event){
        System.out.println("Hello............");
    }
    
    private void filterData(FilteredList<ExpiredItem> inchargeFilter, String newValue) {
        inchargeFilter.setPredicate((ExpiredItem item) -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String searchKeyword = newValue.toLowerCase();
            if (String.valueOf(item.getId()).toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getInchargeName().toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getCertificateNo().toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getPremisesName().toLowerCase().contains(searchKeyword)) {
                return true;
            }else {
                return item.getServiceDate().toString().toLowerCase().contains(searchKeyword);
            }
        });
    }
}
