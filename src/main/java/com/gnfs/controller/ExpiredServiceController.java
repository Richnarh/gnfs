/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.entities.ParticularPremises;
import com.gnfs.model.ExpiredItem;
import com.gnfs.model.ExpiredParams;
import com.gnfs.util.DefaultManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Modality;
import com.gnfs.util.FxPageLoader;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import javafx.util.StringConverter;

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
    @FXML
    private ComboBox<ExpiredParams> filterCmb;
    private ObservableList fxList = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        filterExpiredService();
    }
    
    public void filterExpiredService() {
        List<ParticularPremises> expiredItemList = DefaultManager.findAll(ParticularPremises.class);
        Callback<ListView<ExpiredParams>, ListCell<ExpiredParams>> cellFactory = new Callback<ListView<ExpiredParams>, ListCell<ExpiredParams>>() {
            @Override
            public ListCell<ExpiredParams> call(ListView<ExpiredParams> l) {
                return new ListCell<ExpiredParams>() {
                    @Override
                    protected void updateItem(ExpiredParams item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            setText(item.getPremisesName() +" - "+item.getTelephone());
                        }
                    }
                };
            }
        };
        
        filterCmb.setConverter(new StringConverter<ExpiredParams>() {
            @Override
            public String toString(ExpiredParams dto) {
                if (dto == null) {
                    return null;
                } else {
                    return dto.getId();
                }
            }

            @Override
            public ExpiredParams fromString(String incharge) {
                return ExpiredParams.newInstance().premisesName(incharge);
            }
        });
        filterCmb.setCellFactory(cellFactory);
        filterCmb.setButtonCell(cellFactory.call(null));

        expiredItemList.forEach(item -> {
            fxList.add(new ExpiredParams(item.getId(), item.getName(), item.getTelephone()));
        });
        filterCmb.setItems(fxList);
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
