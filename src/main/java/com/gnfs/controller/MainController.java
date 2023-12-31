package com.gnfs.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.gnfs.entities.CollectionDate;
import com.gnfs.entities.FireFightingEquipment;
import com.gnfs.entities.Incharge;
import com.gnfs.entities.ParticularOccupyers;
import com.gnfs.entities.ParticularOwners;
import com.gnfs.entities.ParticularPremises;
import com.gnfs.entities.SafetyCertificate;
import com.gnfs.entities.SpecialInstallation;
import com.gnfs.entities.TrainedFireSafetyStaff;
import com.gnfs.model.InchargeDto;
import com.gnfs.model.Sms;
import com.gnfs.services.GnfsManager;
import com.gnfs.util.DateUtil;
import com.gnfs.util.DefaultManager;
import java.net.URL;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import com.gnfs.util.FxPageLoader;
import com.gnfs.util.JUtils;
import com.gnfs.util.Popup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author Richard Narh
 */
public class MainController implements Initializable {
    @FXML
    private Button btnSettings;
    @FXML
    private GridPane ownerGridPane;
    @FXML
    private GridPane occupyerGridPane;
    @FXML
    private GridPane staffGridPane;
    @FXML
    private TextField textFieldLandMark;
    @FXML
    private TextField textFieldLocation;
    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldTelephone;
    @FXML
    private TextField textFieldType;
    @FXML
    private TextField gloabelSearchTextField;
    @FXML
    private TextField textFieldHydrant;
    @FXML
    private DatePicker collectionDateField;
    @FXML
    private TextField textFieldSmokeExtractor;
    @FXML
    private TextField textFieldDryRisers;
    @FXML
    private TextField textFieldHeatExtractor;
    @FXML
    private TextField textFieldWetRisers;
    @FXML
    private TextField textFieldHoseReel;
    @FXML
    private TextField textFieldSafetyName;
    @FXML
    private TextField textFieldHseNo;
    @FXML
    private TextField textFieldSafetyLocation;
    @FXML
    private TextField textFieldRiskType;
    @FXML
    private TextField textFieldGPRS;
    @FXML
    private DatePicker issueDateField;
    @FXML
    private DatePicker expiryDateField;
    @FXML
    private TextField textFieldCertNo;
    @FXML
    private TextField dcpQtyTextField;
    @FXML
    private TextField emergencyLightQtyTextField;
    @FXML
    private TextField smokeDetectorQtyTextField;
    @FXML
    private TextField heatDetectorQtyTextField;
    @FXML
    private TextField fireAlarmQtyTextField;
    @FXML
    private TextField generalNoticeQtyTextField;
    @FXML
    private TextField exitSignQtyTextField;
    @FXML
    private TextField assemblyPointQtyTextField;
    @FXML
    private TextField waterSrcTypeTextField;
    @FXML
    private TextField waterSrcQtyTextField;
    @FXML
    private DatePicker dcpInstDateField;
    @FXML
    private DatePicker emergencyLightInstDateField;
    @FXML
    private DatePicker smkDetectorInstDateField;
    @FXML
    private DatePicker heatDetectorInstDateField;
    @FXML
    private DatePicker fireAlarmInstDateField;
    @FXML
    private DatePicker dcpServDatefield;
    @FXML
    private DatePicker emergencyLightServDateField;
    @FXML
    private DatePicker smkDetectorServDateField;
    @FXML
    private DatePicker heatDetectorServDateField;
    @FXML
    private DatePicker fireAlarmServDateField;
    @FXML
    private TextField dcpServByTextField;
    @FXML
    private TextField emergencyLightServByTextField;
    @FXML
    private TextField smkDetectorServByTextField;
    @FXML
    private TextField heatDetectorServByTextField;
    @FXML
    private TextField fireAlarmServByTextField;  
    @FXML
    private ComboBox<InchargeDto> officerCmb;

