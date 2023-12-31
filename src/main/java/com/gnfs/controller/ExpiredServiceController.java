/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gnfs.controller;

import com.gnfs.entities.FireFightingEquipment;
import com.gnfs.entities.Incharge;
import com.gnfs.entities.ParticularPremises;
import com.gnfs.entities.SafetyCertificate;
import com.gnfs.model.ExpiredParams;
import com.gnfs.model.FireFightingEquipmentDto;
import com.gnfs.model.Sms;
import com.gnfs.services.GnfsManager;
import com.gnfs.util.DefaultManager;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.stage.Modality;
import com.gnfs.util.FxPageLoader;
import com.gnfs.util.JUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Richard Narh
 */
public class ExpiredServiceController implements Initializable {

    @FXML
    private Label certificateNoLabel;
    @FXML
    private TableView<FireFightingEquipmentDto> expiredListTableView;
    @FXML
    private TableColumn<FireFightingEquipmentDto, String> idColumn;
    @FXML
    private TableColumn<FireFightingEquipmentDto, String> dcpColumn;
    @FXML
    private TableColumn<FireFightingEquipmentDto, String> emergencyLightColumn;
    @FXML
    private TableColumn<FireFightingEquipmentDto, String> smokeDetectorColumn;
    @FXML
    private TableColumn<FireFightingEquipmentDto, String> heatDetectorColumn;
    @FXML
    private TableColumn<FireFightingEquipmentDto, String> fireAlarmColumn;
    @FXML
    private Label houseNoLabel;
    @FXML
    private Button initiateSMSAction;
    @FXML
    private Label officerInChargeLabel;
    @FXML
    private Label premisesLabel;
    @FXML
    private TextField searchTextField;
    @FXML
    private ComboBox<ExpiredParams> filterCmb;
    
    private String receipient = null;
    
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
                            if(item.getTelephone() == null)
                                setText(item.getPremisesName());
                            else
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
        
        ObservableList expiredServiceList = FXCollections.observableArrayList();

        expiredItemList.forEach(item -> {
            expiredServiceList.add(new ExpiredParams(item.getId(), item.getName(), item.getTelephone()));
        });
        filterCmb.setItems(expiredServiceList);
        filterCmb.getItems().add(0, new ExpiredParams(JUtils.getCode(4), "All", null));
    }
    
    public void fetchExpiredRecords(){
        System.out.println("Initialising table data.......");
    }

    @FXML
    public void initiateSMSAction(ActionEvent event) {
        Sms.newInstance().addReceipient(receipient);
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Sms", "GNFS - SMS", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void filterCmbAction(ActionEvent event){
        initLabels();
        List<FireFightingEquipmentDto> dtoList = new LinkedList<>();
        List<FireFightingEquipment> fireFightingList = new LinkedList<>();
        Incharge incharge = null;        
        String filterId = filterCmb.getSelectionModel().getSelectedItem().getId();
        if (filterId != null && filterId.length() == 4) {
            receipient = "ALL";
            fireFightingList = DefaultManager.findAll(FireFightingEquipment.class);
        } else {
            ParticularPremises pp = DefaultManager.findById(ParticularPremises.class, filterId);
            if (pp != null && pp.getIncharge() != null) {
                incharge = GnfsManager.getIncharge(pp.getIncharge().getId());
                receipient = pp.getName() + " - " + pp.getTelephone();
                premisesLabel.setText(pp.getName() + " - " + pp.getTelephone());
            }
            if (incharge != null) {
                SafetyCertificate sc = GnfsManager.getSafetyCertificate(incharge);
                certificateNoLabel.setText(sc != null && sc.getCertificateNo() != null && !sc.getCertificateNo().isEmpty() ? sc.getCertificateNo() : "N/A");
                houseNoLabel.setText(sc != null && sc.getHouseNo() != null && !sc.getHouseNo().isEmpty() ? sc.getHouseNo() : "N/A");
                officerInChargeLabel.setText(incharge.getOfficerInCharge());
                fireFightingList = GnfsManager.getFireFightingByIncharge(incharge);
            }
        }
        
        for (FireFightingEquipment ffe : fireFightingList) {
            FireFightingEquipmentDto dto = new FireFightingEquipmentDto();
            dto.setId(ffe.getId().substring(0, 4).toUpperCase());
            dto.setDcpServiceDate(ffe.getDcpServiceDate());
            dto.setEmergencyLightServiceDate(ffe.getEmergencyLightServiceDate());
            dto.setSmokeDetectorServiceDate(ffe.getSmokeDetectorServiceDate());
            dto.setHeatDetectorServiceDate(ffe.getHeatDetectorServiceDate());
            dto.setFireAlarmServiceDate(ffe.getFireAlarmServiceDate());
            dtoList.add(dto);
        }
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dcpColumn.setCellValueFactory(date -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(d.format(date.getValue().getDcpServiceDate()));
            return property;
        });
        emergencyLightColumn.setCellValueFactory(date -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(d.format(date.getValue().getEmergencyLightServiceDate()));
            return property;
        });
        smokeDetectorColumn.setCellValueFactory(date -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(d.format(date.getValue().getSmokeDetectorServiceDate()));
            return property;
        });
        heatDetectorColumn.setCellValueFactory(date -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(d.format(date.getValue().getHeatDetectorServiceDate()));
            return property;
        });
        fireAlarmColumn.setCellValueFactory(date -> {
            SimpleStringProperty property = new SimpleStringProperty();
            DateFormat d = new SimpleDateFormat("dd/MM/yyyy");
            property.setValue(d.format(date.getValue().getFireAlarmServiceDate()));
            return property;
        });
        ObservableList expiredObservableServiceList = FXCollections.observableArrayList(dtoList);
        expiredListTableView.setItems(expiredObservableServiceList);
        FilteredList<FireFightingEquipmentDto> expiredServiceFilter = new FilteredList<>(expiredObservableServiceList, b -> true);

        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filterData(expiredServiceFilter, newValue);
        });

        SortedList<FireFightingEquipmentDto> sortedData = new SortedList<>(expiredServiceFilter);
        sortedData.comparatorProperty().bind(expiredListTableView.comparatorProperty());
        expiredListTableView.setItems(sortedData);
    }
    
    private void filterData(FilteredList<FireFightingEquipmentDto> inchargeFilter, String newValue) {
        inchargeFilter.setPredicate((FireFightingEquipmentDto item) -> {
            if (newValue == null || newValue.isEmpty()) {
                return true;
            }
            String searchKeyword = newValue.toLowerCase();
            if (String.valueOf(item.getId()).toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getDcpServiceDate().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getEmergencyLightServiceDate().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getSmokeDetectorServiceDate().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            }else if (item.getHeatDetectorServiceDate().toString().toLowerCase().contains(searchKeyword)) {
                return true;
            }else {
                return item.getFireAlarmServiceDate().toString().toLowerCase().contains(searchKeyword);
            }
        });
    }
    public void initLabels(){
        premisesLabel.setText("N/A");
        officerInChargeLabel.setText("N/A");
        certificateNoLabel.setText("N/A");
        houseNoLabel.setText("N/A");
    }
}
