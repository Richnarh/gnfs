/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.entities.Incharge;
import com.gnfs.model.InchargeDto;
import com.gnfs.util.DefaultManager;
import com.gnfs.util.Msg;
import com.gnfs.util.Popup;
import java.net.URL;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class InchargeController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(InchargeController.class);
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField textFieldOfficer;
    @FXML
    private TextField textFieldSignature;
    @FXML
    private TableView<InchargeDto> offcerTableView;
    @FXML
    private TableColumn<InchargeDto, String> idColumn;
    @FXML
    private TableColumn<InchargeDto, String> dateColumn;
    @FXML
    private TableColumn<InchargeDto, String> officerColumn;
    @FXML
    private TableColumn<InchargeDto, String> signatureColumn;

    private String inchargeId = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fetchIncharge();
    }    
    
    public void fetchIncharge(){
        log.debug("Initialising table data.......");
        List<Incharge> inchargeList = DefaultManager.findAll(Incharge.class);
        List<InchargeDto> dtoList = new LinkedList<>();
        for (Incharge incharge : inchargeList) {
            InchargeDto dto = InchargeDto.newInstance();
            dto.setId(incharge.getId());
            dto.setOfficerInCharge(incharge.getOfficerInCharge());
            dto.setSignature(incharge.getSignature());
            dtoList.add(dto);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        officerColumn.setCellValueFactory(new PropertyValueFactory<>("officerInCharge"));
        signatureColumn.setCellValueFactory(new PropertyValueFactory<>("signature"));
        
        Collections.sort(dtoList, (a, b) -> {
                return a.getOfficerInCharge().compareTo(b.getOfficerInCharge());
            });
//        dtoList.sort((a, b) -> {
//            return a.getDateOfCollection().getTime() > b.getDateOfCollection().getTime() ? -1 : 1;
//        });
        ObservableList<InchargeDto> inchargeObservableList = FXCollections.observableList(dtoList);
        
        offcerTableView.setRowFactory(row ->{
            TableRow<InchargeDto> tableRow = new TableRow<>();
            ContextMenu contextMenu = new ContextMenu();
            MenuItem editMenu = new MenuItem("Edit");
            MenuItem deleteMenu = new MenuItem("Delete");
            
            contextMenu.getItems().addAll(editMenu,deleteMenu);
            
            tableRow.contextMenuProperty().bind(Bindings.when(tableRow.emptyProperty())
              .then((ContextMenu) null)
              .otherwise(contextMenu));
            
            editMenu.setOnAction(e ->{
                InchargeDto selected = offcerTableView.getSelectionModel().getSelectedItem();
                editIncharge(selected);
            });
            
            deleteMenu.setOnAction(e -> {
                InchargeDto selected = offcerTableView.getSelectionModel().getSelectedItem();
                Alert alert = Popup.confirm("Do you want to delete " + selected.getOfficerInCharge(), "Delete");
                if (alert.getResult() == ButtonType.YES) {
                    deleteIncharge(selected);
                }
            });

            return tableRow;
        });
        
        offcerTableView.setItems(inchargeObservableList);
        FilteredList<InchargeDto> inchargeFilter = new FilteredList<>(inchargeObservableList, b -> true);
        
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData(inchargeFilter,newValue);
        });
        
        SortedList<InchargeDto> sortedData = new SortedList<>(inchargeFilter);
        sortedData.comparatorProperty().bind(offcerTableView.comparatorProperty());
        offcerTableView.setItems(sortedData);
    }
    
    public void editIncharge(InchargeDto dto){  
        inchargeId = dto.getId();
        textFieldOfficer.setText(dto.getOfficerInCharge());
        textFieldSignature.setText(dto.getSignature());
    }
    
    private void deleteIncharge(InchargeDto selected) {
        Incharge in = DefaultManager.findById(Incharge.class, selected.getId());
        if(DefaultManager.delete(in)){
            Popup.info(Msg.DELETE_MESSAGE);
            fetchIncharge();
        }
    }
    
    @FXML
    public void saveOfficerAction(ActionEvent event) {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        Incharge incharge = inchargeData();
        if(incharge.getId() != null){
            incharge = DefaultManager.findById(Incharge.class, incharge.getId());
        }
        if(DefaultManager.save(incharge) != null){
            Popup.info(owner, incharge.getId() != null ? Msg.UPDATED : Msg.CREATED);
            fetchIncharge();
        }else
            Popup.error(owner, "Could not update record");
    }

    @FXML
    public void clearFormAction(ActionEvent event) {
        textFieldOfficer.clear();
        textFieldSignature.clear();
        inchargeId = null;
    }
    
    public Incharge inchargeData(){
        Incharge charge = new Incharge();
        charge.setId(inchargeId);
        charge.setOfficerInCharge(textFieldOfficer.getText());
        charge.setSignature(textFieldSignature.getText());
        return charge;
    }

    private void filterData(FilteredList<InchargeDto> inchargeFilter, String newValue) {
        inchargeFilter.setPredicate((InchargeDto charge) -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String searchKeyword = newValue.toLowerCase();
            if (charge.getOfficerInCharge().toLowerCase().contains(searchKeyword)) {
                return true;
            } else {
                return charge.getSignature().toLowerCase().contains(searchKeyword);
            }
        });
    }
}
