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
import com.gnfs.model.Params;
import com.gnfs.model.Sms;
import com.gnfs.services.GnfsManager;
import com.gnfs.util.DateUtil;
import com.gnfs.util.DefaultManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import com.gnfs.util.FxPageLoader;
import com.gnfs.util.JUtils;
import com.gnfs.util.Pattern;
import com.gnfs.util.Popup;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 *
 * @author Richard Narh
 */
public class MainController implements Initializable {
    private static final Logger log = LoggerFactory.getLogger(MainController.class);
    
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnUploadFile;
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

    private String receipient,telephone,id,premId,fscId,ffeId,siId,cId = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inchargeCmb();
        btnSettings.setGraphic(new ImageView(new Image("/icons/settings.png")));
        btnSettings.setTooltip(new Tooltip("Settings"));
        
        btnUploadFile.setGraphic(new ImageView(new Image("/icons/upload.png")));
        btnUploadFile.setTooltip(new Tooltip("Upload Images/File"));
        log.info("Login successful.");
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
                    return dto.getOfficerInCharge();
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
    Label[] staffLabel = new Label[20];

    int ownerRowIndex = 1;
    int occupyRowIndex = 1;
    int staffRowIndex = 1;
    int i = 1;
    int j = 1;
    int k = 1;
    
    LinkedHashMap<Integer, LinkedHashSet<Object>> ownerMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashSet<Object>> occupyerMap = new LinkedHashMap<>();
    LinkedHashMap<Integer, LinkedHashSet<Object>> trainedFireSafetyStaffMap = new LinkedHashMap<>();
    
    @FXML
    public void btnAddOwnerRow(ActionEvent event) {
        createOwners(null);
    }

    @FXML
    public void btnAddOccupyerRow(ActionEvent event) {
        createOccupyers(null);
    }

    @FXML
    public void btnAddStaffRow(ActionEvent event) {
        createTrainedFireSafetyStaff(null);
    }