    private String receipient = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inchargeCmb();
        btnSettings.setGraphic(new ImageView(new Image("/icons/settings.png")));
    }

    public void inchargeCmb() {
        List<Incharge> inchargeList = DefaultManager.findAll(Incharge.class);
        Callback<ListView<InchargeDto>, ListCell<InchargeDto>> cellFactory = new Callback<ListView<InchargeDto>, ListCell<InchargeDto>>() {
            @Override
            public ListCell<InchargeDto> call(ListView<InchargeDto> l) {
                return new ListCell<InchargeDto>() {
                    @Override
                    protected void updateItem(InchargeDto item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                           setText(item.getOfficerInCharge());
                        }
                    }
                };
            }
        };
        officerCmb.setConverter(new StringConverter<InchargeDto>() {
            @Override
            public String toString(InchargeDto dto) {
                if (dto == null) {
                    return null;
                } else {
                    return dto.getId();
                }
            }

            @Override
            public InchargeDto fromString(String incharge) {
                return InchargeDto.newInstance().officerInCharge(incharge);
            }
        });
        
        officerCmb.setCellFactory(cellFactory);
        officerCmb.setButtonCell(cellFactory.call(null));

        ObservableList fxList = FXCollections.observableArrayList();
                
        inchargeList.forEach(item -> {
            fxList.add(new InchargeDto(item.getId(), item.getOfficerInCharge()));
        });
        officerCmb.setItems(fxList);
    }

    TextField[] ownerTextField = new TextField[20];
    Label[] ownerLabel = new Label[20];

    TextField[] occupyTextField = new TextField[20];
    Label[] occupyLabel = new Label[20];

    TextField[] staffTextField = new TextField[20];
    DatePicker[] staffDateField = new DatePicker[20];

    int ownerRowIndex = 1;
    int occupyRowIndex = 1;
    int staffRowIndex = 1;
    int i = 1;
    int j = 1;
    int k = 1;
    
    LinkedHashMap<Integer, LinkedHashSet<TextField>> ownerMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashSet<TextField>> occupyerMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashSet<TextField>> trainedFireSafetyStaffMap = new LinkedHashMap<>();
    
    @FXML
    public void btnAddOwnerRow(ActionEvent event) {
        System.out.println("Adding Owner Row....." + i);
        LinkedHashSet<TextField> fieldList = new LinkedHashSet<>();
        ownerTextField[1] = new TextField();
        ownerTextField[1].setLayoutX(340);
        ownerTextField[1].setId("ownerNameField" + i);
        ownerTextField[1].setPromptText("Name Field - " + i);
        GridPane.setMargin(ownerTextField[1], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[1]);

        ownerTextField[2] = new TextField();
        ownerTextField[2].setLayoutX(340);
        ownerTextField[2].setId("ownerTeleHandyField" + i);
        ownerTextField[2].setPromptText("Tele Handy Field - " + i);
        GridPane.setMargin(ownerTextField[2], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[2]);
        
        ownerTextField[3] = new TextField();
        ownerTextField[3].setLayoutX(340);
        ownerTextField[3].setId("ownerTeleOfficeField" + i);
        ownerTextField[3].setPromptText("Tele Office Field - " + i);
        GridPane.setMargin(ownerTextField[3], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[3]);

        ownerTextField[4] = new TextField();
        ownerTextField[4].setLayoutX(340);
        ownerTextField[4].setId("ownerPurposeField" + i);
        ownerTextField[4].setPromptText("Purpose Field - " + i);
        GridPane.setMargin(ownerTextField[4], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[4]);

        ownerLabel[1] = new Label();
        ownerLabel[1].setId("owner" + String.valueOf(i));
        ownerLabel[1].setText(String.valueOf(i));
        
        if (ownerRowIndex > 6) {
            Popup.error("You cannot add more than 6 rows");
            return;
        }
        
        ownerMap.put(i, fieldList);

        ownerGridPane.add(ownerLabel[1], 0, ownerRowIndex);
        ownerGridPane.add(ownerTextField[1], 1, ownerRowIndex);
        ownerGridPane.add(ownerTextField[2], 2, ownerRowIndex);
        ownerGridPane.add(ownerTextField[3], 3, ownerRowIndex);
        ownerGridPane.add(ownerTextField[4], 4, ownerRowIndex);

        ownerRowIndex++;
        i++;
    }

    @FXML
    public void btnAddOccupyerRow(ActionEvent event) {
        System.out.println("Adding Occupyer Row....." + j);
        LinkedHashSet<TextField> fieldList = new LinkedHashSet<>();
        occupyTextField[1] = new TextField();
        occupyTextField[1].setLayoutX(340);
        occupyTextField[1].setId("occupyerNameField" + j);
        occupyTextField[1].setPromptText("Name Field - " + j);
        GridPane.setMargin(occupyTextField[1], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[1]);

        occupyTextField[2] = new TextField();
        occupyTextField[2].setLayoutX(340);
        occupyTextField[2].setId("occupyerTeleHandyField" + j);
        occupyTextField[2].setPromptText("Tele Handy Field - " + j);
        GridPane.setMargin(occupyTextField[2], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[2]);

        occupyTextField[3] = new TextField();
        occupyTextField[3].setLayoutX(340);
        occupyTextField[3].setId("occupyerTeleOfficeField" + j);
        occupyTextField[3].setPromptText("Tele Office Field - " + j);
        GridPane.setMargin(occupyTextField[3], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[3]);

        occupyTextField[4] = new TextField();
        occupyTextField[4].setLayoutX(340);
        occupyTextField[4].setId("occupyerPurposeField" + j);
        occupyTextField[4].setPromptText("Usage/Activity Field - " + j);
        GridPane.setMargin(occupyTextField[4], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[4]);

        occupyLabel[1] = new Label();
        occupyLabel[1].setId("occupy" + String.valueOf(j));
        occupyLabel[1].setText(String.valueOf(j));
        

        if (occupyRowIndex > 12) {
            Popup.error("You cannot add more than 12 rows");
            return;
        }
        
        occupyerMap.put(j, fieldList);

        occupyerGridPane.add(occupyLabel[1], 0, occupyRowIndex);
        occupyerGridPane.add(occupyTextField[1], 1, occupyRowIndex);
        occupyerGridPane.add(occupyTextField[2], 2, occupyRowIndex);
        occupyerGridPane.add(occupyTextField[3], 3, occupyRowIndex);
        occupyerGridPane.add(occupyTextField[4], 4, occupyRowIndex);
        occupyRowIndex++;
        j++;
    }

    @FXML
    public void btnAddStaffRow(ActionEvent event) {
        System.out.println("Adding Staff Row....." + k);
        LinkedHashSet<TextField> fieldList = new LinkedHashSet<>();
        staffTextField[1] = new TextField();
        staffTextField[1].setLayoutX(340);
        staffTextField[1].setId("staffNameField" + k);
        staffTextField[1].setPromptText("Name Field - " + k);
        GridPane.setMargin(staffTextField[1], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[1]);

        staffTextField[2] = new TextField();
        staffTextField[2].setLayoutX(340);
        staffTextField[2].setId("staffPositionField" + k);
        staffTextField[2].setPromptText("Postion Field - " + k);
        GridPane.setMargin(staffTextField[2], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[2]);

        staffTextField[3] = new TextField();
        staffTextField[3].setLayoutX(340);
        staffTextField[3].setId("staffTrainingField" + k);
        staffTextField[3].setPromptText("Training Field - " + k);
        GridPane.setMargin(staffTextField[3], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[3]);

        staffDateField[4] = new DatePicker();
        staffDateField[4].setLayoutX(340);
        staffDateField[4].setId("staffDateField" + k);
        staffDateField[4].setPromptText("Date Field - " + k);
        GridPane.setMargin(staffDateField[4], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[4]);

        staffTextField[5] = new TextField();
        staffTextField[5].setLayoutX(340);
        staffTextField[5].setId("staffTelephoneField" + k);
        staffTextField[5].setPromptText("Telephone Field - " + k);
        GridPane.setMargin(staffTextField[5], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[5]);

        if (staffRowIndex > 5) {
            Popup.error("You cannot add more than 5 rows");
            return;
        }

        trainedFireSafetyStaffMap.put(k, fieldList);
        
        staffGridPane.add(staffTextField[1], 0, staffRowIndex);
        staffGridPane.add(staffTextField[2], 1, staffRowIndex);
        staffGridPane.add(staffTextField[3], 2, staffRowIndex);
        staffGridPane.add(staffDateField[4], 3, staffRowIndex);
        staffGridPane.add(staffTextField[5], 4, staffRowIndex);
        staffRowIndex++;
        k++;
    }

    @FXML
    public void initiateSMSAction(ActionEvent event) {
        Sms.newInstance().addReceipient(receipient);
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Sms", "GNFS - SMS", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void expiredListAction(ActionEvent event) {
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/ExpiredService", "GNFS - Fire fighting Services Expired Record", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void smsIdAction(ActionEvent event) {
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Settings", "GNFS - Settings", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void addOfficerAction(ActionEvent event) {
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Incharge", "GNFS - Officer in Charge", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void resetPageAction(ActionEvent event) {
        Popup.confirm("Do you want to reset the page?", "Resetting", "Reset");
    }

    @FXML
    public void saveAllAction(ActionEvent event) {
        Window window = ((Node) event.getSource()).getScene().getWindow();
        List<ParticularOwners> ownerList = new LinkedList<>();
        List<ParticularOccupyers> occupyerList = new LinkedList<>();
        List<TrainedFireSafetyStaff> trainedFireSafetyStaffList = new LinkedList<>();
        
        boolean off = officerCmb.getValue() == null || officerCmb.getValue().getId().isEmpty();
        if(off){
            Popup.error(window, "Please select officer in charge.");
            return;
        }
        String inchargeId = officerCmb.getValue().getId();
        Incharge incharge = DefaultManager.findById(Incharge.class, inchargeId);
        if (incharge != null) {
            boolean nilName = textFieldName.getText() == null || textFieldName.getText().isEmpty();
            boolean nilTel = textFieldTelephone.getText() == null || textFieldTelephone.getText().isEmpty();
            if(nilName){
                Popup.error(window, "Premises Name is Required");
                return;
            }
            if(nilTel){
                Popup.error(window, "Premises Telephone is Required");
                return;
            }
            DefaultManager.save(premisesData(incharge));
            DefaultManager.save(fireSafetyData(incharge));
            DefaultManager.save(fireFightingEquipment(incharge));
            DefaultManager.save(specialInst(incharge));
            DefaultManager.save(collDate(incharge));
            if (!ownerMap.isEmpty()) {
                ownerList = GnfsManager.createOwners(ownerMap);
                for (ParticularOwners owner : ownerList) {
                    owner.setIncharge(incharge);
                    DefaultManager.save(owner);
                }
            }
            if (!occupyerMap.isEmpty()) {
                occupyerList = GnfsManager.createOccupyers(occupyerMap);
                for (ParticularOccupyers occupyer : occupyerList) {
                    occupyer.setIncharge(incharge);
                    DefaultManager.save(occupyer);
                }
            }
            if (!trainedFireSafetyStaffMap.isEmpty()) {
                trainedFireSafetyStaffList = GnfsManager.createTrainedFireSafetyStaff(trainedFireSafetyStaffMap);
                for (TrainedFireSafetyStaff staff : trainedFireSafetyStaffList) {
                    staff.setIncharge(incharge);
                    DefaultManager.save(staff);
                }
            }
            Popup.info("Your data is saved successfully!");
        }else
            Popup.error(window, "Sorry, Something went wrong, could not save data");
    }
    
    @FXML
    public void searchAction(ActionEvent event) {
        Window owner = ((Node) event.getSource()).getScene().getWindow();
        System.out.println("Search: "+gloabelSearchTextField.getText());
        if(gloabelSearchTextField.getText() == null || gloabelSearchTextField.getText().isEmpty()){
            Popup.error(owner, "No search entry entered.");
            return;
        }
        ParticularPremises pp = null;
        Incharge incharge = null;
        String searchKey = gloabelSearchTextField.getText();
        SafetyCertificate sc = GnfsManager.searchCert(searchKey,null);
        if(sc == null){
            sc = GnfsManager.searchCert(null,searchKey);
        }
        if(sc == null){
            pp = GnfsManager.searchPremises(searchKey);
        }
        if(sc == null && pp == null){
            Popup.error(owner,"Your search entry does not match any record");
            return;
        }
        if(sc != null){
            incharge = sc.getIncharge();
            pp = sc.getParticularPremises();
            textFieldSafetyName.setText(sc.getName());
            textFieldHseNo.setText(sc.getHouseNo());
            textFieldSafetyLocation.setText(sc.getLocation());
            textFieldRiskType.setText(sc.getTypeOfRisk());
            issueDateField.setValue(DateUtil.dateToLocalDate(sc.getIssueDate()));
            expiryDateField.setValue(DateUtil.dateToLocalDate(sc.getExpiryDate()));
            textFieldCertNo.setText(sc.getCertificateNo());
        }
        if(pp != null){
            incharge = pp.getIncharge();
            textFieldName.setText(pp.getName());
            textFieldType.setText(pp.getPremType());
            textFieldLocation.setText(pp.getLocation());
            textFieldLandMark.setText(pp.getLandMark());
            textFieldTelephone.setText(pp.getTelephone());
        }
        officerCmb.setValue(new InchargeDto(incharge.getId(), incharge.getOfficerInCharge()));
        if(pp != null){
            FireFightingEquipment ffe = GnfsManager.getFireFightingEquipment(pp);
            dcpQtyTextField.setText(JUtils.toString(ffe.getDcpQty()));
            emergencyLightQtyTextField.setText(JUtils.toString(ffe.getEmergencyLightQty()));
            smokeDetectorQtyTextField.setText(JUtils.toString(ffe.getSmokeDetectorQty()));
            heatDetectorQtyTextField.setText(JUtils.toString(ffe.getHeatDetectorQty()));
            fireAlarmQtyTextField.setText(JUtils.toString(ffe.getFireAlarmQty()));
            generalNoticeQtyTextField.setText(JUtils.toString(ffe.getGeneralNoticeQty()));
            exitSignQtyTextField.setText(JUtils.toString(ffe.getExitSignQty()));
            assemblyPointQtyTextField.setText(JUtils.toString(ffe.getAssemblyPointQty()));
            waterSrcQtyTextField.setText(JUtils.toString(ffe.getWaterSourceQty()));
            waterSrcTypeTextField.setText(ffe.getWaterSource());
            dcpInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getDcpInstDate()));
            emergencyLightInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getEmergencyLightInstDate()));
            smkDetectorInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getSmokeDetectorInstDate()));
            heatDetectorInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getHeatDetectorInstDate()));
            fireAlarmInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getFireAlarmInstDate()));
            dcpServDatefield.setValue(DateUtil.dateToLocalDate(ffe.getDcpServiceDate()));
            emergencyLightServDateField.setValue(DateUtil.dateToLocalDate(ffe.getEmergencyLightServiceDate()));
            smkDetectorServDateField.setValue(DateUtil.dateToLocalDate(ffe.getSmokeDetectorServiceDate()));
            heatDetectorServDateField.setValue(DateUtil.dateToLocalDate(ffe.getHeatDetectorServiceDate()));
            fireAlarmServDateField.setValue(DateUtil.dateToLocalDate(ffe.getFireAlarmServiceDate()));
            dcpServByTextField.setText(ffe.getDcpServiceby());
            emergencyLightServByTextField.setText(ffe.getEmergencyLightServiceby());
            smkDetectorServByTextField.setText(ffe.getSmokeDetectorServiceby());
            heatDetectorServByTextField.setText(ffe.getHeatDetectorServiceby());
            fireAlarmServByTextField.setText(ffe.getFireAlarmServiceby());
            
            SpecialInstallation si = GnfsManager.getSpecialInstallation(pp);
            textFieldHydrant.setText(si.getHydrant());
            textFieldSmokeExtractor.setText(si.getSmokeExtractor());
            textFieldDryRisers.setText(si.getDryRisers());
            textFieldHeatExtractor.setText(si.getHeatExtractor());
            textFieldWetRisers.setText(si.getWetRisers());
            textFieldHoseReel.setText(si.getHoseReel());
        }
    }

    @FXML
    public void closeAction(ActionEvent event) {
        Alert alert = Popup.confirm("Do you want to exit app ?", "Exiting...");
        if(alert.getResult() == ButtonType.YES){
            System.exit(0);
        }
    }
    
    public CollectionDate collDate(Incharge incharge){
        CollectionDate cd = new CollectionDate();
        cd.setIncharge(incharge);
        cd.setDateOfCollection(DateUtil.localDateToDate(collectionDateField.getValue()));
        return cd; 
    }
    
    public ParticularPremises premisesData(Incharge incharge){
        ParticularPremises premises = new ParticularPremises();
        premises.setName(textFieldName.getText());
        premises.setPremType(textFieldType.getText());
        premises.setLocation(textFieldLocation.getText());
        premises.setLandMark(textFieldLandMark.getText());
        premises.setTelephone(textFieldTelephone.getText());
        premises.setIncharge(incharge);
        return premises;
    }
        
    public SpecialInstallation specialInst(Incharge incharge){
        SpecialInstallation si = new SpecialInstallation();
        si.setHydrant(textFieldHydrant.getText());
        si.setSmokeExtractor(textFieldSmokeExtractor.getText());
        si.setDryRisers(textFieldDryRisers.getText());
        si.setHeatExtractor(textFieldHeatExtractor.getText());
        si.setWetRisers(textFieldWetRisers.getText());
        si.setHoseReel(textFieldHoseReel.getText());
        si.setIncharge(incharge);
        return si;
    }
    
    public SafetyCertificate fireSafetyData(Incharge incharge){
        SafetyCertificate sc = new SafetyCertificate();
        sc.setName(textFieldSafetyName.getText());
        sc.setHouseNo(textFieldHseNo.getText());
        sc.setLocation(textFieldSafetyLocation.getText());
        sc.setTypeOfRisk(textFieldRiskType.getText());
        sc.setGprs(textFieldGPRS.getText());
        sc.setIssueDate(DateUtil.localDateToDate(issueDateField.getValue()));
        sc.setExpiryDate(DateUtil.localDateToDate(expiryDateField.getValue()));
        sc.setCertificateNo(textFieldCertNo.getText());
        sc.setIncharge(incharge);
        return sc;
    }
    
    public FireFightingEquipment fireFightingEquipment(Incharge incharge){
        FireFightingEquipment ffe = new FireFightingEquipment();
        ffe.setDcpQty(JUtils.toInteger(dcpQtyTextField.getText()));
        ffe.setEmergencyLightQty(JUtils.toInteger(emergencyLightQtyTextField.getText()));
        ffe.setSmokeDetectorQty(JUtils.toInteger(smokeDetectorQtyTextField.getText()));
        ffe.setHeatDetectorQty(JUtils.toInteger(heatDetectorQtyTextField.getText()));
        ffe.setFireAlarmQty(JUtils.toInteger(fireAlarmQtyTextField.getText()));
        ffe.setGeneralNoticeQty(JUtils.toInteger(generalNoticeQtyTextField.getText()));
        ffe.setExitSignQty(JUtils.toInteger(exitSignQtyTextField.getText()));
        ffe.setAssemblyPointQty(JUtils.toInteger(assemblyPointQtyTextField.getText()));
        ffe.setWaterSourceQty(JUtils.toInteger(waterSrcQtyTextField.getText()));
        ffe.setWaterSource(waterSrcTypeTextField.getText());
        ffe.setDcpInstDate(DateUtil.localDateToDate(dcpInstDateField.getValue()));
        ffe.setEmergencyLightInstDate(DateUtil.localDateToDate(emergencyLightInstDateField.getValue()));
        ffe.setSmokeDetectorInstDate(DateUtil.localDateToDate(smkDetectorInstDateField.getValue()));
        ffe.setHeatDetectorInstDate(DateUtil.localDateToDate(heatDetectorInstDateField.getValue()));
        ffe.setFireAlarmInstDate(DateUtil.localDateToDate(fireAlarmInstDateField.getValue()));
        ffe.setDcpServiceDate(DateUtil.localDateToDate(dcpServDatefield.getValue()));
        ffe.setEmergencyLightServiceDate(DateUtil.localDateToDate(emergencyLightServDateField.getValue()));
        ffe.setSmokeDetectorServiceDate(DateUtil.localDateToDate(smkDetectorServDateField.getValue()));
        ffe.setHeatDetectorServiceDate(DateUtil.localDateToDate(heatDetectorServDateField.getValue()));
        ffe.setFireAlarmServiceDate(DateUtil.localDateToDate(fireAlarmServDateField.getValue()));
        ffe.setDcpServiceby(dcpServByTextField.getText());
        ffe.setEmergencyLightServiceby(emergencyLightServByTextField.getText());
        ffe.setSmokeDetectorServiceby(smkDetectorServByTextField.getText());
        ffe.setHeatDetectorServiceby(heatDetectorServByTextField.getText());
        ffe.setFireAlarmServiceby(fireAlarmServByTextField.getText());
        ffe.setIncharge(incharge);
        return ffe;
    }
}
