package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import util.FxPageLoader;
import util.Popup;

/**
 *
 * @author Richard Narh
 */
public class MainController implements Initializable {

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
    private TextField textFieldSearch;
    
    private FxPageLoader pageLoader;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
      pageLoader = new FxPageLoader();
//      textFieldSearch.textProperty().addListener(((observable, oldValue, newValue) -> {
//          filterData(newValue);
//      }));
    }

    TextField ownerTextField[] = new TextField[6];
    Label ownerLabel[] = new Label[6];

    TextField occupyTextField[] = new TextField[15];
    Label occupyLabel[] = new Label[15];

    TextField staffTextField[] = new TextField[8];
    DatePicker staffDateField[] = new DatePicker[8];

    int ownerRowIndex = 1;
    int occupyRowIndex = 1;
    int staffRowIndex = 1;
    int i = 1;
    int j = 1;
    int k = 1;

    @FXML
    public void btnAddOwnerRow(ActionEvent event) {
        System.out.println("Adding Owner Row....." + i);
        ownerTextField[1] = new TextField();
        ownerTextField[1].setLayoutX(340);
        ownerTextField[1].setId("ownerNameTextField" + i);
        ownerTextField[1].setPromptText("Name Field - " + i);
        GridPane.setMargin(ownerTextField[1], new Insets(0, 5, 0, 0));

        ownerTextField[2] = new TextField();
        ownerTextField[2].setLayoutX(340);
        ownerTextField[2].setId("ownerTeleHandyTextField" + i);
        ownerTextField[2].setPromptText("Tele Handy Field - " + i);
        GridPane.setMargin(ownerTextField[2], new Insets(0, 5, 0, 0));

        ownerTextField[3] = new TextField();
        ownerTextField[3].setLayoutX(340);
        ownerTextField[3].setId("ownerTeleOfficeTextField" + i);
        ownerTextField[3].setPromptText("Tele Office Field - " + i);
        GridPane.setMargin(ownerTextField[3], new Insets(0, 5, 0, 0));

        ownerTextField[4] = new TextField();
        ownerTextField[4].setLayoutX(340);
        ownerTextField[4].setId("ownerPurposeTextField" + i);
        ownerTextField[4].setPromptText("Purpose Field - " + i);
        GridPane.setMargin(ownerTextField[4], new Insets(0, 5, 0, 0));

        ownerLabel[1] = new Label();
        ownerLabel[1].setId("owner" + String.valueOf(i));
        ownerLabel[1].setText(String.valueOf(i));

        if (ownerRowIndex > 3) {
            Popup.error("You cannot add more than 3 rows");
            return;
        }

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
        occupyTextField[1] = new TextField();
        occupyTextField[1].setLayoutX(340);
        occupyTextField[1].setId("occupyerNameTextField" + j);
        occupyTextField[1].setPromptText("Name Field - " + j);
        GridPane.setMargin(occupyTextField[1], new Insets(0, 5, 0, 0));

        occupyTextField[2] = new TextField();
        occupyTextField[2].setLayoutX(340);
        occupyTextField[2].setId("occupyerTeleHandyTextField" + j);
        occupyTextField[2].setPromptText("Tele Handy Field - " + j);
        GridPane.setMargin(occupyTextField[2], new Insets(0, 5, 0, 0));

        occupyTextField[3] = new TextField();
        occupyTextField[3].setLayoutX(340);
        occupyTextField[3].setId("occupyerTeleOfficeTextField" + j);
        occupyTextField[3].setPromptText("Tele Office Field - " + j);
        GridPane.setMargin(occupyTextField[3], new Insets(0, 5, 0, 0));

        occupyTextField[4] = new TextField();
        occupyTextField[4].setLayoutX(340);
        occupyTextField[4].setId("occupyerPurposeTextField" + j);
        occupyTextField[4].setPromptText("Purpose Field - " + j);
        GridPane.setMargin(occupyTextField[4], new Insets(0, 5, 0, 0));

        occupyLabel[1] = new Label();
        occupyLabel[1].setId("occupy" + String.valueOf(j));
        occupyLabel[1].setText(String.valueOf(j));

        if (occupyRowIndex > 6) {
            Popup.error("You cannot add more than 6 rows");
            return;
        }

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
        staffTextField[1] = new TextField();
        staffTextField[1].setLayoutX(340);
        staffTextField[1].setId("staffNameTextField" + k);
        staffTextField[1].setPromptText("Name Field - " + k);
        GridPane.setMargin(staffTextField[1], new Insets(0, 5, 0, 0));

        staffTextField[2] = new TextField();
        staffTextField[2].setLayoutX(340);
        staffTextField[2].setId("staffPositionTextField" + k);
        staffTextField[2].setPromptText("Postion Field - " + k);
        GridPane.setMargin(staffTextField[2], new Insets(0, 5, 0, 0));

        staffTextField[3] = new TextField();
        staffTextField[3].setLayoutX(340);
        staffTextField[3].setId("staffTrainingTextField" + k);
        staffTextField[3].setPromptText("Training Field - " + k);
        GridPane.setMargin(staffTextField[3], new Insets(0, 5, 0, 0));

        staffDateField[4] = new DatePicker();
        staffDateField[4].setLayoutX(340);
        staffDateField[4].setId("staffDateField" + k);
        staffDateField[4].setPromptText("Date Field - " + k);
        GridPane.setMargin(staffDateField[4], new Insets(0, 5, 0, 0));

        staffTextField[5] = new TextField();
        staffTextField[5].setLayoutX(340);
        staffTextField[5].setId("staffTelephoneTextField" + k);
        staffTextField[5].setPromptText("Telephone Field - " + k);
        GridPane.setMargin(staffTextField[5], new Insets(0, 5, 0, 0));

        if (staffRowIndex > 5) {
            Popup.error("You cannot add more than 5 rows");
            return;
        }

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
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/Sms.fxml", "GNFS - SMS", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void expiredListAction(ActionEvent event) {
        FxPageLoader fxPageLoader = new FxPageLoader(((Node) event.getSource()).getScene().getWindow());
        fxPageLoader.loadFxml("/fxml/ExpiredList.fxml", "GNFS - Expired List", Modality.APPLICATION_MODAL, false);
    }
    
    @FXML
    public void resetPageAction(ActionEvent event) {
        Popup.confirm("Do you want to reset the page?", "Resetting", "Reset");
    }

    @FXML
    public void saveAllAction(ActionEvent event) {
        Popup.info("Saving it all...........");
    }
    
    @FXML
    public void searchAction(ActionEvent event) {
        Popup.info("Searching for something...........");
    }

    @FXML
    public void closeAction(ActionEvent event) {
        Alert alert = Popup.confirm("Do you want to exit app ?", "Exiting...");
        if(alert.getResult() == ButtonType.YES){
            System.exit(0);
        }
    }
    

    private void filterData(String newValue) {
        
    }

}