    @FXML
    public void initiateSMSAction(ActionEvent event) {
        Sms.newInstance().addReceipient(receipient).addTelephone(telephone);
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
    public void mediaAction(ActionEvent event) {
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Media", "GNFS - Media Data", Modality.APPLICATION_MODAL, false);
    }
    @FXML
    public void uploadAction(ActionEvent event) {
        Window owner = ((Node)event.getSource()).getScene().getWindow();
        if (receipient == null && id == null) {
            Popup.error(owner,"There is no premises found for the upload");
            return;
        }
        Params.newInstance().addId(id).addData(receipient);
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Upload", "GNFS - Upload Files", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void resetPageAction(ActionEvent event) {
        Window owner = ((Node)event.getSource()).getScene().getWindow();
        resetPage(owner);
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
            ParticularPremises pp = DefaultManager.save(premisesData(incharge));
            DefaultManager.save(fireSafetyData(incharge,pp));
            DefaultManager.save(fireFightingEquipment(incharge,pp));
            DefaultManager.save(specialInst(incharge,pp));
            DefaultManager.save(collDate(incharge,pp));
            if (!ownerMap.isEmpty()) {
                ownerList = GnfsManager.createOwners(ownerMap);
                for (ParticularOwners owner : ownerList) {
                    owner.setIncharge(incharge);
                    owner.setParticularPremises(pp);
                    DefaultManager.save(owner);
                }
            }
            if (!occupyerMap.isEmpty()) {
                occupyerList = GnfsManager.createOccupyers(occupyerMap);
                for (ParticularOccupyers occupyer : occupyerList) {
                    occupyer.setIncharge(incharge);
                    occupyer.setParticularPremises(pp);
                    DefaultManager.save(occupyer);
                }
            }
            if (!trainedFireSafetyStaffMap.isEmpty()) {
                trainedFireSafetyStaffList = GnfsManager.createTrainedFireSafetyStaff(trainedFireSafetyStaffMap);
                for (TrainedFireSafetyStaff staff : trainedFireSafetyStaffList) {
                    staff.setIncharge(incharge);
                    staff.setParticularPremises(pp);
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
        log.debug("Search: {}",gloabelSearchTextField.getText());
        if(gloabelSearchTextField.getText() == null || gloabelSearchTextField.getText().isEmpty()){
            Popup.error(owner, "No search entry entered.");
            return;
        }
        ParticularPremises pp = null;
        Incharge incharge = null;
        String searchKey = gloabelSearchTextField.getText();
        SafetyCertificate sc = GnfsManager.getCertificate(searchKey,null);
        if(sc == null){
            sc = GnfsManager.getCertificate(null,searchKey);
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
            fscId = sc.getId();
            textFieldSafetyName.setText(sc.getName());
            textFieldHseNo.setText(sc.getHouseNo());
            textFieldGPRS.setText(sc.getGprs());
            textFieldSafetyLocation.setText(sc.getLocation());
            textFieldRiskType.setText(sc.getTypeOfRisk());
            issueDateField.setValue(DateUtil.dateToLocalDate(sc.getIssueDate(),Pattern.mmddyyyy));
            expiryDateField.setValue(DateUtil.dateToLocalDate(sc.getExpiryDate(),Pattern.mmddyyyy));
            textFieldCertNo.setText(sc.getCertificateNo());
        }
        
        if(pp != null){
            incharge = pp.getIncharge();
            if(incharge != null){
                officerCmb.setValue(new InchargeDto(incharge.getId(), incharge.getOfficerInCharge()));
            }
            premId = pp.getId();
            id = pp.getId();
            textFieldName.setText(pp.getName());
            textFieldType.setText(pp.getPremType());
            textFieldLocation.setText(pp.getLocation());
            textFieldLandMark.setText(pp.getLandMark());
            textFieldTelephone.setText(pp.getTelephone());
            receipient = pp.getName() +" - "+pp.getTelephone();
            telephone = pp.getTelephone();
            
            FireFightingEquipment ffe = GnfsManager.getFireFightingEquipment(pp);
            if(ffe != null){
                ffeId = ffe.getId();
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
                dcpInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getDcpInstDate(),Pattern.mmddyyyy));
                emergencyLightInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getEmergencyLightInstDate(),Pattern.mmddyyyy));
                smkDetectorInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getSmokeDetectorInstDate(),Pattern.mmddyyyy));
                heatDetectorInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getHeatDetectorInstDate(),Pattern.mmddyyyy));
                fireAlarmInstDateField.setValue(DateUtil.dateToLocalDate(ffe.getFireAlarmInstDate(),Pattern.mmddyyyy));
                dcpServDatefield.setValue(DateUtil.dateToLocalDate(ffe.getDcpServiceDate(),Pattern.mmddyyyy));
                emergencyLightServDateField.setValue(DateUtil.dateToLocalDate(ffe.getEmergencyLightServiceDate(),Pattern.mmddyyyy));
                smkDetectorServDateField.setValue(DateUtil.dateToLocalDate(ffe.getSmokeDetectorServiceDate(),Pattern.mmddyyyy));
                heatDetectorServDateField.setValue(DateUtil.dateToLocalDate(ffe.getHeatDetectorServiceDate(),Pattern.mmddyyyy));
                fireAlarmServDateField.setValue(DateUtil.dateToLocalDate(ffe.getFireAlarmServiceDate(),Pattern.mmddyyyy));
                dcpServByTextField.setText(ffe.getDcpServiceby());
                emergencyLightServByTextField.setText(ffe.getEmergencyLightServiceby());
                smkDetectorServByTextField.setText(ffe.getSmokeDetectorServiceby());
                heatDetectorServByTextField.setText(ffe.getHeatDetectorServiceby());
                fireAlarmServByTextField.setText(ffe.getFireAlarmServiceby());
            }
            
            SpecialInstallation si = GnfsManager.getSpecialInstallation(pp);
            if(si != null){
                siId = si.getId();
                textFieldHydrant.setText(si.getHydrant());
                textFieldSmokeExtractor.setText(si.getSmokeExtractor());
                textFieldDryRisers.setText(si.getDryRisers());
                textFieldHeatExtractor.setText(si.getHeatExtractor());
                textFieldWetRisers.setText(si.getWetRisers());
                textFieldHoseReel.setText(si.getHoseReel());
            }
            
            CollectionDate cd = GnfsManager.getCollectionDate(pp);
            if(cd != null){
                cId = cd.getId();
                collectionDateField.setValue(DateUtil.dateToLocalDate(cd.getDateOfCollection(), Pattern.mmddyyyy));
            }
            
            List<ParticularOwners> particularOwnerList = GnfsManager.getParticularOwners(pp);
            particularOwnerList.forEach(owners -> {
                createOwners(owners);
            });
            List<ParticularOccupyers> occupyersList = GnfsManager.getParticularOccupyers(pp);
            occupyersList.forEach(particularOccupyers -> {
                createOccupyers(particularOccupyers);
            });
            List<TrainedFireSafetyStaff> safetyStaffList = GnfsManager.getTrainedFireSafetyStaff(pp);
            safetyStaffList.forEach(trainedFireSafetyStaff -> {
                createTrainedFireSafetyStaff(trainedFireSafetyStaff);
            });
        }
    }
    
    public void resetPage(Window owner){
        owner.hide();
        FxPageLoader fxPageLoader = new FxPageLoader(owner);
        Stage stage = fxPageLoader.loadFxml("/fxml/Main", "GNFS - FIRE PRECAUTION DATA COLLECTION FORM", Modality.WINDOW_MODAL);
        stage.setHeight(680);
        stage.setResizable(false);
        stage.show();
        stage.setX(stage.getX() + stage.getWidth() / 2 - stage.getWidth() / 2);
        stage.setY(stage.getY() + stage.getHeight() / 2 - stage.getHeight() / 2);
//        System.out.println("Reset successful");
        log.info("Reset successful");
    }
 
    private void createOwners(ParticularOwners owners){
        log.debug("Adding Owner Row..... {}", i);
        LinkedHashSet<Object> fieldList = new LinkedHashSet<>();
        ownerTextField[1] = new TextField();
        ownerTextField[1].setLayoutX(340);
        ownerTextField[1].setId("ownerNameField" + i);
        ownerTextField[1].setPromptText("Name Field - " + i);
        ownerTextField[1].setText(owners != null ? owners.getOwnerName() : null);
        GridPane.setMargin(ownerTextField[1], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[1]);

        ownerTextField[2] = new TextField();
        ownerTextField[2].setLayoutX(340);
        ownerTextField[2].setId("ownerTeleHandyField" + i);
        ownerTextField[2].setPromptText("Tele Handy Field - " + i);
        ownerTextField[2].setText(owners != null ? owners.getTeleHandy(): null);
        GridPane.setMargin(ownerTextField[2], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[2]);
        
        ownerTextField[3] = new TextField();
        ownerTextField[3].setLayoutX(340);
        ownerTextField[3].setId("ownerTeleOfficeField" + i);
        ownerTextField[3].setPromptText("Tele Office Field - " + i);
        ownerTextField[3].setText(owners != null ? owners.getTeleOffice(): null);
        GridPane.setMargin(ownerTextField[3], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[3]);

        ownerTextField[4] = new TextField();
        ownerTextField[4].setLayoutX(340);
        ownerTextField[4].setId("ownerPurposeField" + i);
        ownerTextField[4].setPromptText("Purpose Field - " + i);
        ownerTextField[4].setText(owners != null ? owners.getPurpose(): null);
        GridPane.setMargin(ownerTextField[4], new Insets(0, 5, 0, 0));
        fieldList.add(ownerTextField[4]);

        ownerLabel[1] = new Label();
        ownerLabel[1].setId(owners != null ? owners.getId() : "owner" + String.valueOf(i));
        ownerLabel[1].setText(String.valueOf(i));
        fieldList.add(ownerLabel[1]);
        
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
    private void createOccupyers(ParticularOccupyers occupyers){
        log.debug("Adding Occupyer Row..... {}", j);
        LinkedHashSet<Object> fieldList = new LinkedHashSet<>();
        occupyTextField[1] = new TextField();
        occupyTextField[1].setLayoutX(340);
        occupyTextField[1].setId("occupyerNameField" + j);
        occupyTextField[1].setPromptText("Name Field - " + j);
        occupyTextField[1].setText(occupyers != null ? occupyers.getOccupyerName() : null);
        GridPane.setMargin(occupyTextField[1], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[1]);

        occupyTextField[2] = new TextField();
        occupyTextField[2].setLayoutX(340);
        occupyTextField[2].setId("occupyerTeleHandyField" + j);
        occupyTextField[2].setPromptText("Tele Handy Field - " + j);
        occupyTextField[2].setText(occupyers != null ? occupyers.getTeleHandy() : null);
        GridPane.setMargin(occupyTextField[2], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[2]);

        occupyTextField[3] = new TextField();
        occupyTextField[3].setLayoutX(340);
        occupyTextField[3].setId("occupyerTeleOfficeField" + j);
        occupyTextField[3].setPromptText("Tele Office Field - " + j);
        occupyTextField[3].setText(occupyers != null ? occupyers.getTeleOffice() : null);
        GridPane.setMargin(occupyTextField[3], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[3]);

        occupyTextField[4] = new TextField();
        occupyTextField[4].setLayoutX(340);
        occupyTextField[4].setId("occupyerPurposeField" + j);
        occupyTextField[4].setPromptText("Usage/Activity Field - " + j);
        occupyTextField[4].setText(occupyers != null ? occupyers.getUsageActivate() : null);
        GridPane.setMargin(occupyTextField[4], new Insets(0, 5, 0, 0));
        fieldList.add(occupyTextField[4]);
        occupyLabel[1] = new Label();
        occupyLabel[1].setId(occupyers != null ? occupyers.getId() : "occupyer" + String.valueOf(j));
        occupyLabel[1].setText(String.valueOf(j));
        fieldList.add(occupyLabel[1]);
       
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
    private void createTrainedFireSafetyStaff(TrainedFireSafetyStaff staff){
        log.debug("Adding Staff Row..... {}", k);
        LinkedHashSet<Object> fieldList = new LinkedHashSet<>();
        staffTextField[1] = new TextField();
        staffTextField[1].setLayoutX(340);
        staffTextField[1].setId("staffNameField" + k);
        staffTextField[1].setPromptText("Name Field - " + k);
        staffTextField[1].setText(staff != null ? staff.getName() : null);
        GridPane.setMargin(staffTextField[1], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[1]);

        staffTextField[2] = new TextField();
        staffTextField[2].setLayoutX(340);
        staffTextField[2].setId("staffPositionField" + k);
        staffTextField[2].setPromptText("Postion Field - " + k);
        staffTextField[2].setText(staff != null ? staff.getPosition() : null);
        GridPane.setMargin(staffTextField[2], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[2]);

        staffTextField[3] = new TextField();
        staffTextField[3].setLayoutX(340);
        staffTextField[3].setId("staffTrainingField" + k);
        staffTextField[3].setPromptText("Training Field - " + k);
        staffTextField[3].setText(staff != null ? staff.getTypeOftraining() : null);
        GridPane.setMargin(staffTextField[3], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[3]);

        staffDateField[4] = new DatePicker();
        staffDateField[4].setLayoutX(340);
        staffDateField[4].setId("staffDateField" + k);
        staffDateField[4].setPromptText("Staff Date Field - " + k);
        staffDateField[4].setValue(staff != null ? DateUtil.dateToLocalDate(staff.getTrainedDate(), Pattern.mmddyyyy) : null);
        GridPane.setMargin(staffDateField[4], new Insets(0, 5, 0, 0));
        fieldList.add(staffDateField[4]);

        staffTextField[5] = new TextField();
        staffTextField[5].setLayoutX(340);
        staffTextField[5].setId("staffTelephoneField" + k);
        staffTextField[5].setPromptText("Telephone Field - " + k);
        staffTextField[5].setText(staff != null ? staff.getPosition() : null);
        GridPane.setMargin(staffTextField[5], new Insets(0, 5, 0, 0));
        fieldList.add(staffTextField[5]);
        
        staffLabel[6] = new Label();
        staffLabel[6].setId(staff != null ? staff.getId() : "staff" + String.valueOf(j));
        staffLabel[6].setText(String.valueOf(j));
        fieldList.add(staffLabel[6]);

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
    
    public CollectionDate collDate(Incharge incharge,ParticularPremises pp){
        CollectionDate cd = new CollectionDate();
        cd.setId(cId);
        cd.setIncharge(incharge);
        cd.setParticularPremises(pp);
        cd.setDateOfCollection(DateUtil.localDateToDate(collectionDateField.getValue()));
        return cd; 
    }
    
    public ParticularPremises premisesData(Incharge incharge){
        ParticularPremises premises = new ParticularPremises();
        premises.setId(premId);
        premises.setName(textFieldName.getText());
        premises.setPremType(textFieldType.getText());
        premises.setLocation(textFieldLocation.getText());
        premises.setLandMark(textFieldLandMark.getText());
        premises.setTelephone(textFieldTelephone.getText());
        premises.setIncharge(incharge);
        return premises;
    }
        
    public SpecialInstallation specialInst(Incharge incharge,ParticularPremises pp){
        SpecialInstallation si = new SpecialInstallation();
        si.setId(siId);
        si.setHydrant(textFieldHydrant.getText());
        si.setSmokeExtractor(textFieldSmokeExtractor.getText());
        si.setDryRisers(textFieldDryRisers.getText());
        si.setHeatExtractor(textFieldHeatExtractor.getText());
        si.setWetRisers(textFieldWetRisers.getText());
        si.setHoseReel(textFieldHoseReel.getText());
        si.setIncharge(incharge);
        si.setParticularPremises(pp);
        return si;
    }
    
    public SafetyCertificate fireSafetyData(Incharge incharge,ParticularPremises pp){
        SafetyCertificate sc = new SafetyCertificate();
        sc.setId(fscId);
        sc.setName(textFieldSafetyName.getText());
        sc.setHouseNo(textFieldHseNo.getText());
        sc.setLocation(textFieldSafetyLocation.getText());
        sc.setTypeOfRisk(textFieldRiskType.getText());
        sc.setGprs(textFieldGPRS.getText());
        sc.setIssueDate(DateUtil.localDateToDate(issueDateField.getValue()));
        sc.setExpiryDate(DateUtil.localDateToDate(expiryDateField.getValue()));
        sc.setCertificateNo(textFieldCertNo.getText());
        sc.setIncharge(incharge);
        sc.setParticularPremises(pp);
        return sc;
    }
    
    public FireFightingEquipment fireFightingEquipment(Incharge incharge,ParticularPremises pp){
        FireFightingEquipment ffe = new FireFightingEquipment();
        ffe.setId(ffeId);
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
        ffe.setParticularPremises(pp);
        return ffe;
    }
}