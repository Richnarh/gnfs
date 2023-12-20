/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
    private Button btnLogin;

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
    private void doLoginAction(ActionEvent event) throws SQLException, ClassNotFoundException, IOException {
        Window owner = btnLogin.getScene().getWindow();

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

        boolean flag = true;
//        boolean flag = AuthService.login(emailId, password);
        if (!flag) {
            Popup.confirm("Please enter correct Email and Password", null, "Failed");
        } else {
            Node source = (Node) event.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

            Window window = ((Node) event.getSource()).getScene().getWindow();

            indexPage(window);
        }
    }

    private void indexPage(Window window)throws IOException{
        Stage stage  = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Main.fxml"));
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
